
package org.mapfaces.models.timeline;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class Status {
    
    /**
     * The constant attribute that defines the In Progress status.
     */
    public static final Status IN_PROGRESS = new Status("In Progress");
    
    /**
     * The constant attribute that defines the Not Started status.
     */
    public static final Status NOT_STARTED = new Status("Not Started");
    
    /**
     * The constant attribute that defines the Default status.
     */
    public static final Status DEFAULT = new Status("Default");
    
    /**
     * The status name.
     */
    private final String name;
    
    /**
     * Creates a new instance of Status.
     * @param name can be In Progress, Not Started or Default.
     */
    public Status(String name) {
        this.name=name;
        ensureNonNull("name", name);
    }
    
    /**
     * Compares this object with the specified element for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (object!=null && object.getClass().equals(getClass())) {
            final Status that = (Status) object;
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
