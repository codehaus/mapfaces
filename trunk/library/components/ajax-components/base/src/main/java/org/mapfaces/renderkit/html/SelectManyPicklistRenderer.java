/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectMany;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.render.Renderer;
import org.apache.commons.collections.CollectionUtils;
import org.mapfaces.component.UISelectManyPicklist;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.util.picklist.PickListArrayUtils;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.RendererUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.share.utils.SelectItemsIterator;

/**
 *@JSFRenderer
 *   renderKitId = "HTML"
 *   family = "org.mapfaces.SelectManyPicklist"
 *   type = "org.mapfaces.renderkit.html.SelectManyPicklist"
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.3
 */
public class SelectManyPicklistRenderer extends Renderer {

    /*
     *@TODO add static elements attributes in org.mapfaces.share.utils.RendererUtils.HTML
     * instead of this renderer class
     */
    private static String INPUT_TYPE_BUTTON = "button";
    private static String BR_ELEM = "br";
    private static String DATAFLD_ATTR = "datafld";
    private static String DATASRC_ATTR = "datasrc";
    private static String DATAFORMATAS_ATTR = "dataformatas";
    // Common event handler attributes
    private static String ONCLICK_ATTR = "onclick";
    private static String ONDBLCLICK_ATTR = "ondblclick";
    private static String ONMOUSEDOWN_ATTR = "onmousedown";
    private static String ONMOUSEUP_ATTR = "onmouseup";
    private static String ONMOUSEOVER_ATTR = "onmouseover";
    private static String ONMOUSEMOVE_ATTR = "onmousemove";
    private static String ONMOUSEOUT_ATTR = "onmouseout";
    private static String ONKEYPRESS_ATTR = "onkeypress";
    private static String ONKEYDOWN_ATTR = "onkeydown";
    private static String ONKEYUP_ATTR = "onkeyup";

    // Input field event handler attributes
    private static String ONFOCUS_ATTR = "onfocus";
    private static String ONBLUR_ATTR = "onblur";
    private static String ONSELECT_ATTR = "onselect";
    private static String ONCHANGE_ATTR = "onchange";

