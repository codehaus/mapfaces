
package org.mapfaces.chart.extend;

import java.util.HashMap;
import java.util.Map;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

public class IdentifiedSVGGraphics2D extends SVGGraphics2D{

    private final Map<String,String> attributs = new HashMap<String,String>();
    private String id = null;
    private String value = null;

    public IdentifiedSVGGraphics2D(Document doc){
        super(doc);
    }

    public void addSVGAttribut(String key,String value){
        attributs.put(key, value);
    }

    public void clearSVGAttributs(){
        attributs.clear();
    }

    Map<String,String> getSVGAttributs(){
        return attributs;
    }

//    public void setCurrentObjectId(String id){
//        this.id = id;
//    }
//
//    public String getCurrentObjectId(){
//        return id;
//    }
//     public void setCurrentObjectValue(String value){
//        this.value = value;
//    }
//
//    public String getCurrentObjectValue(){
//        return value;
//    }

    @Override
    protected void setGeneratorContext(SVGGeneratorContext arg0) {
        super.setGeneratorContext(arg0);

        this.shapeConverter = new IdentifiedSVGShape(this, generatorCtx);
    }


}
