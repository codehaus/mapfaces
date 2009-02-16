/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SourceProvider implements ISourceProvider {

    public DefaultCategoryDataset getCategoryDataset() {
        DefaultCategoryDataset categoryDataSet;

        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        // column keys...
        String category1 = "A";
        String category2 = "B";
        String category3 = "C";
        String category4 = "D";
        String category5 = "E";

        // create the dataset...
        categoryDataSet = new DefaultCategoryDataset();
        categoryDataSet.addValue(1.0, series1, category1);
        categoryDataSet.addValue(4.0, series1, category2);
        categoryDataSet.addValue(3.0, series1, category3);
        categoryDataSet.addValue(5.0, series1, category4);
        categoryDataSet.addValue(5.0, series1, category5);
        categoryDataSet.addValue(5.0, series2, category1);
        categoryDataSet.addValue(7.0, series2, category2);
        categoryDataSet.addValue(6.0, series2, category3);
        categoryDataSet.addValue(8.0, series2, category4);
        categoryDataSet.addValue(4.0, series2, category5);
        categoryDataSet.addValue(4.0, series3, category1);
        categoryDataSet.addValue(3.0, series3, category2);
        categoryDataSet.addValue(2.0, series3, category3);
        categoryDataSet.addValue(3.0, series3, category4);
        categoryDataSet.addValue(6.0, series3, category5);
        return categoryDataSet;

    }

    public DefaultPieDataset getPieDataset() {
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        pieDataSet.setValue("A", 52);
        pieDataSet.setValue("B", 18);
        pieDataSet.setValue("C", 30);
        return pieDataSet;
    }

    /**
     * Creates a sample dataset.
     * 
     * @param name  the dataset name.
     * @param base  the starting value.
     * @param start  the starting period.
     * @param count  the number of values to generate.
     *
     * @return The dataset.
     */
    private XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {

        /*TimeSeries series = new TimeSeries(name, start.getClass());
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
        series.add(period, value);    
        period = period.next();
        value = value * (1 + (Math.random() - 0.495) / 10.0);
        }
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);*/
        TimeSeries s1 = new TimeSeries("Series 1 ", Month.class);
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 1995), 142.9);
        s1.add(new Month(2, 1995), 138.7);
        s1.add(new Month(3, 1995), 137.3);
        s1.add(new Month(4, 1995), 143.9);
        s1.add(new Month(5, 1995), 139.8);
        s1.add(new Month(6, 1995), 137.0);
        s1.add(new Month(7, 1995), 132.8); 
        s1.add(new Month(3, 1999), 167.3);
        s1.add(new Month(4, 1999), 153.8);
        s1.add(new Month(5, 1999), 167.6);
        s1.add(new Month(6, 1999), 158.8);
        s1.add(new Month(7, 1999), 148.3);
        s1.add(new Month(8, 1999), 153.9);
        s1.add(new Month(9, 1999), 142.7);
        s1.add(new Month(10, 1999), 123.2);
        s1.add(new Month(11, 1999), 131.8);
        s1.add(new Month(12, 1999), 139.6);
        s1.add(new Month(1, 1994), 142.9);
        s1.add(new Month(2, 1994), 138.7);
        s1.add(new Month(3, 1994), 137.3);
        s1.add(new Month(4, 1994), 143.9);
        s1.add(new Month(5, 1994), 139.8);
        s1.add(new Month(6, 1994), 137.0);
        s1.add(new Month(7, 1994), 132.8); 
        s1.add(new Month(3, 2000), 167.3);
        s1.add(new Month(4, 2000), 153.8);
        s1.add(new Month(5, 2000), 167.6);
        s1.add(new Month(6, 2000), 158.8);
        s1.add(new Month(7, 2000), 148.3);
        s1.add(new Month(8, 2000), 153.9);
        s1.add(new Month(9, 2000), 142.7);
        s1.add(new Month(10, 2000), 123.2);
        s1.add(new Month(11, 2000), 131.8);
        s1.add(new Month(12, 2000), 139.6);
        s1.add(new Month(1, 1996), 142.9);
        s1.add(new Month(2, 1996), 138.7);
        s1.add(new Month(3, 1996), 137.3);
        s1.add(new Month(4, 1996), 143.9);
        s1.add(new Month(5, 1996), 139.8);
        s1.add(new Month(6, 1996), 137.0);
        s1.add(new Month(7, 1996), 132.8); 
        s1.add(new Month(3, 1992), 167.3);
        s1.add(new Month(4, 1992), 153.8);
        s1.add(new Month(5, 1992), 167.6);
        s1.add(new Month(6, 1992), 158.8);
        s1.add(new Month(7, 1992), 148.3);
        s1.add(new Month(8, 1992), 153.9);
        s1.add(new Month(9, 1992), 142.7);
        s1.add(new Month(10, 1992), 123.2);
        s1.add(new Month(11, 1992), 131.8);
        s1.add(new Month(12, 1992), 139.6);
        s1.add(new Month(1, 2003), 142.9);
        s1.add(new Month(2, 2003), 138.7);
        s1.add(new Month(3, 2003), 137.3);
        s1.add(new Month(4, 2003), 143.9);
        s1.add(new Month(5, 2003), 139.8);
        s1.add(new Month(6, 2003), 137.0);
        s1.add(new Month(7, 2003), 132.8);
        s1.add(new Month(8, 2003), 142.9);
        s1.add(new Month(9, 2003), 138.7);
        s1.add(new Month(10, 2003), 137.3);
        s1.add(new Month(8, 2004), 143.9);
        s1.add(new Month(9, 2004), 139.8);
        s1.add(new Month(10, 2004), 137.0);
        s1.add(new Month(11, 2004), 132.8);
        s1.add(new Month(12, 2004), 142.9);
        s1.add(new Month(2, 2004), 138.7);
        s1.add(new Month(3, 2004), 137.3);
        s1.add(new Month(4, 2004), 143.9);
        s1.add(new Month(5, 2004), 139.8);
        s1.add(new Month(6, 2004), 137.0);
        s1.add(new Month(10, 2005), 132.8);
        s1.add(new Month(1, 2005), 142.9);
        s1.add(new Month(2, 2005), 138.7);
        s1.add(new Month(3, 2005), 137.3);
        s1.add(new Month(4, 2005), 143.9);
        s1.add(new Month(5, 2005), 139.8);
        s1.add(new Month(6, 2005), 137.0);
        s1.add(new Month(7, 2005), 132.8);
        s1.add(new Month(8, 2005), 137.0);
        s1.add(new Month(9, 2005), 132.8);
        s1.add(new Month(1, 2006), 142.9);
        s1.add(new Month(2, 2006), 138.7);
        s1.add(new Month(3, 2006), 137.3);
        s1.add(new Month(4, 2006), 143.9);
        s1.add(new Month(5, 2006), 139.8);
        s1.add(new Month(6, 2006), 137.0);
        s1.add(new Month(7, 2006), 132.8);
        s1.add(new Month(8, 2006), 137.0);
        s1.add(new Month(9, 2006), 132.8);
        s1.add(new Month(10, 2006), 142.9);
        s1.add(new Month(2, 2007), 138.7);
        s1.add(new Month(3, 2007), 137.3);
        s1.add(new Month(4, 2007), 143.9);
        s1.add(new Month(5, 2007), 139.8);
        s1.add(new Month(6, 2007), 137.0);
        s1.add(new Month(7, 2007), 132.8);
        s1.add(new Month(8, 2007), 137.0);
        s1.add(new Month(9, 2007), 132.8);
        s1.add(new Month(10, 2007), 142.9);
        s1.add(new Month(11, 2007), 138.7);
        s1.add(new Month(3, 2008), 137.3);
        s1.add(new Month(4, 2008), 143.9);
        s1.add(new Month(5, 2008), 139.8);
        s1.add(new Month(6, 2008), 137.0);
        s1.add(new Month(7, 2008), 132.8);
        
        TimeSeries s10 = new TimeSeries("Series 10", Month.class);
        s10.add(new Month(2, 2001), 1810.8);
        s10.add(new Month(3, 2001), 1670.3);
        s10.add(new Month(4, 2001), 1530.8);
        s10.add(new Month(5, 2001), 1670.6);
        s10.add(new Month(6, 2001), 1580.8);
        s10.add(new Month(7, 2001), 1480.3);
        s10.add(new Month(8, 2001), 1530.9);
        s10.add(new Month(9, 2001), 1420.7);
        s10.add(new Month(10, 2001), 1230.2);
        s10.add(new Month(11, 2001), 1310.8);
        s10.add(new Month(12, 2001), 1390.6);
        s10.add(new Month(1, 2002), 1420.9);
        s10.add(new Month(2, 2002), 1380.7);
        s10.add(new Month(3, 2002), 1370.3);
        s10.add(new Month(4, 2002), 1430.9);
        s10.add(new Month(5, 2002), 1390.8);
        s10.add(new Month(6, 2002), 1370.0);
        s10.add(new Month(7, 2002), 1320.8); 
        s10.add(new Month(2, 1999), 18100.8);
        s10.add(new Month(3, 1999), 16700.3);
        s10.add(new Month(4, 1999), 15300.8);
        s10.add(new Month(5, 1999), 16700.6);
        s10.add(new Month(6, 1999), 15800.8);
        s10.add(new Month(7, 1999), 1480.3);
        s10.add(new Month(8, 1999), 1530.9);
        s10.add(new Month(9, 1999), 1420.7);
        s10.add(new Month(10, 1999), 1230.2);
        s10.add(new Month(11, 1999), 1310.8);
        s10.add(new Month(12, 1999), 1390.6);
        s10.add(new Month(1, 1995), 1420.9);
        s10.add(new Month(2, 1995), 1380.7);
        s10.add(new Month(3, 1995), 1370.3);
        s10.add(new Month(4, 1995), 1430.9);
        s10.add(new Month(5, 1995), 1390.8);
        s10.add(new Month(6, 1995), 1370.0);
        s10.add(new Month(7, 1995), 1320.8); 
        s10.add(new Month(2, 1998), 1810.8);
        s10.add(new Month(3, 1998), 1670.3);
        s10.add(new Month(4, 1998), 1530.8);
        s10.add(new Month(5, 1998), 1670.6);
        s10.add(new Month(6, 1998), 1580.8);
        s10.add(new Month(7, 1998), 1480.3);
        s10.add(new Month(8, 1998), 1530.9);
        s10.add(new Month(9, 1998), 1420.7);
        s10.add(new Month(10, 1998), 1230.2);
        s10.add(new Month(11, 1998), 1310.8);
        s10.add(new Month(12, 1998), 1390.6);
        s10.add(new Month(1, 1996), 1420.9);
        s10.add(new Month(2, 1996), 1380.7);
        s10.add(new Month(3, 1996), 1370.3);
        s10.add(new Month(4, 1996), 1430.9);
        s10.add(new Month(5, 1996), 1390.8);
        s10.add(new Month(6, 1996), 1370.0);
        s10.add(new Month(7, 1996), 1320.8); 
        s10.add(new Month(2, 1997), 1810.8);
        s10.add(new Month(3, 1997), 1670.3);
        s10.add(new Month(4, 1997), 1530.8);
        s10.add(new Month(5, 1997), 1670.6);
        s10.add(new Month(6, 1997), 1580.8);
        s10.add(new Month(7, 1997), 1480.3);
        s10.add(new Month(8, 1997), 1530.9);
        s10.add(new Month(9, 1997), 1420.7);
        s10.add(new Month(10, 1997), 1230.2);
        s10.add(new Month(11, 1997), 1310.8);
        s10.add(new Month(12, 1997), 1390.6);
        s10.add(new Month(1, 2003), 1420.9);
        s10.add(new Month(2, 2003), 1380.7);
        s10.add(new Month(3, 2003), 1370.3);
        s10.add(new Month(4, 2003), 1430.9);
        s10.add(new Month(5, 2003), 1390.8);
        s10.add(new Month(6, 2003), 1370.0);
        s10.add(new Month(7, 2003), 1320.8);
        s10.add(new Month(10, 2003), 1420.9);
        s10.add(new Month(8, 2003), 1380.7);
        s10.add(new Month(9, 2003), 1370.3);
        s10.add(new Month(4, 2004), 1430.9);
        s10.add(new Month(5, 2004), 1390.8);
        s10.add(new Month(6, 2004), 1370.0);
        s10.add(new Month(7, 2004), 1320.8);
        s10.add(new Month(1, 2004), 1420.9);
        s10.add(new Month(2, 2004), 1380.7);
        s10.add(new Month(3, 2004), 1370.3);
        s10.add(new Month(8, 2004), 1430.9);
        s10.add(new Month(9, 2004), 1390.8);
        s10.add(new Month(10, 2004), 1370.0);
        s10.add(new Month(1, 2005), 1420.9);
        s10.add(new Month(2, 2005), 1380.7);
        s10.add(new Month(3, 2005), 1370.3);
        s10.add(new Month(4, 2005), 1430.9);
        s10.add(new Month(5, 2005), 1390.8);
        s10.add(new Month(6, 2005), 1370.0);
        s10.add(new Month(7, 2005), 1320.8);
        s10.add(new Month(8, 2005), 1370.0);
        s10.add(new Month(9, 2005), 1320.8);
        s10.add(new Month(1, 2006), 1420.9);
        s10.add(new Month(2, 2006), 1380.7);
        s10.add(new Month(3, 2006), 1370.3);
        s10.add(new Month(4, 2006), 1430.9);
        s10.add(new Month(5, 2006), 1390.8);
        s10.add(new Month(6, 2006), 1370.0);
        s10.add(new Month(7, 2006), 1320.8);
        s10.add(new Month(8, 2006), 1370.0);
        s10.add(new Month(9, 2006), 1320.8);
        s10.add(new Month(10, 2006), 1420.9);
        s10.add(new Month(2, 2007), 1380.7);
        s10.add(new Month(3, 2007), 1370.3);
        s10.add(new Month(4, 2007), 1430.9);
        s10.add(new Month(5, 2007), 1390.8);
        s10.add(new Month(6, 2007), 1370.0);
        s10.add(new Month(7, 2007), 1320.8);
        s10.add(new Month(8, 2007), 1370.0);
        s10.add(new Month(9, 2007), 1320.8);
        s10.add(new Month(10, 2007), 1420.9);
        s10.add(new Month(12, 2007), 1380.7);
        s10.add(new Month(3, 2008), 1370.3);
        s10.add(new Month(4, 2008), 1430.9);
        s10.add(new Month(5, 2008), 1390.8);
        s10.add(new Month(6, 2008), 1370.0);
        s10.add(new Month(7, 2008), 1320.8);
        /*TimeSeries s2 = new TimeSeries("LG UK Index Trust MF", Month.class);
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);
        TimeSeries s3 = new TimeSeries("LG European Index Trust Men", Month.class);
        s3.add(new Month(2, 2003), 181.8);
        s3.add(new Month(3, 2003), 167.3);
        s3.add(new Month(4, 2003), 153.8);
        s3.add(new Month(5, 2003), 167.6);
        s3.add(new Month(6, 2003), 158.8);
        s3.add(new Month(7, 2003), 148.3);
        s3.add(new Month(8, 2003), 153.9);
        s3.add(new Month(9, 2003), 142.7);
        s3.add(new Month(10, 2003), 123.2);
        s3.add(new Month(11, 2003), 131.8);
        s3.add(new Month(12, 2003), 139.6);
        s3.add(new Month(1, 2002), 142.9);
        s3.add(new Month(2, 2002), 138.7);
        s3.add(new Month(3, 2002), 137.3);
        s3.add(new Month(4, 2002), 143.9);
        s3.add(new Month(5, 2002), 139.8);
        s3.add(new Month(6, 2002), 137.0);
        s3.add(new Month(7, 2002), 132.8);
        TimeSeries s4 = new TimeSeries("LG European Index Trust Women", Month.class);
        s4.add(new Month(1, 2002), 142.9);
        s4.add(new Month(2, 2002), 138.7);
        s4.add(new Month(3, 2002), 137.3);
        s4.add(new Month(4, 2002), 143.9);
        s4.add(new Month(5, 2002), 139.8);
        s4.add(new Month(6, 2002), 137.0);
        s4.add(new Month(7, 2002), 132.8);*/
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        //dataset.setDomainIsPointsInTime(true);
        if (name.equals("Series 1"))
        dataset.addSeries(s1);
        if (name.equals("Series 10"))
        dataset.addSeries(s10);
