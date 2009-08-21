package org.mapfaces.model.tree;

import javax.faces.FacesException;
import javax.faces.model.DataModel;
import javax.faces.model.DataModelEvent;
import javax.faces.model.DataModelListener;

/**
 *
 * @author kevindelfour
 */
public class TreeDataModel extends DataModel {

    // ------------------------------------------------------ Instance Variables
    // The current row index (zero relative)
    private int index = -1;
    // The list we are wrapping
    private ExtendTreeModel treeModel;
    
    public TreeDataModel(ExtendTreeModel treeModel) {
        super();
        setWrappedData(treeModel);
    }

    public TreeDataModel() {
        super();
    }

    /**
     * <p>Return <code>true</code> if there is <code>wrappedData</code>
     * available, and the current value of <code>rowIndex</code> is greater
     * than or equal to zero, and less than the size of the list.  Otherwise,
     * return <code>false</code>.</p>
     *
     * @throws FacesException if an error occurs getting the row availability
     */
    @Override
    public boolean isRowAvailable() {
        if (treeModel == null) {
            return (false);
        } else if ((index >= 0) && (index < (treeModel.length()+1))) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * <p>If there is <code>wrappedData</code> available, return the
     * length of the list.  If no <code>wrappedData</code> is available,
     * return -1.</p>
     *
     * @throws FacesException if an error occurs getting the row count
     */
    @Override
    public int getRowCount() {
        if (treeModel == null) {
            return (-1);
        }
        return (treeModel.length());
    }

    /**
     * <p>If row data is available, return the array element at the index
     * specified by <code>rowIndex</code>.  If no wrapped data is available,
     * return <code>null</code>.</p>
     *
     * @throws FacesException if an error occurs getting the row data
     * @throws IllegalArgumentException if now row data is available
     *  at the currently specified row index
     */
    @Override
    public Object getRowData() {
        if (treeModel == null) {
            return (null);
        } else if (!isRowAvailable()) {
            throw new IllegalArgumentException();
        } else {
            return (treeModel.getRowAt(index));
        }
    }

    /**
     * @throws FacesException {@inheritDoc}
     */
    @Override
    public int getRowIndex() {
        return (index);
    }

    /**
     * @throws FacesException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex < -1) {
            throw new IllegalArgumentException();
        }
        int old = index;
        index = rowIndex;
        if (treeModel == null) {
            return;
        }
        DataModelListener[] listeners = getDataModelListeners();
        if ((old != index) && (listeners != null)) {
            Object rowData = null;
            if (isRowAvailable()) {
                rowData = getRowData();
            }
            DataModelEvent event =
                    new DataModelEvent(this, index, rowData);
            int n = listeners.length;
            for (int i = 0; i < n; i++) {
                if (null != listeners[i]) {
                    listeners[i].rowSelected(event);
                }
            }
        }

    }

    @Override
    public Object getWrappedData() {
        return (this.treeModel);
    }

    /**
     * @throws ClassCastException if <code>data</code> is
     *  non-<code>null</code> and is not a <code>List</code>
     */
    @Override
    public void setWrappedData(Object data) {
        if (data == null) {
            treeModel = null;
            setRowIndex(-1);
        } else {
            treeModel = (ExtendTreeModel) data;
            index = -1;
            setRowIndex(0);
        }
    }
}