    // universal attributes
    private static String DIR_ATTR = "dir";
    private static String LANG_ATTR = "lang";
    private static String STYLE_ATTR = "style";
    private static String TITLE_ATTR = "title";
    private static String STYLE_CLASS_ATTR = "styleClass"; //"class" cannot be used as property name
    private static String ACCESSKEY_ATTR = "accesskey";
    private static String TABINDEX_ATTR = "tabindex";
    private static String OPTGROUP_ELEM = "optgroup";
    private static String LABEL_ATTR = "label";
    private static String OPTION_ELEM = "option";
    private static String SELECTED_ATTR = "selected";
    private static final char TABULATOR = '\t';
    private static String[] SELECT_ATTRIBUTES = {
        DATAFLD_ATTR,
        DATASRC_ATTR,
        DATAFORMATAS_ATTR,};
    private static String[] EVENT_HANDLER_ATTRIBUTES_WITHOUT_ONCLICK = {
        ONDBLCLICK_ATTR,
        ONMOUSEDOWN_ATTR,
        ONMOUSEUP_ATTR,
        ONMOUSEOVER_ATTR,
        ONMOUSEMOVE_ATTR,
        ONMOUSEOUT_ATTR,
        ONKEYPRESS_ATTR,
        ONKEYDOWN_ATTR,
        ONKEYUP_ATTR
    };
    private static String[] EVENT_HANDLER_ATTRIBUTES =
            (String[]) PickListArrayUtils.concat(
            EVENT_HANDLER_ATTRIBUTES_WITHOUT_ONCLICK,
            new String[]{ONCLICK_ATTR});
    private static String[] UNIVERSAL_ATTRIBUTES_WITHOUT_STYLE = {
        DIR_ATTR,
        LANG_ATTR,
        TITLE_ATTR,
    };
    private static String[] UNIVERSAL_ATTRIBUTES =
            (String[]) PickListArrayUtils.concat(
            UNIVERSAL_ATTRIBUTES_WITHOUT_STYLE,
            new String[]{STYLE_ATTR, STYLE_CLASS_ATTR});
    private static String[] COMMON_PASSTROUGH_ATTRIBUTES =
            (String[]) PickListArrayUtils.concat(
            EVENT_HANDLER_ATTRIBUTES,
            UNIVERSAL_ATTRIBUTES);
    private static String[] COMMON_FIELD_ATTRIBUTES_WITHOUT_DISABLED = {
        ACCESSKEY_ATTR,
        TABINDEX_ATTR
    };
    private static String[] COMMON_FIELD_EVENT_ATTRIBUTES = {
        ONFOCUS_ATTR,
        ONBLUR_ATTR,
        ONSELECT_ATTR,
        ONCHANGE_ATTR
    };
    private static String[] COMMON_FIELD_PASSTROUGH_ATTRIBUTES_WITHOUT_DISABLED =
            (String[]) PickListArrayUtils.concat(
            COMMON_PASSTROUGH_ATTRIBUTES,
            COMMON_FIELD_ATTRIBUTES_WITHOUT_DISABLED,
            COMMON_FIELD_EVENT_ATTRIBUTES);
    private static String[] SELECT_PASSTHROUGH_ATTRIBUTES_WITHOUT_DISABLED =
            (String[]) PickListArrayUtils.concat(
            SELECT_ATTRIBUTES,
            COMMON_FIELD_PASSTROUGH_ATTRIBUTES_WITHOUT_DISABLED);
    private static final String FUNCTION_ADD_TO_SELECTED = "mapfaces_picklist_addToSelected";
    private static final String FUNCTION_ADD_ALL_TO_SELECTED = "mapfaces_picklist_addAllToSelected";
    private static final String FUNCTION_REMOVE_FROM_SELECTED = "mapfaces_picklist_removeFromSelected";
    private static final String FUNCTION_REMOVE_ALL_FROM_SELECTED = "mapfaces_picklist_removeAllFromSelected";
    private static final String AVAILABLE_SUFFIX = "_AVAILABLE";
    private static final String SELECTED_SUFFIX = "_SELECTED";
    private static final String HIDDEN_SUFFIX = "_HIDDEN";
    private static final String PICKLIST_JS = "/org/mapfaces/resources/picklist/picklist.js";

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        FacesUtils.assertValid(context, component);
        final UISelectManyPicklist comp = (UISelectManyPicklist) component;
        // suppress rendering if "rendered" property on the component is false.
        if (!comp.isRendered()) {
            return;
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final UISelectManyPicklist picklist = (UISelectManyPicklist) component;
        final ResponseWriter writer = context.getResponseWriter();

        String addButtonText = picklist.getAddButtonText();
        String addAllButtonText = picklist.getAddAllButtonText();
        String removeButtonText = picklist.getRemoveButtonText();
        String removeAllButtonText = picklist.getRemoveAllButtonText();
        String addButtonStyle = picklist.getAddButtonStyle();
        String addAllButtonStyle = picklist.getAddAllButtonStyle();
        String removeButtonStyle = picklist.getRemoveButtonStyle();
        String removeAllButtonStyle = picklist.getRemoveAllButtonStyle();
        String addButtonStyleClass = picklist.getAddButtonStyleClass();
        String addAllButtonStyleClass = picklist.getAddAllButtonStyleClass();
        String removeButtonStyleClass = picklist.getRemoveButtonStyleClass();
        String removeAllButtonStyleClass = picklist.getRemoveAllButtonStyleClass();

        //Set the default values for addButtonText and removeButtonText
        if (addButtonText == null || addButtonText.length() == 0) {
            addButtonText = ">";
        }

        if (addAllButtonText == null || addAllButtonText.length() == 0) {
            addAllButtonText = ">>";
        }

        if (removeButtonText == null || removeButtonText.length() == 0) {
            removeButtonText = "<";
        }

        if (removeAllButtonText == null || removeAllButtonText.length() == 0) {
            removeAllButtonText = "<<";
        }

        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.Picklistjs")) {
            extContext.getRequestMap().put("ajaxflag.Picklistjs", Boolean.TRUE);
            writeHeaders(context, component);
        }

