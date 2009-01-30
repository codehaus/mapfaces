/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.chart.extend;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author sorel
 */
public class Testeur {

    public static void main(String[] args) throws FileNotFoundException, SVGGraphics2DIOException, UnsupportedEncodingException {

        FileOutputStream stream = new FileOutputStream(new File("test.svg"));

        DefaultPieDataset dataset = new DefaultPieDataset();
	dataset.setValue("id A", 52);
	dataset.setValue("id B", 18);
	dataset.setValue("id C", 30);

        JFreeChart chart = IdentifiedChartFactory.createPieChart("", dataset, true, false,Locale.FRENCH);


        final DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        final String svgNS = "http://www.w3.org/2000/svg";
        final Document document = domImpl.createDocument(svgNS, "svg", null);
        Element doc = document.getDocumentElement();

        final SVGGraphics2D svgGenerator = new IdentifiedSVGGraphics2D(document);
//        svgGenerator.getRoot(doc);
        svgGenerator.setSVGCanvasSize(new Dimension(500,500));
        chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, 500,500), null);
        doc.appendChild(svgGenerator.getRoot().getChildNodes().item(2));

        svgGenerator.stream(doc, new OutputStreamWriter(stream, "UTF-8"));
        
    }

}
