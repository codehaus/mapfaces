package org.mapfaces.component.treetable;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;


import javax.faces.component.UIComponent;

import javax.faces.component.UIData;
import javax.faces.model.ListDataModel;
import javax.faces.model.ScalarDataModel;
import javax.swing.tree.DefaultTreeModel;
import org.mapfaces.model.tree.ExtendTreeModel;
import org.mapfaces.model.tree.TreeDataModel;
import org.mapfaces.share.util.tree.TreeModelAdapter;

/**
 * <p><strong>UITreeData</strong> is a {@link UIComponent} that supports data
 * binding to a collection of data objects represented by a {@link DataModel}
 * instance, which is the current value of this component itself (typically
 * established via a {@link ValueExpression}). During iterative processing over
 * the rows of data in the data model, the object for the current row is exposed
 * as a request attribute under the key specified by the <code>var</code>
 * property.</p>
 * <p/>
 * <p>Only children of type {@link UITreeColumn} should be processed by renderers
 * associated with this component.</p>
 * <p/>
 * <p>By default, the <code>rendererType</code> property is set to
 * <code>javax.faces.Table</code>.  This value can be changed by calling the
 * <code>setRendererType()</code> method.</p>
 */
public class UITreeData extends UIData {

    /**
     * <p>The {@link DataModel} associated with this component, lazily
     * instantiated if requested.  This object is not part of the saved and
     * restored state of the component.</p>
     */
    private DataModel model = null;

    // --------------------------------------------------------- Protected Methods
    /**
     * <p>Return the internal {@link DataModel} object representing the data
     * objects that we will iterate over in this component's rendering.</p>
     * <p/>
     * <p>If the model has been cached by a previous call to {@link
     * #setDataModel}, return it.  Otherwise call {@link #getValue}.  If the
     * result is null, create an empty {@link ListDataModel} and return it.  If
     * the result is an instance of {@link DataModel}, return it.  Otherwise,
     * adapt the result as described in {@link #getValue} and return it.</p>
     */
    @Override
    public DataModel getDataModel() {

        // Return any previously cached DataModel instance
        if (this.model != null) {
            return (model);
        }

        // Synthesize a DataModel around our current value if possible
        Object current = getValue();
        // Case the value is a DefaultTreeModel
        if (current instanceof DefaultTreeModel) {
            final ExtendTreeModel currentEtm = TreeModelAdapter.defaultTreeModel2Extend((DefaultTreeModel) current);
            setDataModel(new TreeDataModel(currentEtm));
        } // Case the value is an ExtendTreeModel
        else if (current instanceof ExtendTreeModel) {
            setDataModel(new TreeDataModel((ExtendTreeModel) current));
        } // Case the value is a TreeDataModel
        else if (current instanceof TreeDataModel) {
            setDataModel((TreeDataModel) current);
        } // Case the value is a String (Tomcat)
        else if (current instanceof String) {
            final FacesContext context = FacesContext.getCurrentInstance();
            final ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
            final ValueExpression ve = expressionFactory.createValueExpression(context.getELContext(), (String) current, java.lang.Object.class);
            final Object object = ve.getValue(context.getELContext());
            if (object instanceof DefaultTreeModel) {
                final ExtendTreeModel currentEtm = TreeModelAdapter.defaultTreeModel2Extend((DefaultTreeModel) object);
                setDataModel(new TreeDataModel(currentEtm));
            } else if (object instanceof ExtendTreeModel) {
                setDataModel(new TreeDataModel((ExtendTreeModel) object));
            } else if (object instanceof TreeDataModel) {
                setDataModel((TreeDataModel) object);
            }
        } else {
            setDataModel(new ScalarDataModel(current));
        }
        if (model == null) {
            setDataModel(new TreeDataModel());
        }
        return (model);

    }

    /**
     * <p>Set the internal DataModel.  This <code>UITreeDate</code> instance must
     * use the given {@link DataModel} as its internal value representation from
     * now until the next call to <code>setDataModel</code>.  If the given
     * <code>DataModel</code> is <code>null</code>, the internal
     * <code>DataModel</code> must be reset in a manner so that the next call to
     * {@link #getDataModel} causes lazy instantion of a newly refreshed
     * <code>DataModel</code>.</p>
     * <p/>
     * <p>Subclasses might call this method if they either want to restore the
     * internal <code>DataModel</code> during the <em>Restore View</em> phase or
     * if they want to explicitly refresh the current <code>DataModel</code> for
     * the <em>Render Response</em> phase.</p>
     *
     * @param dataModel the new <code>DataModel</code> or <code>null</code> to
     *                  cause the model to be refreshed.
     */
    @Override
    protected void setDataModel(DataModel dataModel) {
        this.model = dataModel;
    }
}
