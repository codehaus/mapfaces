/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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
package org.mapfaces.demo.model;

import java.io.Serializable;

/**
 * @author Mehdi Sidhoum (Geomatys)
 */
public class ModelTreeRow implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;
    private int id;
    private String name;
    private boolean read;
    private boolean write;

    public ModelTreeRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * @return the write
     */
    public boolean isWrite() {
        return write;
    }

    /**
     * @param write the write to set
     */
    public void setWrite(boolean write) {
        this.write = write;
    }

    @Override
    public String toString() {
        return ModelTreeRow.class.getName() + " : " + this.id + " name= " + this.getName() + "  read= " + this.isRead() + " write= " + this.isWrite();
    }
}
