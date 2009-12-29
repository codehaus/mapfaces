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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
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
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.FacesUtils;

/**
 * @author Leo Pratlong
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
        System.out.println("ENCODE BEGIN !!!");
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        final UIComponentSelector compSel = (UIComponentSelector) component;
        if ((compSel.getId() != null) && (compSel.getType() != null)) {
            final String type = compSel.getType();
            final String id = compSel.getId();

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

            renderValue(context, compSel, type, id);
            System.out.println("ENCODE BEGIN END !!!!!!");
        } else {
            LOGGER.severe("mdi.getValue() return NULL into encodeBegin method");
        }

    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        super.encodeChildren(context, component);
    }

//    @Override
//    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
//        UIComponent child = getCurrentChild(component);
//        if (child != null) {
//            FacesUtils.encodeRecursive(context, child);
//            UIComponent childMore = getChildWithId(component, child.getId() + "_more");
//            if (childMore != null) {
//                FacesUtils.encodeRecursive(context, childMore);
//            }
//        }
//    }


    @Override
    public boolean getRendersChildren() {

        return true;

    }

    @Override
    public void decode(FacesContext context, UIComponent component) {

//        ValueItem item;
//        final UIMetadataInput mdi = (UIMetadataInput) component;
//
//        if (mdi.getValue() != null) {
//            if (mdi.getValue() instanceof ValueItem) {
//                item = (ValueItem) mdi.getValue();
//
//                // Because of some bugs due to the bad architecture of this component, we
//                // catch the value of the input typed by user with the "getSubmittedValue" method,
//                // then set the Item.value.
//                UIComponent child = getCurrentChild(component);
//                if ((child instanceof HtmlSelectOneMenu) && ((UIInput)child).getSubmittedValue() != null) {
//                    item.setValue((String) ((UIInput)child).getSubmittedValue());
//                } else if (child instanceof UIDatepicker) {
//                    List<UIComponent> dpChildren = child.getChildren();
//                    boolean find = false;
//                    int i = 0;
//                    while ((i < dpChildren.size()) && !find) {
//                        if ((dpChildren.get(i).getId() != null) && child.getId().concat("_inputdate").equals(dpChildren.get(i).getId())) {
//                            if (((UIInput)dpChildren.get(i)).getSubmittedValue() != null) {
//                                item.setValue(((UIInput)dpChildren.get(i)).getSubmittedValue().toString());
//                            }
//                            find = true;
//                        }
//                        i++;
//                    }
//                }
//                if (FacesUtils.getRequestParameterValue(context, component.getClientId(context) + "_actionParamID") != null) {
//                    final boolean rowToAdd = Boolean.valueOf((String) FacesUtils.getRequestParameterValue(context, component.getClientId(context) + "_actionParamADD"));
//                    final String rowId = (String) FacesUtils.getRequestParameterValue(context, component.getClientId(context) + "_actionParamID");
//
//                    final UITreeColumn treecolum = (UITreeColumn) mdi.getParent();
//                    final UITreeTable treetable = (UITreeTable) treecolum.getParent();
//                    final DefaultTreeModel defaulttreemodel = (DefaultTreeModel) treetable.getValue();
//                    final ExtendMutableTreeNode currentTreenode = (ExtendMutableTreeNode) treetable.getRowData();
//                    item = (ValueItem) currentTreenode.getUserObject();
//
//                    DefaultMutableTreeNode node = TreetableAdapter.find((DefaultMutableTreeNode) defaulttreemodel.getRoot(), item);
//
//                    if (!rowId.isEmpty()) {
//                        if (rowToAdd) {
//                            /* Add a new node */
//                            if (node == null) {
//                                LOGGER.severe("Node with this item : " + getIdWithUnderscores(item.getPath().getId()) + "haven't be found !");
//                            } else {
//
//                                final ValueItem item2 = new ValueItem(item);
//                                item2.setOrdinal(item2.getOrdinal() + 1);
//                                final DefaultMutableTreeNode clone = TreetableAdapter.copy(node);
//                                clone.setUserObject(item2);
//
//                                final DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
//                                parent.insert(clone, parent.getIndex(node) + 1);
//
//
//                                DefaultTreeModel treemodelOrdered = TreetableAdapter.reOrder(defaulttreemodel);
//                                ValueExpression valueExpr = treetable.getValueExpression("value");
//                                if (valueExpr != null && valueExpr.getExpressionString().contains("#")) {
//                                    valueExpr.setValue(context.getELContext(), treemodelOrdered);
//                                    treetable.setValueExpression("value", valueExpr);
//                                }
//                                treetable.setValue(treemodelOrdered);
//                            }
//                        } else {
//                            /* Delete a specific node */
//                            if (node == null) {
//                                LOGGER.severe("Node with this item : " + getIdWithUnderscores(item.getPath().getId()) + "haven't be found !");
//                            } else {
//                                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
//                                parent.remove(parent.getIndex(node));
//
//                                DefaultTreeModel treemodelOrdered = TreetableAdapter.reOrder(defaulttreemodel);
//                                ValueExpression valueExpr = treetable.getValueExpression("value");
//                                if (valueExpr != null && valueExpr.getExpressionString().contains("#")) {
//                                    valueExpr.setValue(context.getELContext(), treemodelOrdered);
//                                    treetable.setValueExpression("value", valueExpr);
//                                }
//                                treetable.setValue(treemodelOrdered);
//                            }
//                        }
//
//                    }
//                }
//            }
//        }

    }

    /**
     *
     * @param context
     * @param component
     * @param item
     */
    private void renderValue(FacesContext context, UIComponentSelector component, String type, String id) {
        final boolean mandatory = component.isMandatory();
        if ("text".equals(type) || "web".equals(type) || "mail".equals(type)) {
            renderText(context, component, id, mandatory, type);
        } else if ("textarea".equals(type)) {
            renderTextArea(context, component, id, mandatory);
        } else if ("readonly".equals(type)) {
            renderReadOnly(context, component, id);
        } else if ("select".equals(type)) {
           renderSelect(context, component, id, mandatory);
        } else if ("date".equals(type)) {
            renderDatePicker(context, component, id, mandatory);
        }
//        else {
//            LOGGER.severe("this fieldType is not recognize : " + fieldType + " for " + item.getIdValue());
//        }
//
//        if (profileElement.getMaximumOccurence() > 1) {
//            renderMoreButton(context, component, item);
//            renderMinusButton(component, item);
//        }

    }

    /**
     *
     * @param component
     * @param item
     * @param profileElement
     * @param obligation
     */
    private void renderText(FacesContext context, UIComponentSelector component, String id, boolean mandatory, String type) {
        final UIComponent input = FacesUtils.findComponentById(context, component, getIdWithUnderscores(id) + "_text");
        final HtmlInputText inputText;

        if (input instanceof HtmlInputText) {
            inputText = (HtmlInputText) input;
        } else {
            /* boolean addNewChild = false;
            if (mdInput instanceof HtmlInputText) {
                inputText = (HtmlInputText) mdInput;
            } else { */
            inputText = new HtmlInputText();
            inputText.setId(getIdWithUnderscores(id) + "_text");
            // MetadataFieldControlRenderer.renderHtmlInputTextControls(inputText, profileElement);
            //addNewChild = true;
            // createValueExpr(context, component, itvalueem, inputText, mandatory, false);
            inputText.setStyleClass(this.returnStyle(mandatory));
            if ("mail".equals(type)) {
                inputText.setOnchange("COMPONENTUTIL.emailcheck(this);");
                inputText.setOnkeyup("COMPONENTUTIL.emailcheck(this);");
            }
            // if (addNewChild)
            component.getChildren().add(inputText);
        }
        createValueExpr(context, component, inputText, false);
    }

    /**
     *
     * @param component
     * @param item
     * @param profileElement
     * @param obligation
     */
    private void renderTextArea(FacesContext context, UIComponentSelector component, String id, boolean mandatory) {
        final UIComponent input = FacesUtils.findComponentById(context, component, getIdWithUnderscores(id) + "_text");
        final HtmlInputText maxCarText;
        final HtmlInputTextarea inputTextArea;

        final Integer maxCar;
        if (component.getMaxCar() != null) {
            maxCar = component.getMaxCar();
        } else {
            maxCar = DEFAULT_MAX_CAR;
        }
        boolean setValue = true;
        if (input instanceof HtmlInputText) {
            maxCarText = (HtmlInputText) input;
            final UIComponent inputValue = FacesUtils.findComponentById(context, maxCarText, getIdWithUnderscores(id) + "_textArea");
            if (inputValue instanceof HtmlInputTextarea) {
                inputTextArea = (HtmlInputTextarea) inputValue;
            } else {
                inputTextArea = null;
                setValue = false;
            }
        } else {
            inputTextArea = new HtmlInputTextarea();
            inputTextArea.setId(getIdWithUnderscores(id) + "_textArea");

            maxCarText = new HtmlInputText();
            maxCarText.setId(getIdWithUnderscores(id) + "_text");
            maxCarText.setReadonly(true);
            maxCarText.setStyleClass(DEFAULT_COMPONENT_CLASS + " " + CHAR_CONTROL_CLASS);
            maxCarText.getChildren().add(inputTextArea);
            component.getChildren().add(maxCarText);
            inputTextArea.setStyleClass(this.returnStyle(mandatory));

            inputTextArea.setOnchange("COMPONENTUTIL.maxcharcheck(this,$('" + maxCarText.getClientId(context) + "')," + maxCar + ");");
            inputTextArea.setOnkeyup("COMPONENTUTIL.maxcharcheck(this,$('" + maxCarText.getClientId(context) + "')," + maxCar + ");");

            component.getChildren().add(maxCarText);
        }
        
        int maxCarOnLoad = maxCar;
        if (setValue) {
            createValueExpr(context, component, inputTextArea, false);
            if (inputTextArea.getValue().toString().length() <= maxCar) maxCarOnLoad = maxCar - inputTextArea.getValue().toString().length();
        }

        maxCarText.setValue(maxCarOnLoad + " car.");
    }

    private void renderDatePicker(FacesContext context, UIComponentSelector component, String id, boolean mandatory) {
        final UIComponent mdInput = FacesUtils.findComponentById(context, component, getIdWithUnderscores(id) + "_date");
        if (context.getExternalContext().getRequestMap().containsKey("ajaxflag.DatePickerjs")) {
            context.getExternalContext().getRequestMap().remove("ajaxflag.DatePickerjs");
        }
        final UITemporal datepicker;
        if (mdInput instanceof UITemporal) {
            datepicker = (UITemporal) mdInput;
        } else {
            datepicker = new UITemporal();
            datepicker.setId(getIdWithUnderscores(id) + "_date");
            datepicker.setLoadCss(true);
            datepicker.setLoadJs(true);
            datepicker.setLoadMootools(true);
            datepicker.setStyleClass((mandatory) ? MANDATORY_FIELD_CLASS + " " + DEFAULT_COMPONENT_CLASS : DEFAULT_COMPONENT_CLASS);
            component.getChildren().add(datepicker);
        }
        createValueExpr(context, component, datepicker, true);
    }



    /**
     * It renders a ReadOnly output with the Value attribute.
     * @param component
     * @param item
     * @param obligation
     */
    private void renderReadOnly(FacesContext context, UIComponentSelector component, String id) {
        final UIComponent mdInput = FacesUtils.findComponentById(context, component, getIdWithUnderscores(id) + "_output");
        if ((mdInput == null) && (component.getValue() != null)) {
            final HtmlOutputText outputText = new HtmlOutputText();
            outputText.setId(getIdWithUnderscores(id) + "_output");
            outputText.setValue(component.getValue());
            component.getChildren().add(outputText);
        }
    }

    /**
     * This method build a Select component with a Map. The Value attribute defines
     * the selected key of the select.
     * @param component
     * @param item
     * @param profileElement
     * @param obligation
     */
    private void renderSelect(FacesContext context, UIComponentSelector component, String id, boolean mandatory) {
        final UIComponent mdInput = FacesUtils.findComponentById(context, component, getIdWithUnderscores(id) + "_select");
        final HtmlSelectOneMenu selectOneMenu;
        if (mdInput instanceof HtmlSelectOneMenu) {
            selectOneMenu = (HtmlSelectOneMenu) mdInput;
            // selectOneMenu.getChildren().clear();
        } else {
            selectOneMenu = new HtmlSelectOneMenu();
            selectOneMenu.setId(getIdWithUnderscores(id) + "_select");

            if (component.getSelectMap() != null) {
                final Map<Object, String> selectMap = component.getSelectMap();
                for (Map.Entry<Object, String> e : selectMap.entrySet()) {
                    final UISelectItem selectItem = new UISelectItem();
                    selectItem.setItemLabel(e.getValue());
                    selectItem.setItemValue(e.getKey());
                    selectOneMenu.getChildren().add(selectItem);
                }

                selectOneMenu.setStyleClass(this.returnStyle(mandatory));
                component.getChildren().add(selectOneMenu);
            }
        }
        
        createValueExpr(context, component, selectOneMenu, false);
    }

