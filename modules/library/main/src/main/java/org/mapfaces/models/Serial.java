/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 *
 * @author Med
 */
public class Serial implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private String name;
    
    private transient Dimension dimension;
    
    static private Serial singleton = null;

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

    public static void main(String... args) {

        try {
            Dimension d = new DefaultDimension();
            d.setCurrent(true);
            
            Serial serial = new Serial("monserial", d);
            System.out.println(serial + " : " + serial.getDimension().isCurrent());


            FileOutputStream fos = new FileOutputStream("serial.serial");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

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
            FileInputStream fis = new FileInputStream("serial.serial");
            ObjectInputStream ois = new ObjectInputStream(fis);
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
    }
    
    Object writeReplace() throws ObjectStreamException {
        System.out.println("Entering writeReplace");
        
        singleton = this;
        Serial s = getSingleton();
                
        return s;
    }

    Object readResolve() throws ObjectStreamException {
        System.out.println("Entering readResolve");
        
        Serial s = getSingleton();
        
        return s;
    }

    static public synchronized Serial getSingleton() {
        if (singleton == null) {
            singleton = new Serial();
        }
        return singleton;
    }
}
