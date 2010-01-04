/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.mapfaces.renderkit.html;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.el.ValueExpression;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.geotoolkit.util.logging.Logging;

import org.mapfaces.component.UIComponentSelector;
import org.mapfaces.component.UITemporal;
import org.mapfaces.model.ComponentDescriptor;
import org.mapfaces.model.tree.ExtendMutableTreeNode;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.FacesUtils;

/**
 * @author Leo Pratlong (Geomatys)
 * @since 0.3
 */
public class ComponentSelectorRenderer extends Renderer {

    private static final Logger LOGGER = Logging.getLogger("org.mapfaces.renderkit.html");
    private static final String JS_FUNCTIONS_LIBRARY = "/org/mapfaces/resources/componentcontrols/js/componentUtilities.js";
    private static final String CSS_STYLES = "/org/mapfaces/metadata/css/metadataStyles.css";
    private static final String MANDATORY_FIELD_CLASS = "mandatory_field";
    private static final String DEFAULT_COMPONENT_CLASS = "default_field";
    private static final String CHAR_CONTROL_CLASS = "char_control";

    private static final int DEFAULT_MAX_CAR = 250;

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        final UIComponentSelector compSel = (UIComponentSelector) component;
        boolean isInIterator = false;
        // Check if this component has an Iterator parent.
        final UIComponent parent = FacesUtils.findParentComponentByClass(compSel, UIData.class);

        if (parent instanceof UIData) {
            // if this component is a child of an iterator, we take the value of the var
            // and set the value of our UI.
            isInIterator = true;
            final Object obj = context.getExternalContext().getRequestMap().get(((UIData) parent).getVar());
            ComponentDescriptor varObj = null;
            if (obj instanceof ComponentDescriptor) {
                varObj = (ComponentDescriptor) obj;
            } else if ((obj instanceof ExtendMutableTreeNode) && (((ExtendMutableTreeNode) obj).getUserObject() instanceof ComponentDescriptor)) {
                varObj = (ComponentDescriptor) ((ExtendMutableTreeNode) obj).getUserObject();
            }
            if (varObj != null) {
                compSel.setType(varObj.getType());
                compSel.setValue((varObj.getValue() != null) ? varObj.getValue().toString() : "");
                compSel.setKey(varObj.getTitle());
                compSel.setMandatory(varObj.isMandatory());
                compSel.setMaxCar(varObj.getMaxCar());
                compSel.setMaxOccurence(varObj.getMaxOccurence());
                compSel.setSelectMap(varObj.getSelectMap());
            }
        }

        if ((compSel.getId() != null) && (compSel.getType() != null)) {
            final String type = compSel.getType();

            if (compSel.isHasParent() == false) {
                final ResponseWriter writer = context.getResponseWriter();
                writer.startElement("script", compSel);
                writer.writeAttribute("type", "text/javascript", null);
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_FUNCTIONS_LIBRARY, null), null);
                writer.endElement("script");

