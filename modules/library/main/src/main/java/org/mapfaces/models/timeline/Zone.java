/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.models.timeline;

import java.io.Serializable;
import java.util.Date;
import org.geotoolkit.util.Utilities;

/**
 * @author Mehdi Sidhoum
 */
public class Zone implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;
    private Date begin;
    private Date end;
    private String unit;
    private Integer magnify;

    public Zone(final Date begin, final Date end, final String unit, final Integer magnify) {
        this.begin = begin;
        this.end = end;
        this.unit = unit;
        this.magnify = magnify;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(final Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
        this.end = end;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public Integer getMagnify() {
        return magnify;
    }

    public void setMagnify(final Integer magnify) {
        this.magnify = magnify;
    }

    /**
     * {@inheritDoc }
     */
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

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.unit != null ? this.unit.hashCode() : 0);
        hash = 37 * hash + this.begin.hashCode();
        hash = 37 * hash + this.end.hashCode();
        hash = 37 * hash + this.magnify;
        return hash;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder("Zone:").append('\n');
        if (unit != null) {
            s.append("unit:").append(unit).append('\n');
        }
        s.append("begin:").append(begin).append('\n');
        s.append("end:").append(end).append('\n');
        s.append("magnify:").append(magnify).append('\n');

        return s.toString();
    }

}
