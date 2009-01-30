
package org.mapfaces.chart.extend;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

public class IdentifiedSVGGraphics2D extends SVGGraphics2D{

    private String id = null;
    private String value = null;

    public IdentifiedSVGGraphics2D(Document doc){
        super(doc);
    }

    public void setCurrentObjectId(String id){
        this.id = id;
    }

    public String getCurrentObjectId(){
        return id;
    }
     public void setCurrentObjectValue(String value){
        this.value = value;
    }

    public String getCurrentObjectValue(){
        return value;
    }

    @Override
    protected void setGeneratorContext(SVGGeneratorContext arg0) {
        super.setGeneratorContext(arg0);

        this.shapeConverter = new IdentifiedSVGShape(this, generatorCtx);
    }


}
