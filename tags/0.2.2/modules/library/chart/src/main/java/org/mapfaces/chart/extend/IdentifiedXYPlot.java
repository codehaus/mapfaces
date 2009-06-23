package org.mapfaces.chart.extend;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.renderer.RendererUtilities;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

/**
 * @author Johann Sorel (Geomatys)
 */
public class IdentifiedXYPlot extends FastXYPlot {

    public IdentifiedXYPlot(XYDataset dataset, ValueAxis domainAxis, ValueAxis rangeAxis, XYItemRenderer renderer) {
        super(dataset, domainAxis, rangeAxis, renderer);
    }

    /**
     * Draws a representation of the data within the dataArea region, using the
     * current renderer.
     * <P>
     * The <code>info</code> and <code>crosshairState</code> arguments may be
     * <code>null</code>.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param index  the dataset index.
     * @param info  an optional object for collection dimension information.
     * @param crosshairState  collects crosshair information
     *                        (<code>null</code> permitted).
     *
     * @return A flag that indicates whether any data was actually rendered.
     */
    @Override
    public boolean render(Graphics2D g2,
            Rectangle2D dataArea,
            int index,
            PlotRenderingInfo info,
            CrosshairState crosshairState) {


        boolean foundData = false;
        boolean disableOptimization = false;

        XYDataset dataset = getDataset(index);

        if (!DatasetUtilities.isEmptyOrNull(dataset)) {
            foundData = true;
            ValueAxis xAxis = getDomainAxisForDataset(index);
            ValueAxis yAxis = getRangeAxisForDataset(index);
            XYItemRenderer renderer = getRenderer(index);
            if (renderer == null) {
//                    System.out.println("renderer(index) is null");
                renderer = getRenderer();
                if (renderer == null) { // no default renderer available
                    return foundData;
                }
            }

            XYItemRendererState state = renderer.initialise(g2, dataArea, this,
                    dataset, info);
            int passCount = renderer.getPassCount();

            if (renderer instanceof XYLineAndShapeRenderer) {
                disableOptimization = ((XYLineAndShapeRenderer) renderer).getDrawSeriesLineAsPath();   // in case of e.g. splines
            }

            RectangleEdge domainEdge = getDomainAxisEdge();
            RectangleEdge rangeEdge = getDomainAxisEdge();

            SeriesRenderingOrder seriesOrder = getSeriesRenderingOrder();
            if (seriesOrder == SeriesRenderingOrder.REVERSE) {
//                System.out.println("je suis dans le reverse");
                //render series in reverse order
                for (int pass = 0; pass < passCount; pass++) {
                    super.renderedPixels.clear();                     // need to clear every pass or else shapes won't be drawn correctly
                    int seriesCount = dataset.getSeriesCount();
                    for (int series = seriesCount - 1; series >= 0; series--) {
                        int firstItem = 0;
                        int lastItem = dataset.getItemCount(series) - 1;
                        if (lastItem == -1) {
                            continue;
                        }
                        if (state.getProcessVisibleItemsOnly()) {
//                            System.out.println("je suis dans le getProcessVisibleItemsOnly");

                            int[] itemBounds = RendererUtilities.findLiveItems(
                                    dataset, series, xAxis.getLowerBound(),
                                    xAxis.getUpperBound());
                            firstItem = itemBounds[0];
                            lastItem = itemBounds[1];
                            
                        }
                        for (int item = firstItem; item <= lastItem; item++) {
                            if (disableOptimization || !super.hasRendered(dataset, xAxis, yAxis, domainEdge, rangeEdge, dataArea, series, item)) {
                                ////////////////////////////////////////////////////////////////////
                                if (renderer instanceof XYLineAndShapeRenderer && (lastItem-firstItem) < 80) {
                                   ((XYLineAndShapeRenderer) getRenderer(index)).setBaseShapesVisible(true);
//                                    System.out.println("je suis dans le afstXYyyy plot"+(lastItem-firstItem) );
                                    renderer =  ((XYLineAndShapeRenderer) getRenderer(index));
                                }else {
                                    
                                   ((XYLineAndShapeRenderer) getRenderer(index)).setBaseShapesVisible(false);
//                                    System.out.println("je suis dans le afstXYyyy2 plot"+(lastItem-firstItem) );
                                    renderer =  ((XYLineAndShapeRenderer) getRenderer(index));
                                }
                                if (g2 instanceof IdentifiedSVGGraphics2D) {
                                    ((IdentifiedSVGGraphics2D) g2).addSVGAttribut("serie", getDataset(index).getSeriesKey(series).toString());
                                    ((IdentifiedSVGGraphics2D) g2).addSVGAttribut("xValue", String.valueOf(getDataset(index).getXValue(series, item)));
                                    ((IdentifiedSVGGraphics2D) g2).addSVGAttribut("yValue", String.valueOf(getDataset(index).getYValue(series, item)));
                                }
                                ////////////////////////////////////////////////////////////////////

                                renderer.drawItem(g2, state, dataArea, info,
                                        this, xAxis, yAxis, dataset, series, item,
                                        crosshairState, pass);
                            }
                        }
                    }
                }
            } else {
//                System.out.println("je suis dans le order");

                //render series in forward order
                for (int pass = 0; pass < passCount; pass++) {
                    renderedPixels.clear();                     // need to clear every pass or else shapes won't be drawn correctly
                    int seriesCount = dataset.getSeriesCount();
                    for (int series = 0; series < seriesCount; series++) {
                        int firstItem = 0;
                        int lastItem = dataset.getItemCount(series) - 1;
                        if (state.getProcessVisibleItemsOnly()) {
//                            System.out.println("je suis dans le getProcessVisibleItemsOnly");

                            int[] itemBounds = RendererUtilities.findLiveItems(
                                    dataset, series, xAxis.getLowerBound(),
                                    xAxis.getUpperBound());
                            firstItem = itemBounds[0];
                            lastItem = itemBounds[1];
                        }
                        for (int item = firstItem; item <= lastItem; item++) {
                            if (disableOptimization || !hasRendered(dataset, xAxis, yAxis, domainEdge, rangeEdge, dataArea, series, item)) {
                                ////////////////////////////////////////////////////////////////////
                                if (renderer instanceof XYLineAndShapeRenderer && (lastItem-firstItem) < 50) {
                                   ((XYLineAndShapeRenderer) getRenderer(index)).setBaseShapesVisible(true);
//                                    System.out.println("je suis dans le afstXY plot"+(lastItem-firstItem) );
                                    renderer =  ((XYLineAndShapeRenderer) getRenderer(index));
                                }else {
                                    
                                   ((XYLineAndShapeRenderer) getRenderer(index)).setBaseShapesVisible(false);
//                                    System.out.println("je suis dans le afstXY2 plot"+(lastItem-firstItem) );
                                    renderer =  ((XYLineAndShapeRenderer) getRenderer(index));
                                }
                                renderer.setSeriesPaint(0, Color.ORANGE);
                                if (g2 instanceof IdentifiedSVGGraphics2D) {
                                    ((IdentifiedSVGGraphics2D) g2).addSVGAttribut("serie", getDataset(index).getSeriesKey(series).toString());
                                    ((IdentifiedSVGGraphics2D) g2).addSVGAttribut("xValue", String.valueOf(getDataset(index).getXValue(series, item)));
                                    ((IdentifiedSVGGraphics2D) g2).addSVGAttribut("yValue", String.valueOf(getDataset(index).getYValue(series, item)));
                                }
                                ////////////////////////////////////////////////////////////////////

                                renderer.drawItem(g2, state, dataArea, info,
                                        this, xAxis, yAxis, dataset, series, item,
                                        crosshairState, pass);
                            }
                        }
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////
            if (g2 instanceof IdentifiedSVGGraphics2D) {
                ((IdentifiedSVGGraphics2D) g2).clearSVGAttributs();
            }
        ////////////////////////////////////////////////////////////////////
        }
        return foundData;
    }
}
