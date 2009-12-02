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
package org.mapfaces.taglib;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import org.mapfaces.component.UISelectManyPicklist;

/**
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.3
 */
public class SelectManyPicklistTag extends UIComponentELTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.SelectManyPicklist";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.SelectManyPicklist";
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    /**
     * Option to see debug messages.
     */
    private ValueExpression debug = null;
    private ValueExpression addButtonText = null;
    private ValueExpression addAllButtonText = null;
    private ValueExpression removeButtonText = null;
    private ValueExpression removeAllButtonText = null;
    private ValueExpression addButtonStyle = null;
    private ValueExpression addAllButtonStyle = null;
    private ValueExpression removeButtonStyle = null;
    private ValueExpression removeAllButtonStyle = null;
    private ValueExpression addButtonStyleClass = null;
    private ValueExpression addAllButtonStyleClass = null;
    private ValueExpression removeButtonStyleClass = null;
    private ValueExpression removeAllButtonStyleClass = null;
    private ValueExpression size = null;
    private ValueExpression onchange = null;
    private ValueExpression onselect = null;
    private ValueExpression disabled = null;
    private ValueExpression readonly = null;
    private ValueExpression dir = null;
    private ValueExpression title = null;
    private ValueExpression onclick = null;
    private ValueExpression ondblclick = null;
    private ValueExpression onkeydown = null;
    private ValueExpression onkeypress = null;
    private ValueExpression onkeyup = null;
    private ValueExpression onmousedown = null;
    private ValueExpression onmousemove = null;
    private ValueExpression onmouseout = null;
    private ValueExpression onmouseover = null;
    private ValueExpression onmouseup = null;
    private ValueExpression disabledClass = null;
    private ValueExpression enabledClass = null;
    private ValueExpression onblur = null;
    private ValueExpression onfocus = null;
    private ValueExpression valueChangeListener = null;
    private ValueExpression immediate = null;
    private ValueExpression loadJs = null;
    private ValueExpression value = null;

    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        FacesContext context = FacesContext.getCurrentInstance();
        final ExpressionFactory ef = context.getApplication().getExpressionFactory();
        UISelectManyPicklist picklist = (UISelectManyPicklist) component;

        if (value != null) {
            if (value.getExpressionString() != null && value.getExpressionString().contains("#")) {
                final ValueExpression vex = ef.createValueExpression(context.getELContext(), value.getExpressionString(), java.lang.Object.class);
                picklist.setValue(vex.getValue(context.getELContext()));
            }
        }

        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("style", style);
        component.setValueExpression("debug", debug);
        component.setValueExpression("addButtonText", addButtonText);
        component.setValueExpression("addAllButtonText", addAllButtonText);
        component.setValueExpression("removeButtonText", removeButtonText);
        component.setValueExpression("removeAllButtonText", removeAllButtonText);
        component.setValueExpression("addButtonStyle", addButtonStyle);
        component.setValueExpression("addAllButtonStyle", addAllButtonStyle);
        component.setValueExpression("removeButtonStyle", removeButtonStyle);
        component.setValueExpression("removeAllButtonStyle", removeAllButtonStyle);
        component.setValueExpression("addButtonStyleClass", addButtonStyleClass);
        component.setValueExpression("addAllButtonStyleClass", addAllButtonStyleClass);
        component.setValueExpression("removeButtonStyleClass", removeButtonStyleClass);
        component.setValueExpression("removeAllButtonStyleClass", removeAllButtonStyleClass);
        component.setValueExpression("size", size);
        component.setValueExpression("onchange", onchange);
        component.setValueExpression("onselect", onselect);
        component.setValueExpression("disabled", disabled);
        component.setValueExpression("readonly", readonly);
        component.setValueExpression("dir", dir);
        component.setValueExpression("title", title);
        component.setValueExpression("onclick", onclick);
        component.setValueExpression("ondblclick", ondblclick);
        component.setValueExpression("onkeydown", onkeydown);
        component.setValueExpression("onkeypress", onkeypress);
        component.setValueExpression("onkeyup", onkeyup);
        component.setValueExpression("onmousedown", onmousedown);
        component.setValueExpression("onmousemove", onmousemove);
        component.setValueExpression("onmouseout", onmouseout);
        component.setValueExpression("onmouseover", onmouseover);
        component.setValueExpression("onmouseup", onmouseup);
        component.setValueExpression("disabledClass", disabledClass);
        component.setValueExpression("enabledClass", enabledClass);
        component.setValueExpression("onblur", onblur);
        component.setValueExpression("onfocus", onfocus);
        component.setValueExpression("valueChangeListener", valueChangeListener);
        component.setValueExpression("immediate", immediate);
        component.setValueExpression("loadJs", loadJs);
        component.setValueExpression("value", value);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        styleClass = null;
        style = null;
        debug = null;
        addButtonText = null;
        addAllButtonText = null;
        removeButtonText = null;
        removeAllButtonText = null;
        addButtonStyle = null;
        addAllButtonStyle = null;
        removeButtonStyle = null;
        removeAllButtonStyle = null;
        addButtonStyleClass = null;
        addAllButtonStyleClass = null;
        removeButtonStyleClass = null;
        removeAllButtonStyleClass = null;
        size = null;
        onchange = null;
        onselect = null;
        disabled = null;
        readonly = null;
        dir = null;
        title = null;
        onclick = null;
        ondblclick = null;
        onkeydown = null;
        onkeypress = null;
        onkeyup = null;
        onmousedown = null;
        onmousemove = null;
        onmouseout = null;
        onmouseover = null;
        onmouseup = null;
        disabledClass = null;
        enabledClass = null;
        onblur = null;
        onfocus = null;
        valueChangeListener = null;
        immediate = null;
        loadJs = null;
        value = null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    /**
     * @return the styleClass
     */
    public ValueExpression getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the style
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    /**
     * @return the debug
     */
    public ValueExpression getDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    /**
     * @return the addButtonText
     */
    public ValueExpression getAddButtonText() {
        return addButtonText;
    }

    /**
     * @param addButtonText the addButtonText to set
     */
    public void setAddButtonText(ValueExpression addButtonText) {
        this.addButtonText = addButtonText;
    }

    /**
     * @return the addAllButtonText
     */
    public ValueExpression getAddAllButtonText() {
        return addAllButtonText;
    }

    /**
     * @param addAllButtonText the addAllButtonText to set
     */
    public void setAddAllButtonText(ValueExpression addAllButtonText) {
        this.addAllButtonText = addAllButtonText;
    }

    /**
     * @return the removeButtonText
     */
    public ValueExpression getRemoveButtonText() {
        return removeButtonText;
    }

    /**
     * @param removeButtonText the removeButtonText to set
     */
    public void setRemoveButtonText(ValueExpression removeButtonText) {
        this.removeButtonText = removeButtonText;
    }

    /**
     * @return the removeAllButtonText
     */
    public ValueExpression getRemoveAllButtonText() {
        return removeAllButtonText;
    }

    /**
     * @param removeAllButtonText the removeAllButtonText to set
     */
    public void setRemoveAllButtonText(ValueExpression removeAllButtonText) {
        this.removeAllButtonText = removeAllButtonText;
    }

    /**
     * @return the addButtonStyle
     */
    public ValueExpression getAddButtonStyle() {
        return addButtonStyle;
    }

    /**
     * @param addButtonStyle the addButtonStyle to set
     */
    public void setAddButtonStyle(ValueExpression addButtonStyle) {
        this.addButtonStyle = addButtonStyle;
    }

    /**
     * @return the addAllButtonStyle
     */
    public ValueExpression getAddAllButtonStyle() {
        return addAllButtonStyle;
    }

    /**
     * @param addAllButtonStyle the addAllButtonStyle to set
     */
    public void setAddAllButtonStyle(ValueExpression addAllButtonStyle) {
        this.addAllButtonStyle = addAllButtonStyle;
    }

    /**
     * @return the removeButtonStyle
     */
    public ValueExpression getRemoveButtonStyle() {
        return removeButtonStyle;
    }

    /**
     * @param removeButtonStyle the removeButtonStyle to set
     */
    public void setRemoveButtonStyle(ValueExpression removeButtonStyle) {
        this.removeButtonStyle = removeButtonStyle;
    }

    /**
     * @return the removeAllButtonStyle
     */
    public ValueExpression getRemoveAllButtonStyle() {
        return removeAllButtonStyle;
    }

    /**
     * @param removeAllButtonStyle the removeAllButtonStyle to set
     */
    public void setRemoveAllButtonStyle(ValueExpression removeAllButtonStyle) {
        this.removeAllButtonStyle = removeAllButtonStyle;
    }

    /**
     * @return the addButtonStyleClass
     */
    public ValueExpression getAddButtonStyleClass() {
        return addButtonStyleClass;
    }

    /**
     * @param addButtonStyleClass the addButtonStyleClass to set
     */
    public void setAddButtonStyleClass(ValueExpression addButtonStyleClass) {
        this.addButtonStyleClass = addButtonStyleClass;
    }

    /**
     * @return the addAllButtonStyleClass
     */
    public ValueExpression getAddAllButtonStyleClass() {
        return addAllButtonStyleClass;
    }

    /**
     * @param addAllButtonStyleClass the addAllButtonStyleClass to set
     */
    public void setAddAllButtonStyleClass(ValueExpression addAllButtonStyleClass) {
        this.addAllButtonStyleClass = addAllButtonStyleClass;
    }

    /**
     * @return the removeButtonStyleClass
     */
    public ValueExpression getRemoveButtonStyleClass() {
        return removeButtonStyleClass;
    }

    /**
     * @param removeButtonStyleClass the removeButtonStyleClass to set
     */
    public void setRemoveButtonStyleClass(ValueExpression removeButtonStyleClass) {
        this.removeButtonStyleClass = removeButtonStyleClass;
    }

    /**
     * @return the removeAllButtonStyleClass
     */
    public ValueExpression getRemoveAllButtonStyleClass() {
        return removeAllButtonStyleClass;
    }

    /**
     * @param removeAllButtonStyleClass the removeAllButtonStyleClass to set
     */
    public void setRemoveAllButtonStyleClass(ValueExpression removeAllButtonStyleClass) {
        this.removeAllButtonStyleClass = removeAllButtonStyleClass;
    }

    /**
     * @return the size
     */
    public ValueExpression getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(ValueExpression size) {
        this.size = size;
    }

    /**
     * @return the onchange
     */
    public ValueExpression getOnchange() {
        return onchange;
    }

    /**
     * @param onchange the onchange to set
     */
    public void setOnchange(ValueExpression onchange) {
        this.onchange = onchange;
    }

    /**
     * @return the onselect
     */
    public ValueExpression getOnselect() {
        return onselect;
    }

    /**
     * @param onselect the onselect to set
     */
    public void setOnselect(ValueExpression onselect) {
        this.onselect = onselect;
    }

    /**
     * @return the disabled
     */
    public ValueExpression getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(ValueExpression disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the readonly
     */
    public ValueExpression getReadonly() {
        return readonly;
    }

    /**
     * @param readonly the readonly to set
     */
    public void setReadonly(ValueExpression readonly) {
        this.readonly = readonly;
    }

    /**
     * @return the dir
     */
    public ValueExpression getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(ValueExpression dir) {
        this.dir = dir;
    }

    /**
     * @return the title
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    /**
     * @return the onclick
     */
    public ValueExpression getOnclick() {
        return onclick;
    }

    /**
     * @param onclick the onclick to set
     */
    public void setOnclick(ValueExpression onclick) {
        this.onclick = onclick;
    }

    /**
     * @return the ondblclick
     */
    public ValueExpression getOndblclick() {
        return ondblclick;
    }

    /**
     * @param ondblclick the ondblclick to set
     */
    public void setOndblclick(ValueExpression ondblclick) {
        this.ondblclick = ondblclick;
    }

    /**
     * @return the onkeydown
     */
    public ValueExpression getOnkeydown() {
        return onkeydown;
    }

    /**
     * @param onkeydown the onkeydown to set
     */
    public void setOnkeydown(ValueExpression onkeydown) {
        this.onkeydown = onkeydown;
    }

    /**
     * @return the onkeypress
     */
    public ValueExpression getOnkeypress() {
        return onkeypress;
    }

    /**
     * @param onkeypress the onkeypress to set
     */
    public void setOnkeypress(ValueExpression onkeypress) {
        this.onkeypress = onkeypress;
    }

    /**
     * @return the onkeyup
     */
    public ValueExpression getOnkeyup() {
        return onkeyup;
    }

    /**
     * @param onkeyup the onkeyup to set
     */
    public void setOnkeyup(ValueExpression onkeyup) {
        this.onkeyup = onkeyup;
    }

    /**
     * @return the onmousedown
     */
    public ValueExpression getOnmousedown() {
        return onmousedown;
    }

    /**
     * @param onmousedown the onmousedown to set
     */
    public void setOnmousedown(ValueExpression onmousedown) {
        this.onmousedown = onmousedown;
    }

    /**
     * @return the onmousemove
     */
    public ValueExpression getOnmousemove() {
        return onmousemove;
    }

    /**
     * @param onmousemove the onmousemove to set
     */
    public void setOnmousemove(ValueExpression onmousemove) {
        this.onmousemove = onmousemove;
    }

    /**
     * @return the onmouseout
     */
    public ValueExpression getOnmouseout() {
        return onmouseout;
    }

    /**
     * @param onmouseout the onmouseout to set
     */
    public void setOnmouseout(ValueExpression onmouseout) {
        this.onmouseout = onmouseout;
    }

    /**
     * @return the onmouseover
     */
    public ValueExpression getOnmouseover() {
        return onmouseover;
    }

    /**
     * @param onmouseover the onmouseover to set
     */
    public void setOnmouseover(ValueExpression onmouseover) {
        this.onmouseover = onmouseover;
    }

    /**
     * @return the onmouseup
     */
    public ValueExpression getOnmouseup() {
        return onmouseup;
    }

    /**
     * @param onmouseup the onmouseup to set
     */
    public void setOnmouseup(ValueExpression onmouseup) {
        this.onmouseup = onmouseup;
    }

    /**
     * @return the disabledClass
     */
    public ValueExpression getDisabledClass() {
        return disabledClass;
    }

    /**
     * @param disabledClass the disabledClass to set
     */
    public void setDisabledClass(ValueExpression disabledClass) {
        this.disabledClass = disabledClass;
    }

    /**
     * @return the enabledClass
     */
    public ValueExpression getEnabledClass() {
        return enabledClass;
    }

    /**
     * @param enabledClass the enabledClass to set
     */
    public void setEnabledClass(ValueExpression enabledClass) {
        this.enabledClass = enabledClass;
    }

    /**
     * @return the onblur
     */
    public ValueExpression getOnblur() {
        return onblur;
    }

    /**
     * @param onblur the onblur to set
     */
    public void setOnblur(ValueExpression onblur) {
        this.onblur = onblur;
    }

    /**
     * @return the onfocus
     */
    public ValueExpression getOnfocus() {
        return onfocus;
    }

    /**
     * @param onfocus the onfocus to set
     */
    public void setOnfocus(ValueExpression onfocus) {
        this.onfocus = onfocus;
    }

    /**
     * @return the valueChangeListener
     */
    public ValueExpression getValueChangeListener() {
        return valueChangeListener;
    }

    /**
     * @param valueChangeListener the valueChangeListener to set
     */
    public void setValueChangeListener(ValueExpression valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }

    /**
     * @return the immediate
     */
    public ValueExpression getImmediate() {
        return immediate;
    }

    /**
     * @param immediate the immediate to set
     */
    public void setImmediate(ValueExpression immediate) {
        this.immediate = immediate;
    }

    /**
     * @return the loadJs
     */
    public ValueExpression getLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(ValueExpression loadJs) {
        this.loadJs = loadJs;
    }

    /**
     * @return the value
     */
    public ValueExpression getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ValueExpression value) {
        this.value = value;
    }
}
