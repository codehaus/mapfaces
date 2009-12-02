

package org.mapfaces.chart.extend;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlotState;
import org.jfree.data.general.PieDataset;

public class IdentifiedPiePlot extends PiePlot{


    public IdentifiedPiePlot(PieDataset set) {
        super(set);
    }

    @Override
    protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea, PiePlotState state, int currentPass) {

        if(g2 instanceof IdentifiedSVGGraphics2D){
            ((IdentifiedSVGGraphics2D)g2).addSVGAttribut("id",getDataset().getKey(section).toString());
            ((IdentifiedSVGGraphics2D)g2).addSVGAttribut("value",getDataset().getValue(section).toString());
        }

        super.drawItem(g2, section, dataArea, state, currentPass);

        if(g2 instanceof IdentifiedSVGGraphics2D){
            ((IdentifiedSVGGraphics2D)g2).clearSVGAttributs();
        }
    }


}
