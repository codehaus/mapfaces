/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models.timeline;

import java.util.Date;
import org.geotools.util.Utilities;

/**
 *
 * @author Mehdi Sidhoum
 */
public class Zone {
    private Date begin;
    private Date end;
    private String unit;
    private Integer magnify;
    
    public Zone(Date begin, Date end, String unit, Integer magnify) {
        this.begin = begin;
        this.end = end;
        this.unit = unit;
        this.magnify = magnify;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getMagnify() {
        return magnify;
    }

    public void setMagnify(Integer magnify) {
        this.magnify = magnify;
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Zone) {
            final Zone that = (Zone) object;

            return Utilities.equals(this.begin, that.begin) &&
                    Utilities.equals(this.end, that.end) &&
                    Utilities.equals(this.magnify, that.magnify) &&
                    Utilities.equals(this.unit, that.unit);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.unit != null ? this.unit.hashCode() : 0);
        hash = 37 * hash + this.begin.hashCode();
        hash = 37 * hash + this.end.hashCode();
        hash = 37 * hash + this.magnify;
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Zone:").append('\n');
        if (unit != null) {
            s.append("unit:").append(unit).append('\n');
        }
        s.append("begin:").append(begin).append('\n');
        s.append("end:").append(end).append('\n');
        s.append("magnify:").append(magnify).append('\n');

        return s.toString();
    }

}
