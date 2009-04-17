
package org.mapfaces.chart.extend;

import java.awt.Shape;
import java.util.Map;
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

        final Map<String,String> attributs = generator.getSVGAttributs();
        if (ele != null) {
            for(String key : attributs.keySet()){
                ele.setAttribute(key, attributs.get(key));
            }
        }
        
        return ele;
    }



}
