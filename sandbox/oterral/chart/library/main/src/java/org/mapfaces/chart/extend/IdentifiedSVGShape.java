
package org.mapfaces.chart.extend;

import java.awt.Shape;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGShape;
import org.w3c.dom.Element;

public class IdentifiedSVGShape extends SVGShape{

    private final IdentifiedSVGGraphics2D generator;

    public IdentifiedSVGShape(IdentifiedSVGGraphics2D generator,
            SVGGeneratorContext generatorContext) {
        super(generatorContext);
        this.generator = generator;
    }

    @Override
    public Element toSVG(Shape arg0) {
        final Element ele = super.toSVG(arg0);

        final String id = generator.getCurrentObjectId();
        if(id != null) ele.setAttribute("id", id);
        final String value = generator.getCurrentObjectValue();
        if(value != null) ele.setAttribute("value", value);
        
        return ele;
    }



}
