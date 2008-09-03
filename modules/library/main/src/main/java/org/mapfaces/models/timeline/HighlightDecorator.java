package org.mapfaces.models.timeline;

import java.util.Date;

/**
 *
 * @author Mehdi Sidhoum
 */
public class HighlightDecorator extends Event {

    public HighlightDecorator(Date start, Date end, String title, String period, String color) {
        super(start, end, null, false, title, period, null, null, null, null, color, null, null, null);
    }
}