        String availableListClientId = component.getClientId(context) + AVAILABLE_SUFFIX;
        String selectedListClientId = component.getClientId(context) + SELECTED_SUFFIX;
        String hiddenFieldCliendId = component.getClientId(context) + HIDDEN_SUFFIX;

        List selectItemList = getSelectItemList((UISelectMany) component);

        Set lookupSet = getSubmittedOrSelectedValuesAsSet(true, component, context);

        List selectItemsForSelectedValues = selectItemsForSelectedList(
                context, component, selectItemList, lookupSet);
        List selectItemsForAvailableList = selectItemsForAvailableList(
                context, component, selectItemList,
                selectItemsForSelectedValues);


        writer.startElement("table", component);
        writer.startElement(HTML.TR_ELEMENT, component);
        writer.startElement(HTML.td_ELEM, component);

        boolean disabled = (picklist.isReadonly() || picklist.isDisabled());
        encodeSelect(context, picklist, availableListClientId, disabled, picklist.getSize(),
                selectItemsForAvailableList);

        writer.endElement(HTML.td_ELEM);

        // encode buttons
        writer.startElement(HTML.td_ELEM, component);

        String javascriptAddToSelected = FUNCTION_ADD_TO_SELECTED + "('" + availableListClientId + "','" + selectedListClientId + "','" + hiddenFieldCliendId + "')";

        String javascriptAddAllToSelected = FUNCTION_ADD_ALL_TO_SELECTED + "('" + availableListClientId + "','" + selectedListClientId + "','" + hiddenFieldCliendId + "')";

        String javascriptRemoveFromSelected = FUNCTION_REMOVE_FROM_SELECTED + "('" + availableListClientId + "','" + selectedListClientId + "','" + hiddenFieldCliendId + "')";

        String javascriptRemoveAllFromSelected = FUNCTION_REMOVE_ALL_FROM_SELECTED + "('" + availableListClientId + "','" + selectedListClientId + "','" + hiddenFieldCliendId + "')";

        // encode (add selected) button.
        encodeSwapButton(context, component, javascriptAddToSelected,
                addButtonText, addButtonStyle, addButtonStyleClass);

        writer.startElement(BR_ELEM, component);
        writer.endElement(BR_ELEM);

        // encode (add all) button.
        encodeSwapButton(context, component, javascriptAddAllToSelected,
                addAllButtonText, addAllButtonStyle, addAllButtonStyleClass);

        writer.startElement(BR_ELEM, component);
        writer.endElement(BR_ELEM);

        // encode (remove selected) button.
        encodeSwapButton(context, component, javascriptRemoveFromSelected,
                removeButtonText, removeButtonStyle, removeButtonStyleClass);

        writer.startElement(BR_ELEM, component);
        writer.endElement(BR_ELEM);

        // encode (remove all) button.
        encodeSwapButton(context, component, javascriptRemoveAllFromSelected,
                removeAllButtonText, removeAllButtonStyle, removeAllButtonStyleClass);

        writer.endElement(HTML.td_ELEM);

        // encode selected list
        writer.startElement(HTML.td_ELEM, component);

        encodeSelect(context, picklist, selectedListClientId, disabled, picklist.getSize(),
                selectItemsForSelectedValues);

        // hidden field with the selected values
        encodeHiddenField(context, component, hiddenFieldCliendId,
                lookupSet);

        writer.endElement(HTML.td_ELEM);
        writer.endElement(HTML.TR_ELEMENT);
        writer.endElement("table");

        writer.flush();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        FacesUtils.assertValid(context, component);
        final UISelectManyPicklist comp = (UISelectManyPicklist) component;
        if (!(component instanceof EditableValueHolder)) {
            throw new IllegalArgumentException("Component " + component.getClientId(context) + " is not an EditableValueHolder");
        }

        String hiddenClientId = component.getClientId(context) + HIDDEN_SUFFIX;

        Map paramValuesMap = context.getExternalContext().getRequestParameterValuesMap();

        if (comp.isReadonly() || comp.isDisabled()) {
            return;
        }

