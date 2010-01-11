/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General  License for more details.
 */

package org.mapfaces.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author Mehdi Sidhoum.
 */
public class Serial implements Serializable {

    /**
    +     * Determines if a de-serialized file is compatible with this class.
    +     *
    +     * Maintainers must change this value if and only if the new version
    +     * of this class is not compatible with old versions. See Sun docs
    +     * for <a href=http://java.sun.com/products/jdk/1.1/docs/guide
    +     * /serialization/spec/version.doc.html> details. </a>
    +     *
    +     * Not necessary to include in first version of the class, but
    +     * included here as a reminder of its importance.
    +     */
    private static final long serialVersionUID = 7526471155622776147L;
    private String name;
    private transient Dimension dimension;
    private static Serial singleton = null;

    /**
     * Empty constructor used for Serialization.
     */
    public Serial() {
    }

    public Serial(String name, Dimension d) {
        this.name = name;
        this.dimension = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /*@Override
    public String toString() {
    return "[ " + this.getClass().getSimpleName() + " : " + this.name + " : " + this.dimension + " ] ";
    }*/
    
    /*public static void main(String... args) {

        try {
            final Dimension d = new DefaultDimension();
            d.setCurrent(true);

            Serial serial = new Serial("monserial", d);
            System.out.println(serial + " : " + serial.getDimension().isCurrent());


            final FileOutputStream fos = new FileOutputStream("serial.serial");
            final ObjectOutputStream oos = new ObjectOutputStream(fos);

            try {
                oos.writeObject(serial);
                oos.flush();
            } finally {
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
            final FileInputStream fis = new FileInputStream("serial.serial");
            final ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                serial = (Serial) ois.readObject();
                System.out.println(serial + " : " + serial.getDimension().isCurrent());
            } finally {
                ois.close();
                fis.close();
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/

    Object writeReplace() throws ObjectStreamException {
        final Dimension dim = getDimension();
        final Serial tmp = getSingleton();
        tmp.setDimension(dim);
        final Serial s = this;

        return s;
    }

    Object readResolve() throws ObjectStreamException {
        final Serial s = this;
        final Dimension dim = getSingleton().getDimension();
        s.setDimension(dim);

        return s;
    }

    public static synchronized Serial getSingleton() {
        if (singleton == null) {
            singleton = new Serial();
        }
        return singleton;
    }
}
