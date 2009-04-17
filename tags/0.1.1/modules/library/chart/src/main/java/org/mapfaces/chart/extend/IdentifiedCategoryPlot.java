

package org.mapfaces.chart.extend;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryCrosshairState;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.util.SortOrder;

public class IdentifiedCategoryPlot extends CategoryPlot{


    public IdentifiedCategoryPlot(CategoryDataset set, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryItemRenderer renderer) {
        super(set, domainAxis, rangeAxis, renderer);
    }

    /**
     * Draws a representation of a dataset within the dataArea region using the
     * appropriate renderer.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param index  the dataset and renderer index.
     * @param info  an optional object for collection dimension information.
     * @param crosshairState  a state object for tracking crosshair info
     *        (<code>null</code> permitted).
     *
     * @return A boolean that indicates whether or not real data was found.
     *
     * @since 1.0.11
     */
    public boolean render(Graphics2D g2, Rectangle2D dataArea, int index,
            PlotRenderingInfo info, CategoryCrosshairState crosshairState) {

        boolean foundData = false;
        CategoryDataset currentDataset = getDataset(index);
        CategoryItemRenderer renderer = getRenderer(index);
        CategoryAxis domainAxis = getDomainAxisForDataset(index);
        ValueAxis rangeAxis = getRangeAxisForDataset(index);
        boolean hasData = !DatasetUtilities.isEmptyOrNull(currentDataset);
        if (hasData && renderer != null) {

            ////////////////////////////////////////////////////////////////////
            if(g2 instanceof IdentifiedSVGGraphics2D){
                ((IdentifiedSVGGraphics2D)g2).addSVGAttribut("serie","toptop");
            }
            ////////////////////////////////////////////////////////////////////


            foundData = true;
            CategoryItemRendererState state = renderer.initialise(g2, dataArea,
                    this, index, info);
            state.setCrosshairState(crosshairState);
            int columnCount = currentDataset.getColumnCount();
            int rowCount = currentDataset.getRowCount();
            int passCount = renderer.getPassCount();
            for (int pass = 0; pass < passCount; pass++) {
                if (getColumnRenderingOrder() == SortOrder.ASCENDING) {
                    for (int column = 0; column < columnCount; column++) {
                        if (getRowRenderingOrder() == SortOrder.ASCENDING) {
                            for (int row = 0; row < rowCount; row++) {
                                renderer.drawItem(g2, state, dataArea, this,
                                        domainAxis, rangeAxis, currentDataset,
                                        row, column, pass);
                            }
                        }
                        else {
                            for (int row = rowCount - 1; row >= 0; row--) {
                                renderer.drawItem(g2, state, dataArea, this,
                                        domainAxis, rangeAxis, currentDataset,
                                        row, column, pass);
                            }
                        }
                    }
                }
                else {
                    for (int column = columnCount - 1; column >= 0; column--) {
                        if (getRowRenderingOrder() == SortOrder.ASCENDING) {
                            for (int row = 0; row < rowCount; row++) {
                                renderer.drawItem(g2, state, dataArea, this,
                                        domainAxis, rangeAxis, currentDataset,
                                        row, column, pass);
                            }
                        }
                        else {
                            for (int row = rowCount - 1; row >= 0; row--) {
                                renderer.drawItem(g2, state, dataArea, this,
                                        domainAxis, rangeAxis, currentDataset,
                                        row, column, pass);
                            }
                        }
                    }
                }
            }

            ////////////////////////////////////////////////////////////////////
            if(g2 instanceof IdentifiedSVGGraphics2D){
                ((IdentifiedSVGGraphics2D)g2).clearSVGAttributs();
            }
            ////////////////////////////////////////////////////////////////////

        }
        return foundData;

    }

}