        if (paramValuesMap.containsKey(hiddenClientId)) {
            String[] valuesInline = (String[]) paramValuesMap.get(hiddenClientId);

            if (valuesInline[0].trim().equals("")) {
                ((EditableValueHolder) component).setSubmittedValue(new String[]{});
                Object value = comp.getValue();

                ValueExpression ve = comp.getValueExpression("value");
                if (ve == null && value instanceof String) {
                    final ExpressionFactory ef = context.getApplication().getExpressionFactory();
                    ve = ef.createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                }

                if (ve != null) {
                    ve.setValue(context.getELContext(), new String[]{});
                    comp.setValueExpression("value", ve);
                }
            } else {
                String[] reqValues = valuesInline[0].split(",");
                ((EditableValueHolder) component).setSubmittedValue(reqValues);
                Object value = comp.getValue();
                ValueExpression ve = comp.getValueExpression("value");
                if (ve == null && value instanceof String) {
                    final ExpressionFactory ef = context.getApplication().getExpressionFactory();
                    ve = ef.createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                }

                if (ve != null) {
                    ve.setValue(context.getELContext(), reqValues);
                    comp.setValueExpression("value", ve);
                }
            }
        } else {
            /* request parameter not found, nothing to decode - set submitted value to an empty array
            as we should get here only if the component is on a submitted form, is rendered
            and if the component is not readonly or has not been disabled.

            So in fact, there must be component value at this location, but for listboxes, comboboxes etc.
            the submitted value is not posted if no item is selected. */
            ((EditableValueHolder) component).setSubmittedValue(new String[]{});
            ValueExpression ve = comp.getValueExpression("value");
            Object value = comp.getValue();
            if (ve == null && value instanceof String) {
                final ExpressionFactory ef = context.getApplication().getExpressionFactory();
                ve = ef.createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
            }
            if (ve != null) {
                ve.setValue(context.getELContext(), new String[]{});
                comp.setValueExpression("value", ve);
            }

        }
    }

    /**
     * Write headers css and js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UISelectManyPicklist comp = (UISelectManyPicklist) component;

        if (comp.isLoadJs()) {
            writer.startElement(HTML.SCRIPT_ELEM, comp);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, PICKLIST_JS, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }
    }

    private void encodeSelect(FacesContext facesContext,
            UIComponent uiComponent, String clientId, boolean disabled,
            int size, List selectItemsToDisplay)
            throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        writer.startElement("select", uiComponent);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, "id");
        writer.writeAttribute(HTML.NAME_ATTRIBUTE, clientId, null);

        writer.writeAttribute("multiple", "true", null);

        if (size == 0) {
            //No size given (Listbox) --> size is number of select items
            writer.writeAttribute(HTML.size_ATTRIBUTE, Integer.toString(selectItemsToDisplay.size()), null);
        } else {
            writer.writeAttribute(HTML.size_ATTRIBUTE, Integer.toString(size), null);
        }
        renderHTMLAttributes(writer, uiComponent,
                SELECT_PASSTHROUGH_ATTRIBUTES_WITHOUT_DISABLED);
        if (disabled) {
            writer.writeAttribute(HTML.DISABLED_ATTR, Boolean.TRUE, null);
        }

        renderSelectOptions(facesContext, uiComponent, Collections.EMPTY_SET, selectItemsToDisplay);

        writer.writeText("", null);
        writer.endElement("select");
    }

    private void encodeSwapButton(FacesContext facesContext,
            UIComponent uiComponent, String javaScriptFunction,
            String text, String style, String styleClass)
            throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        writer.startElement(HTML.INPUT_ELEM, uiComponent);
        writer.writeAttribute(HTML.style_ATTRIBUTE, style, null);
        writer.writeAttribute(HTML.class_ATTRIBUTE, styleClass, null);
        writer.writeAttribute(HTML.TYPE_ATTR, INPUT_TYPE_BUTTON, "type");
        writer.writeAttribute(HTML.onclick_ATTRIBUTE, javaScriptFunction, null);
        writer.writeAttribute(HTML.value_ATTRIBUTE, text, null);
        writer.endElement(HTML.INPUT_ELEM);
    }

    private void encodeHiddenField(FacesContext facesContext,
            UIComponent uiComponent, String hiddenFieldCliendId, Set lookupSet)
            throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        writer.startElement(HTML.INPUT_ELEM, uiComponent);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.INPUT_TYPE_HIDDEN,
                HTML.TYPE_ATTR);
        writer.writeAttribute(HTML.id_ATTRIBUTE, hiddenFieldCliendId,
                HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.NAME_ATTRIBUTE, hiddenFieldCliendId, null);

        StringBuffer sb = new StringBuffer();
        int n = 0;
        for (Iterator i = lookupSet.iterator(); i.hasNext();) {
            if (n > 0) {
                sb.append(",");
            }
            String value = (String) i.next();
            sb.append(value);
            n++;
        }

        writer.writeAttribute(HTML.value_ATTRIBUTE, sb.toString(), null);
        writer.endElement(HTML.INPUT_ELEM);

    }

    /**
     * @return true, if an attribute was written
     * @throws java.io.IOException
     */
    protected boolean renderHTMLAttributes(ResponseWriter writer,
            UIComponent component, String[] attributes) throws IOException {
        boolean somethingDone = false;
        for (int i = 0, len = attributes.length; i < len; i++) {
            String attrName = attributes[i];
            if (renderHTMLAttribute(writer, component, attrName, attrName)) {
                somethingDone = true;
            }
        }
        return somethingDone;
    }

    protected boolean renderHTMLAttribute(ResponseWriter writer,
            UIComponent component, String componentProperty, String htmlAttrName)
            throws IOException {
        Object value = component.getAttributes().get(componentProperty);
        return renderHTMLAttribute(writer, componentProperty, htmlAttrName,
                value);
    }

    protected boolean renderHTMLAttribute(ResponseWriter writer,
            String componentProperty, String attrName, Object value)
            throws IOException {
        if (!isDefaultAttributeValue(value)) {
            // render JSF "styleClass" and "itemStyleClass" attributes as "class"
            String htmlAttrName =
                    attrName.equals(HTML.STYLE_CLASS_ATTR) ? HTML.class_ATTRIBUTE : attrName;
            writer.writeAttribute(htmlAttrName, value, componentProperty);
            return true;
        }

        return false;
    }

    public static boolean isDefaultAttributeValue(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof Boolean) {
            return !((Boolean) value).booleanValue();
        } else if (value instanceof Number) {
            if (value instanceof Integer) {
                return ((Number) value).intValue() == Integer.MIN_VALUE;
            } else if (value instanceof Double) {
                return ((Number) value).doubleValue() == Double.MIN_VALUE;
            } else if (value instanceof Long) {
                return ((Number) value).longValue() == Long.MIN_VALUE;
            } else if (value instanceof Byte) {
                return ((Number) value).byteValue() == Byte.MIN_VALUE;
            } else if (value instanceof Float) {
                return ((Number) value).floatValue() == Float.MIN_VALUE;
            } else if (value instanceof Short) {
                return ((Number) value).shortValue() == Short.MIN_VALUE;
            }
        }
        return false;
    }

    private List getSelectItemList(UIComponent uiComponent) {

        List list = new ArrayList();

        for (Iterator iter = new SelectItemsIterator(uiComponent); iter.hasNext();) {
            list.add(iter.next());
        }
        return list;
    }

    private List selectItemsForSelectedList(FacesContext facesContext,
            UIComponent uiComponent, List selectItemList, Set lookupSet) {
        List selectItemForSelectedValues = new ArrayList(lookupSet.size());

        for (Iterator i = selectItemList.iterator(); i.hasNext();) {
            SelectItem selectItem = (SelectItem) i.next();
            String itemStrValue = getConvertedStringValue(facesContext, uiComponent, selectItem);

            for (Iterator i2 = lookupSet.iterator(); i2.hasNext();) {
                Object value = i2.next();
                if (value.equals(itemStrValue)) {
                    selectItemForSelectedValues.add(selectItem);
                }
            }
        }

        return selectItemForSelectedValues;
    }

    private List selectItemsForAvailableList(FacesContext facesContext,
            UIComponent uiComponent, List selectItemList,
            List selectItemsForSelectedList) {

        return new ArrayList(CollectionUtils.subtract(selectItemList,
                selectItemsForSelectedList));
    }

    private Set getSubmittedOrSelectedValuesAsSet(boolean selectMany, UIComponent uiComponent, FacesContext facesContext) {
        Set lookupSet = null;

        if (selectMany) {
            UISelectMany uiSelectMany = (UISelectMany) uiComponent;
            lookupSet = getSubmittedValuesAsSet(facesContext, uiComponent, uiSelectMany);
            if (lookupSet == null) {
                lookupSet = getSelectedValuesAsSet(facesContext, uiComponent, uiSelectMany);
            }
        }
        return lookupSet;
    }

    /**
     * Convenient utility method that returns the currently submitted values of
     * a UISelectMany component as a Set, of which the contains method can then be
     * easily used to determine if a select item is currently selected.
     * Calling the contains method of this Set with the renderable (String converted) item value
     * as argument returns true if this item is selected.
     *
     * @param uiSelectMany
     * @return Set containing all currently selected values
     */
    private Set getSubmittedValuesAsSet(FacesContext context, UIComponent component, UISelectMany uiSelectMany) {
        Object submittedValues = uiSelectMany.getSubmittedValue();
        if (submittedValues == null) {
            return null;
        }

        return internalSubmittedOrSelectedValuesAsSet(context, component, uiSelectMany, submittedValues);
    }

    /**
     * Convenient utility method that returns the currently selected values of
     * a UISelectMany component as a Set, of which the contains method can then be
     * easily used to determine if a value is currently selected.
     * Calling the contains method of this Set with the item value
     * as argument returns true if this item is selected.
     *
     * @param uiSelectMany
     * @return Set containing all currently selected values
     */
    private Set getSelectedValuesAsSet(FacesContext context, UIComponent component, UISelectMany uiSelectMany) {
        Object selectedValues = uiSelectMany.getValue();

        return internalSubmittedOrSelectedValuesAsSet(context, component, uiSelectMany, selectedValues);
    }

    private Set internalSubmittedOrSelectedValuesAsSet(FacesContext context,
            UIComponent component, UISelectMany uiSelectMany,
            Object values) {

        final String EMPTY_STRING = "";
        if (values == null || EMPTY_STRING.equals(values)) {
            return Collections.EMPTY_SET;
        } else if (values instanceof Object[]) {
            //Object array
            Object[] ar = (Object[]) values;
            if (ar.length == 0) {
                return Collections.EMPTY_SET;
            }

            HashSet set = new HashSet(calcCapacity(ar.length));
            for (int i = 0; i < ar.length; i++) {
                set.add(getConvertedStringValue(context, component, ar[i]));
            }
            return set;
        } else if (values.getClass().isArray()) {
            //primitive array
            int len = Array.getLength(values);
            HashSet set = new HashSet(calcCapacity(len));
            for (int i = 0; i < len; i++) {
                set.add(getConvertedStringValue(context, component, Array.get(values, i)));
            }
            return set;
        } else if (values instanceof List) {
            List lst = (List) values;
            if (lst.size() == 0) {
                return Collections.EMPTY_SET;
            } else {
                HashSet set = new HashSet(calcCapacity(lst.size()));
                for (Iterator i = lst.iterator(); i.hasNext();) {
                    set.add(getConvertedStringValue(context, component, i.next()));
                }

                return set;
            }
        } else if (values instanceof String && ((String) values).contains("#")) {
            //This case is for apache tomcat server only
            final ExpressionFactory ef = context.getApplication().getExpressionFactory();
            final ValueExpression vex = ef.createValueExpression(context.getELContext(), (String) values, java.lang.Object.class);
            Object valuesExpr = vex.getValue(context.getELContext());
            return internalSubmittedOrSelectedValuesAsSet(context, component, uiSelectMany, valuesExpr);
        } else {
            throw new IllegalArgumentException("Value of UISelectMany component with path : " + RendererUtils.getPathToComponent(uiSelectMany) + " is not of type Array or List");
        }
    }

    /**
     * Calculates initial capacity needed to hold <code>size</code> elements in
     * a HashMap or Hashtable without forcing an expensive increase in internal
     * capacity. Capacity is based on the default load factor of .75.
     * <p>
     * Usage: <code>Map map = new HashMap(HashMapUtils.calcCapacity(10));<code>
     * </p>
     * @param size the number of items that will be put into a HashMap
     * @return initial capacity needed
     */
    private final int calcCapacity(int size) {
        return ((size * 4) + 3) / 3;
    }

    /**
     * Convenient utility method that returns the currently given SelectItem value
     * as String, using the given converter.
     * Especially usefull for dealing with primitive types.
     */
    private String getConvertedStringValue(FacesContext context,
            UIComponent component, SelectItem selectItem) {
        return getConvertedStringValue(context, component, selectItem.getValue());
    }

    /**
     * Convenient utility method that returns the currently given value as String,
     * using the given converter.
     * Especially usefull for dealing with primitive types.
     */
    private String getConvertedStringValue(FacesContext context,
            UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof String) {
            return (String) value;
        } else if (RendererUtils.NOTHING.equals(value)) {
            return "";
        } else {
            throw new IllegalArgumentException(
                    "Value is no String (class=" + value.getClass().getName() + ", value=" + value + ") and component " + component.getClientId(context) + "with path: " + RendererUtils.getPathToComponent(component) + " does not have a Converter");
        }
    }

    /**
     * Renders the select options for a <code>UIComponent</code> that is
     * rendered as an HTML select element.
     *
     * @param context        the current <code>FacesContext</code>.
     * @param component      the <code>UIComponent</code> whose options need to be
     *                       rendered.
     * @param converter      <code>component</code>'s converter
     * @param lookupSet      the <code>Set</code> to use to look up selected options
     * @param selectItemList the <code>List</code> of <code>SelectItem</code> s to be
     *                       rendered as HTML option elements.
     * @throws IOException
     */
    private void renderSelectOptions(FacesContext context,
            UIComponent component, Set lookupSet,
            List selectItemList) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        for (Iterator it = selectItemList.iterator(); it.hasNext();) {
            SelectItem selectItem = (SelectItem) it.next();

            if (selectItem instanceof SelectItemGroup) {
                writer.startElement(OPTGROUP_ELEM, component);
                writer.writeAttribute(LABEL_ATTR, selectItem.getLabel(),
                        null);
                SelectItem[] selectItems = ((SelectItemGroup) selectItem).getSelectItems();
                renderSelectOptions(context, component, lookupSet,
                        Arrays.asList(selectItems));
                writer.endElement(OPTGROUP_ELEM);
            } else {
                String itemStrValue = getConvertedStringValue(context, component, selectItem);

                writer.write(TABULATOR);
                writer.startElement(OPTION_ELEM, component);
                if (itemStrValue != null) {
                    writer.writeAttribute(HTML.value_ATTRIBUTE, itemStrValue, null);
                }

                if (lookupSet.contains(itemStrValue)) {  //TODO/FIX: we always compare the String vales, better fill lookupSet with Strings only when useSubmittedValue==true, else use the real item value Objects
                    writer.writeAttribute(SELECTED_ATTR,
                            SELECTED_ATTR, null);
                }

                boolean disabled = selectItem.isDisabled();
                if (disabled) {
                    writer.writeAttribute(HTML.DISABLED_ATTR,
                            HTML.DISABLED_ATTR, null);
                }

                String labelClass;
                boolean componentDisabled = isTrue(component.getAttributes().get("disabled"));

                if (componentDisabled || disabled) {
                    labelClass = (String) component.getAttributes().get("disabledClass");
                } else {
                    labelClass = (String) component.getAttributes().get("enabledClass");
                }
                if (labelClass != null) {
                    writer.writeAttribute("class", labelClass, "labelClass");
                }

                boolean escape;

                escape = getBooleanAttribute(component, "escape",
                        true); //default is to escape
                if (escape) {
                    writer.writeText(selectItem.getLabel(), null);
                } else {
                    writer.write(selectItem.getLabel());
                }

                writer.endElement(OPTION_ELEM);
            }
        }
    }

    private boolean getBooleanAttribute(UIComponent component,
            String attrName,
            boolean defaultValue) {
        Boolean b = (Boolean) component.getAttributes().get(attrName);
        return b != null ? b.booleanValue() : defaultValue;
    }

    private static boolean isTrue(Object obj) {
        if (!(obj instanceof Boolean)) {
            return false;
        }

        return ((Boolean) obj).booleanValue();
    }
}
