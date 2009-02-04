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

//            TimeSeries series = new TimeSeries(name, start.getClass());
//            RegularTimePeriod period = start;
//            double value = base;
//            for (int i = 0; i < count; i++) {
//                series.add(period, value);    
//                period = period.next();
//                value = value * (1 + (Math.random() - 0.495) / 10.0);
//            }
//
//            TimeSeriesCollection dataset = new TimeSeriesCollection();
//            dataset.addSeries(series);
            TimeSeries s1 = new TimeSeries("L&G European Index Trust", Month.class);
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

            TimeSeries s2 = new TimeSeries("L&G UK Index Trust MF", Month.class);
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
            TimeSeries s3 = new TimeSeries("L&G European Index Trust Men", Month.class);
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
            TimeSeries s4 = new TimeSeries("L&G European Index Trust Women", Month.class);
            s4.add(new Month(1, 2002), 142.9);
            s4.add(new Month(2, 2002), 138.7);
            s4.add(new Month(3, 2002), 137.3);
            s4.add(new Month(4, 2002), 143.9);
            s4.add(new Month(5, 2002), 139.8);
            s4.add(new Month(6, 2002), 137.0);
            s4.add(new Month(7, 2002), 132.8);
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.setDomainIsPointsInTime(true);
            dataset.addSeries(s1);
            dataset.addSeries(s2);
            dataset.addSeries(s3);
            dataset.addSeries(s4);

            return dataset;

        }
        public List<XYDataset> getMultipleXYDataset() {
            List list = new ArrayList();
            list.add(createDataset("Series 1", 100.0, new Second(), 20));
            list.add(createDataset("Series 2", 1000.0, new Second(), 20));
            list.add(createDataset("Series 3", 10000.0, new Second(), 20));
            list.add(createDataset("Series 4", 25.0, new Second(), 20));
            return list;            
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
			timeSeriesDataSet.addSeries(s2);
                        
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