//    /**
//     *
//     * @param component
//     * @param item
//     */
//    private void renderMoreButton(FacesContext context, UIComponent component, ValueItem item) {
//        final UIComponent mdInput = FacesUtils.findComponentById(context, component, getIdWithUnderscores(item.getPath().getId() + "_more"));
//        if (mdInput == null) {
//            final HtmlOutputLink linkMore;
//            final HtmlGraphicImage moreOccurence = new HtmlGraphicImage();
//            linkMore = new HtmlOutputLink();
//            final UIMetadataInput mdi = (UIMetadataInput) component;
//            final UITreeColumn treecolum = (UITreeColumn) mdi.getParent();
//            final UITreeTable treetable = (UITreeTable) treecolum.getParent();
//
//            final String formId = FacesUtils.getParentComponentIdByClass(component, UIForm.class);
//            final String treetableId = treetable.getId();
//            final String actionParamIDId = component.getId() + "_actionParamID";
//            final String actionParamADDId = component.getId() + "_actionParamADD";
//            final int index = treetable.getRowIndex();
//            final int actionParamIDValue = index;
//            final boolean actionParamADDValue = true;
//
//            final StringBuilder a4jRequest = new StringBuilder();
//            linkMore.setId(getIdWithUnderscores(item.getPath().getId()) + "_more");
//            a4jRequest.append("A4J.AJAX.Submit('").append(formId).append(":metadataRegion','").append(formId).append("',null,{'single':true,'parameters':{'").append(formId).append(":a4jsupport':'").append(formId).append(":a4jsupport',");
//            a4jRequest.append("'").append(formId).append(":").append(treetableId).append(":").append(index).append(":").append(actionParamIDId).append("':").append(actionParamIDValue).append(",");
//            a4jRequest.append("'").append(formId).append(":").append(treetableId).append(":").append(index).append(":").append(actionParamADDId).append("':'").append(actionParamADDValue).append("',");
//            a4jRequest.append("'refresh':'").append(formId).append(":").append(treetableId).append("'} ,");
//            a4jRequest.append("'actionUrl':window.location.href} ); return false;");
//
//            linkMore.setOnclick(a4jRequest.toString());
//
//            moreOccurence.setValue("/resource.jsf?r=/org/mapfaces/resources/tree/images/default/tree/elbow-plus-nl.gif");
//            moreOccurence.setStyle("position:absolute; left: 94%;");
//
//            linkMore.getChildren().add(moreOccurence);
//            component.getChildren().add(linkMore);
//        }
//    }
//
//    /**
//     *
//     * @param component
//     * @param item
//     */
//    private void renderMinusButton(UIComponent component, ValueItem item) {
//
//        final HtmlGraphicImage minusOccurence = new HtmlGraphicImage();
//        final HtmlOutputLink linkMinus = new HtmlOutputLink();
//        final UIMetadataInput mdi = (UIMetadataInput) component;
//        final UITreeColumn treecolum = (UITreeColumn) mdi.getParent();
//        final UITreeTable treetable = (UITreeTable) treecolum.getParent();
//
//        final String formId = FacesUtils.getParentComponentIdByClass(component, UIForm.class);
//        final String treetableId = treetable.getId();
//        final String actionParamIDId = component.getId() + "_actionParamID";
//        final String actionParamADDId = component.getId() + "_actionParamADD";
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
//            component.getChildren().add(linkMinus);
//        }
//
//    }

    private String returnStyle(boolean mandatory) {
        return (mandatory) ? MANDATORY_FIELD_CLASS + " " + DEFAULT_COMPONENT_CLASS : DEFAULT_COMPONENT_CLASS;
    }

    private void createValueExpr(FacesContext context, UIComponentSelector component, UIInput htmlComp, boolean isDatePicker) {
        final ValueExpression ve = component.getValueExpression("value");
        if (ve != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                System.out.println("VALUE IN DA RENDERER => " + ve.getValue(context.getELContext()));
                if (!isDatePicker) {
                    htmlComp.setValue(ve.getValue(context.getELContext()));
                }
                htmlComp.setValueExpression("value", ve);
            }
        }
    }

    private String getIdWithUnderscores(String id) {
        id = id.replace(':', '_');
        id = id.replace(' ', '_');
        return id;
    }