//        if (name.equals("Series 2"))
      /*  dataset.addSeries(s2);
//        if (name.equals("Series 3"))
        dataset.addSeries(s3);
//        if (name.equals("Series 4"))
        dataset.addSeries(s4);*/

        return dataset;

    }
    public List<XYDataset> getMultipleXYDataset() {
        List list = new ArrayList();
        list.add(createDataset("Series 1", 100.0, new Second(), 501));
        list.add(createDataset("Series 10", 100.0, new Second(), 501));
//        list.add(createDataset("Series 2", 1000.0, new Second(), 501));
//        list.add(createDataset("Series 3", 10000.0, new Second(), 501));
//        list.add(createDataset("Series 4", 25.0, new Second(), 501));
        
        return list;
    }
    //Returns an implementation of an xy dataset
    public XYDataset getEmptyXYDataset() {
        TimeSeries serie = new TimeSeries("", Millisecond.class);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(serie);
        return dataset;
    }
    //Returns an implementation of an xy dataset
    public XYDataset getXYDataset() {
        TimeSeriesCollection timeSeriesDataSet = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries(" Capteur 1", Month.class);
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);

        TimeSeries s2 = new TimeSeries(" Capteur 2", Month.class);
        s2.add(new Month(2, 2001), 12.6);
        s2.add(new Month(3, 2001), 12.2);
        s2.add(new Month(4, 2001), 11.2);
        s2.add(new Month(5, 2001), 12.1);
        s2.add(new Month(6, 2001), 12.6);
        s2.add(new Month(7, 2001), 11.2);
        s2.add(new Month(8, 2001), 11.5);
        s2.add(new Month(9, 2001), 11.7);
        s2.add(new Month(10, 2001), 10.5);
        s2.add(new Month(11, 2001), 10.1);
        s2.add(new Month(12, 2001), 11.3);
        s2.add(new Month(1, 2002), 11.7);
        s2.add(new Month(2, 2002), 11.0);
        s2.add(new Month(3, 2002), 10.6);
        s2.add(new Month(4, 2002), 11.2);
        s2.add(new Month(5, 2002), 11.6);
        s2.add(new Month(6, 2002), 10.8);
        s2.add(new Month(7, 2002), 10.6);
        timeSeriesDataSet.addSeries(s1);
       // timeSeriesDataSet.addSeries(s2);

        return timeSeriesDataSet;
    }

    public XYDataset getFirstXYDataset() {
        XYSeries series = new XYSeries("Price");
        series.setDescription("Price");
        series.add(1, 8);
        series.add(2, 7);
        series.add(3, 6);
        series.add(4, 5);
        series.add(5, 4);
        series.add(6, 3);
        series.add(7, 7);
        series.add(8, 8);

        return new XYSeriesCollection(series);
    }

    public XYDataset getSecondXYDataset() {
        XYSeries series = new XYSeries("Sentiment");
        series.setDescription("Sentiment");
        series.add(1, 10);
        series.add(2, 20);
        series.add(3, 30);
        series.add(4, 40);
        series.add(5, 50);
        series.add(6, 60);
        series.add(7, 70);
        series.add(8, 80);

        return new XYSeriesCollection(series);
    }
}