                writer.startElement("link", compSel);
                writer.writeAttribute("type", "text/css", null);
                writer.writeAttribute("rel", "stylesheet", null);
                writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_STYLES, null), null);
                writer.endElement("link");
            }
            // We render the component of the type indicated.
            renderValue(context, compSel, type, compSel.getKey() + "_" + compSel.getType(), isInIterator);
        } else {
            LOGGER.severe("mdi.getValue() return NULL into encodeBegin method");
        }

    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (component instanceof UIComponentSelector) {
            UIComponentSelector compSel = ((UIComponentSelector) component);
            UIComponent child = FacesUtils.findComponentById(context, component, compSel.getKey() + "_" + compSel.getType());
            if (child != null) FacesUtils.encodeRecursive(context, child);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        final UIComponentSelector compSel = (UIComponentSelector) component;
        // We check if the ComponentSelector has an iterator parent.
        UIComponent parent = FacesUtils.findParentComponentByClass(compSel, UIData.class);
        if (parent instanceof UIData) {
            // in this case, we catch the value of the current Var (value for the row).
            final Object obj = context.getExternalContext().getRequestMap().get(((UIData) parent).getVar());
            ComponentDescriptor varObj = null;
            if (obj instanceof ComponentDescriptor) {
                varObj = (ComponentDescriptor) obj;
            } else if ((obj instanceof ExtendMutableTreeNode) && (((ExtendMutableTreeNode) obj).getUserObject() instanceof ComponentDescriptor)) {
                varObj = (ComponentDescriptor) ((ExtendMutableTreeNode) obj).getUserObject();
            }
            if (varObj != null) {
                // And we catch the type and key values.
                compSel.setType(varObj.getType());
                compSel.setKey(varObj.getTitle());
                // We check if there is a child with the Key value for id.
                UIComponent child = FacesUtils.findComponentById(context, component, compSel.getKey() + "_" + compSel.getType());
                String type = compSel.getType();
                Object value = null;
                if ((child != null) && "text".equals(type) || "web".equals(type) || "mail".equals(type) || "select".equals(type)) {
                    // We catch the value of the client component with the Request parameter map.
                    value = FacesUtils.getRequestParameterValue(context, child.getClientId(context));
                } else if ("textarea".equals(type)) {
                    // If the type is a textarea, then we have to catch the HTMLInputTextarea containing the value.
                    final UIComponent inputChild = FacesUtils.findComponentById(context, child, compSel.getKey() + "_" + compSel.getType() + "_sub");
                    if (inputChild instanceof HtmlInputTextarea) {
                        child = inputChild;
                        // We catch the value of the client component with the Request parameter map.
                        value = FacesUtils.getRequestParameterValue(context, child.getClientId(context));
                    }
                } else if ("date".equals(type)) {
                    value = FacesUtils.getRequestParameterValue(context, child.getClientId(context) + "_inputdate");
                } else {
                    LOGGER.severe("this fieldType is not recognize : " + type);
                }
                if (value != null) varObj.setValue(value);
            }
        }
    }

    /**
     * This method calls Render method for the selected component.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param type The type of the component we want to render.
     * @param id Id of the component to render.
     * @param isInIterator Indicates if the ComponentSelector is child of an iterator.
     */
    private void renderValue(FacesContext context, UIComponentSelector compSel, String type, String id, boolean isInIterator) {
        final boolean mandatory = compSel.isMandatory();
        if ("text".equals(type) || "web".equals(type) || "mail".equals(type)) {
            renderText(context, compSel, id, mandatory, type, isInIterator);
        } else if ("textarea".equals(type)) {
            renderTextArea(context, compSel, id, mandatory, isInIterator);
        } else if ("readonly".equals(type)) {
            renderReadOnly(context, compSel, id);
        } else if ("select".equals(type)) {
           renderSelect(context, compSel, id, mandatory, isInIterator);
        } else if ("date".equals(type)) {
            renderDatePicker(context, compSel, id, mandatory, isInIterator);
        } else {
            LOGGER.severe("this fieldType is not recognize : " + type + " for " + id);
        }

        if ((compSel.getMaxOccurence() != null) && (compSel.getMaxOccurence() > 1)) {
           // renderMoreButton(context, compSel, id);
           // renderMinusButton(compSel, item);
        }
    }

    /**
     * Renders an InputText component.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param id The id for the component we'll render.
     * @param mandatory Does this input has to be filled ?
     * @param type the Type of the input (mail, web, text).
     * @param isInIterator Indicates if the ComponentSelector is a child of an iterator.
     */
    private void renderText(FacesContext context, UIComponentSelector compSel, String id, boolean mandatory, String type, boolean isInIterator) {
        final UIComponent input = FacesUtils.findComponentById(context, compSel, getIdWithUnderscores(id));
        final HtmlInputText inputText;

        if (input instanceof HtmlInputText) {
            inputText = (HtmlInputText) input;
        } else {
            /* boolean addNewChild = false;
            if (mdInput instanceof HtmlInputText) {
                inputText = (HtmlInputText) mdInput;
            } else { */
            inputText = new HtmlInputText();
            inputText.setId(getIdWithUnderscores(id));
            // MetadataFieldControlRenderer.renderHtmlInputTextControls(inputText, profileElement);
            //addNewChild = true;
            // fillChildValue(context, compSel, itvalueem, inputText, mandatory, false);
            inputText.setStyleClass(this.returnStyle(mandatory));
            if ("mail".equals(type)) {
                inputText.setOnchange("COMPONENTUTIL.emailcheck(this);");
                inputText.setOnkeyup("COMPONENTUTIL.emailcheck(this);");
            }
            // if (addNewChild)
            compSel.getChildren().add(inputText);
        }
        fillChildValue(context, compSel, inputText, isInIterator);
    }

    /**
     * Renders an InputTextarea component.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param id The id for the component we'll render.
     * @param mandatory Does this input has to be filled ?
     * @param isInIterator Indicates if the ComponentSelector is a child of an iterator.
     */
    private void renderTextArea(FacesContext context, UIComponentSelector compSel, String id, boolean mandatory, boolean isInIterator) {
        final UIComponent input = FacesUtils.findComponentById(context, compSel, getIdWithUnderscores(id));
        final HtmlInputText maxCarText;
        final HtmlInputTextarea inputTextArea;

        final Integer maxCar;
        if (compSel.getMaxCar() != null) {
            maxCar = compSel.getMaxCar();
        } else {
            maxCar = DEFAULT_MAX_CAR;
        }
        boolean setValue = true;
        if (input instanceof HtmlInputText) {
            maxCarText = (HtmlInputText) input;
            final UIComponent inputValue = FacesUtils.findComponentById(context, maxCarText, getIdWithUnderscores(id) + "_sub");
            if (inputValue instanceof HtmlInputTextarea) {
                inputTextArea = (HtmlInputTextarea) inputValue;
            } else {
                inputTextArea = null;
                setValue = false;
            }
        } else {
            inputTextArea = new HtmlInputTextarea();
            inputTextArea.setId(getIdWithUnderscores(id) + "_sub");

            maxCarText = new HtmlInputText();
            maxCarText.setId(getIdWithUnderscores(id));
            maxCarText.setReadonly(true);
            maxCarText.setStyleClass(DEFAULT_COMPONENT_CLASS + " " + CHAR_CONTROL_CLASS);
            maxCarText.getChildren().add(inputTextArea);
            inputTextArea.setStyleClass(this.returnStyle(mandatory));

            inputTextArea.setOnchange("COMPONENTUTIL.maxcharcheck(this,$('" + maxCarText.getClientId(context) + "')," + maxCar + ");");
            inputTextArea.setOnkeyup("COMPONENTUTIL.maxcharcheck(this,$('" + maxCarText.getClientId(context) + "')," + maxCar + ");");

            compSel.getChildren().add(maxCarText);
        }

        int maxCarOnLoad = maxCar;
        if (setValue) {
            fillChildValue(context, compSel, inputTextArea, isInIterator);
            if ((inputTextArea.getValue() != null) && (inputTextArea.getValue().toString().length() <= maxCar)) maxCarOnLoad = maxCar - inputTextArea.getValue().toString().length();
        }

        maxCarText.setValue(maxCarOnLoad + " car.");
    }

    /**
     * Renders a DatePicker component.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param id The id for the component we'll render.
     * @param mandatory Does this input has to be filled ?
     * @param isInIterator Indicates if the ComponentSelector is a child of an iterator.
     */
    private void renderDatePicker(FacesContext context, UIComponentSelector compSel, String id, boolean mandatory, boolean isInIterator) {
        final UIComponent mdInput = FacesUtils.findComponentById(context, compSel, getIdWithUnderscores(id));
        if (context.getExternalContext().getRequestMap().containsKey("ajaxflag.DatePickerjs")) {
            context.getExternalContext().getRequestMap().remove("ajaxflag.DatePickerjs");
        }
        final UITemporal datepicker;
        if (mdInput instanceof UITemporal) {
            datepicker = (UITemporal) mdInput;
        } else {
            datepicker = new UITemporal();
            datepicker.setId(getIdWithUnderscores(id));
            datepicker.setLoadCss(true);
            datepicker.setLoadJs(true);
            datepicker.setLoadMootools(true);
            datepicker.setStyleClass(returnStyle(mandatory));
            compSel.getChildren().add(datepicker);
        }
        fillChildValue(context, compSel, datepicker, isInIterator);
    }

    /**
     * Renders a read only text component.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param id The id for the component we'll render.
     */
    private void renderReadOnly(FacesContext context, UIComponentSelector compSel, String id) {
        final UIComponent mdInput = FacesUtils.findComponentById(context, compSel, getIdWithUnderscores(id));
        if ((mdInput == null) && (compSel.getValue() != null)) {
            final HtmlOutputText outputText = new HtmlOutputText();
            outputText.setId(getIdWithUnderscores(id));
            outputText.setValue(compSel.getValue());
            compSel.getChildren().add(outputText);
        }
    }

    /**
     * Renders a SelectOneMenu component.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param id The id for the component we'll render.
     * @param mandatory Does this input has to be filled ?
     * @param isInIterator Indicates if the ComponentSelector is a child of an iterator.
     */
    private void renderSelect(FacesContext context, UIComponentSelector compSel, String id, boolean mandatory, boolean isInIterator) {
        final UIComponent mdInput = FacesUtils.findComponentById(context, compSel, getIdWithUnderscores(id));
        final HtmlSelectOneMenu selectOneMenu;
        if (mdInput instanceof HtmlSelectOneMenu) {
            selectOneMenu = (HtmlSelectOneMenu) mdInput;
            // selectOneMenu.getChildren().clear();
        } else {
            selectOneMenu = new HtmlSelectOneMenu();
            selectOneMenu.setId(getIdWithUnderscores(id));

            if (compSel.getSelectMap() != null) {
                final Map<Object, String> selectMap = compSel.getSelectMap();
                for (Map.Entry<Object, String> e : selectMap.entrySet()) {
                    final UISelectItem selectItem = new UISelectItem();
                    selectItem.setItemLabel(e.getValue());
                    selectItem.setItemValue(e.getKey());
                    selectOneMenu.getChildren().add(selectItem);
                }

                selectOneMenu.setStyleClass(this.returnStyle(mandatory));
                compSel.getChildren().add(selectOneMenu);
            }
        }

        fillChildValue(context, compSel, selectOneMenu, isInIterator);
    }

    /**
     *
     * @param compSel
     * @param item
     */
    private void renderMoreButton(FacesContext context, UIComponentSelector component, String id) {
  /*      final UIComponent mdInput = FacesUtils.findComponentById(context, compSel, getIdWithUnderscores(id) + "_more");
        if (mdInput == null) {
            final HtmlOutputLink linkMore;
            final HtmlGraphicImage moreOccurence = new HtmlGraphicImage();
            linkMore = new HtmlOutputLink();
            final UITreeColumn treecolum = (UITreeColumn) mdi.getParent();
            final UITreeTable treetable = (UITreeTable) treecolum.getParent();
            final String formId = FacesUtils.getParentComponentIdByClass(compSel, UIForm.class);
            final String treetableId = treetable.getId();
            final String actionParamIDId = compSel.getId() + "_actionParamID";
            final String actionParamADDId = compSel.getId() + "_actionParamADD";
            final int index = treetable.getRowIndex();
            final int actionParamIDValue = index;
            final boolean actionParamADDValue = true;

            final StringBuilder a4jRequest = new StringBuilder();
            linkMore.setId(getIdWithUnderscores(item.getPath().getId()) + "_more");
            a4jRequest.append("A4J.AJAX.Submit('").append(formId).append(":metadataRegion','").append(formId).append("',null,{'single':true,'parameters':{'").append(formId).append(":a4jsupport':'").append(formId).append(":a4jsupport',");
            a4jRequest.append("'").append(formId).append(":").append(treetableId).append(":").append(index).append(":").append(actionParamIDId).append("':").append(actionParamIDValue).append(",");
            a4jRequest.append("'").append(formId).append(":").append(treetableId).append(":").append(index).append(":").append(actionParamADDId).append("':'").append(actionParamADDValue).append("',");
            a4jRequest.append("'refresh':'").append(formId).append(":").append(treetableId).append("'} ,");
            a4jRequest.append("'actionUrl':window.location.href} ); return false;");

            linkMore.setOnclick(a4jRequest.toString());

            moreOccurence.setValue("/resource.jsf?r=/org/mapfaces/resources/tree/images/default/tree/elbow-plus-nl.gif");
            moreOccurence.setStyle("position:absolute; left: 94%;");

            linkMore.getChildren().add(moreOccurence);
            compSel.getChildren().add(linkMore);
        } */
    }

