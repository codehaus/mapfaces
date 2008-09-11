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

/**
 *
 * @author Mehdi Sidhoum.
 */
public class Priority {
    
    /**
     * The constant attribute that defines the Low priority.
     */
    public static final Priority LOW = new Priority("Low");
    
    /**
     * The constant attribute that defines the High priority.
     */
    public static final Priority HIGH = new Priority("High");
    
    /**
     * The constant attribute that defines the Normal priority.
     */
    public static final Priority NORMAL = new Priority("Normal");
    
    /**
     * The priority name.
     */
    private final String name;
    
    /**
     * Creates a new instance of Priority.
     * @param name can be Low, High or Normal.
     */
    public Priority(String name) {
        this.name=name;
        ensureNonNull("name", name);
    }
    
    /**
     * Compares this object with the specified element for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (object!=null && object.getClass().equals(getClass())) {
            final Priority that = (Priority) object;
            return equals(this.name, that.name);
        }
        return false;
    }
    
    /**
     * Returns a hash value for this element.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    /**
     * Returns a string representation of this element.
     * This is mostly for debugging purpose.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + name + ']';
    }
    
    /**
     * Compares the specified objects for equality.
     * Null values are allowed.
     */
    protected static boolean equals(final Object o1, final Object o2) {
        return (o1 == o2) || (o1 != null && o1.equals(o2));
    }
    
    /**
     * ensure if the value is not null.
     *
     * @throws IllegalArgumentException if value is null.
     */
    protected static void ensureNonNull(final String name, final Object value)
            throws IllegalArgumentException
    {
        if (value == null) {
            throw new IllegalArgumentException("the value of \"" + name + "\" don't should be null.");
        }
    }

}
