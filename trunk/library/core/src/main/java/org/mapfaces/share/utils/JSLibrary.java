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
package org.mapfaces.share.utils;

/**
 *
 * @author Olivier Terral (Geomatys)
 */
public enum JSLibrary {

    SCRIPTACULOUS("SCRIPTACULOUS"),
    MOOTOOLS("MOOTOOLS");

    private final String value;

    JSLibrary(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static JSLibrary fromValue(String v) {
        for (JSLibrary c: JSLibrary.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
};