//    /**
//     *
//     * @param compSel
//     * @param item
//     */
//    private void renderMinusButton(UIComponent compSel, ValueItem item) {
//
//        final HtmlGraphicImage minusOccurence = new HtmlGraphicImage();
//        final HtmlOutputLink linkMinus = new HtmlOutputLink();
//        final UIMetadataInput mdi = (UIMetadataInput) compSel;
//        final UITreeColumn treecolum = (UITreeColumn) mdi.getParent();
//        final UITreeTable treetable = (UITreeTable) treecolum.getParent();
//
//        final String formId = FacesUtils.getParentComponentIdByClass(compSel, UIForm.class);
//        final String treetableId = treetable.getId();
//        final String actionParamIDId = compSel.getId() + "_actionParamID";
//        final String actionParamADDId = compSel.getId() + "_actionParamADD";
//        final int index = treetable.getRowIndex();
//        final int actionParamIDValue = index;
//        final boolean actionParamADDValue = false;
//
//        final StringBuilder a4jRequest = new StringBuilder();
//
//        if (item.getOrdinal() > 1) {
//            a4jRequest.append("A4J.AJAX.Submit('").append(formId).append(":metadataRegion','").append(formId).append("',null,{'single':true,'parameters':{'").append(formId).append(":a4jsupport':'").append(formId).append(":a4jsupport',");
//            a4jRequest.append("'").append(formId).append(":").append(treetableId).append(":").append(index).append(":").append(actionParamIDId).append("':").append(actionParamIDValue).append(",");
//            a4jRequest.append("'").append(formId).append(":").append(treetableId).append(":").append(index).append(":").append(actionParamADDId).append("':'").append(actionParamADDValue).append("',");
//            a4jRequest.append("'refresh':'").append(formId).append(":").append(treetableId).append("'} ,");
//            a4jRequest.append("'actionUrl':window.location.href} ); return false;");
//
//            linkMinus.setOnclick(a4jRequest.toString());
//
//            minusOccurence.setValue("/resource.jsf?r=/org/mapfaces/resources/tree/images/default/tree/elbow-end-minus-nl.gif");
//            minusOccurence.setStyle("position:absolute; left: 96%;");
//
//            linkMinus.getChildren().add(minusOccurence);
//            compSel.getChildren().add(linkMinus);
//        }
//
//    }

    /**
     * Return the styleClass according to its Mandatory value.
     * @param mandatory Boolean indicating which of the two styleClass we have to choose.
     * @return String The styleClass.
     */
    private String returnStyle(boolean mandatory) {
        return (mandatory) ? MANDATORY_FIELD_CLASS + " " + DEFAULT_COMPONENT_CLASS : DEFAULT_COMPONENT_CLASS;
    }

    /**
     * This method fills ValueExpressions of the UIInput components. Values will now
     * be linked to the model. If the ComponentSelector is a child of an iterator,
     * we just set the Value without setting a valueExpression. The case of the DatePicker
     * is specific because it also uses an UIInput child which will be setted by the DatePicker.
     * @param context FacesContext
     * @param compSel The UIComponentSelector
     * @param htmlComp UIInput Component.
     * @param isInIterator Indicates if the ComponentSelector is a child of an iterator.
     */
    private void fillChildValue(FacesContext context, UIComponentSelector compSel, UIInput htmlComp, boolean isInIterator) {
        if (isInIterator) {
            htmlComp.setValue(compSel.getValue());
        } else {
            final ValueExpression ve = compSel.getValueExpression("value");
            if (ve != null) {
                if (ve.getValue(context.getELContext()) instanceof String) {
                    if (!(htmlComp instanceof UITemporal)) {
                        htmlComp.setValue(ve.getValue(context.getELContext()));
                    }
                    htmlComp.setValueExpression("value", ve);
                }
            }
        }
    }

    /**
     * Return a valid ID (without ':' or ' ');
     * @param id Initial id.
     * @return String The valid Id.
     */
    private String getIdWithUnderscores(String id) {
        id = id.replace(':', '_');
        id = id.replace(' ', '_');
        return id;
    }

    /**
     * Check if the context and the component are setted.
     * @param context FacesContext
     * @param component The UIComponent.
     */
    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}