//    private boolean isDate(ProfileElement profileElement) {
//        boolean isDate = false;
//        List<FieldControl> controls = profileElement.getField().getControls();
//        int i = 0;
//        while ((i < controls.size()) && !isDate) {
//            if (controls.get(i).getNameControl().equals("verifDate")) {
//                isDate = true;
//            }
//            i++;
//        }
//        return isDate;
//    }
//
//    private ProfileElement getProfileElement(ValueItem item) {
//        final Profile profile = item.getForm().getProfile();
//        final ProfileElement profileElement = profile.getProfileElementByPath(item.getPath());
//        return profileElement;
//    }
//
//    private UIComponent getCurrentChild(UIComponent component) {
//        final UIMetadataInput mdi = (UIMetadataInput) component;
//        UIComponent currentChild = null;
//        if (mdi.getValue() instanceof ValueItem) {
//            ValueItem item = (ValueItem) mdi.getValue();
//            currentChild = getChildWithId(component, getIdWithUnderscores(item.getPath().getId()));
//
//        }
//        return currentChild;
//    }

    private UIComponent getChildWithId(UIComponent component, String id) {
        int i = 0;
        boolean find = false;
        List<UIComponent> children = component.getChildren();
        while ((i < children.size()) && !find) {
            if (getIdWithUnderscores(id).equals(children.get(i).getId())) {
                return children.get(i);
            }
            i++;
        }
        return null;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}

