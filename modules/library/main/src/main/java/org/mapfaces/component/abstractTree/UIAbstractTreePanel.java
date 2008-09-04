package org.mapfaces.component.abstractTree;

import java.io.Serializable;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

/**
 *
 * @author kdelfour
 */
public abstract class UIAbstractTreePanel extends UIOutput implements Serializable {

    private boolean init;
    private boolean TREEPANEL_EXPAND_ALL = true;

    // =========== ATTRIBUTES ================================================== //
    private String border;
    private boolean check;
    private boolean collapsible;
    private boolean frame;
    private boolean header;
    private String height;
    private boolean rowId;
    private boolean showLines;
    private String title;
    private boolean debug;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    public boolean isFrame() {
        return frame;
    }

    public void setFrame(boolean frame) {
        this.frame = frame;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isRowId() {
        return rowId;
    }

    public void setRowId(boolean rowId) {
        this.rowId = rowId;
    }

    public boolean isShowLines() {
        return showLines;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the debug
     */
    public boolean getDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    // =========== FONCTIONS ======================================== //

    public boolean isTREEPANEL_EXPAND_ALL() {
        return TREEPANEL_EXPAND_ALL;
    }

    public void setTREEPANEL_EXPAND_ALL(boolean TREEPANEL_EXPAND_ALL) {
        this.TREEPANEL_EXPAND_ALL = TREEPANEL_EXPAND_ALL;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[12];
        values[0] = super.saveState(context);
        values[1] = getBorder();
        values[2] = isCheck();
        values[3] = isCollapsible();
        values[4] = isFrame();
        values[5] = isHeader();
        values[6] = getHeight();
        values[7] = isRowId();
        values[8] = isShowLines();
        values[9] = getTitle();
        values[10] = isInit();
        values[11] = getDebug();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setBorder((String) values[1]);
        setCheck((boolean) (Boolean) values[2]);
        setCollapsible((boolean) (Boolean) values[3]);
        setFrame((boolean) (Boolean) values[4]);
        setHeader((boolean) (Boolean) values[5]);
        setHeight((String) values[6]);
        setRowId((boolean) (Boolean) values[7]);
        setShowLines((boolean) (Boolean) values[8]);
        setTitle((String) values[9]);
        init = (Boolean) values[10];
    }

    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}
