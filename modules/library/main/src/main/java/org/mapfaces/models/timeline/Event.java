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

import org.geotoolkit.temporal.object.DefaultPeriod;
import org.geotoolkit.temporal.object.DefaultTemporalObject;

import org.mapfaces.models.Layer;

import org.opengis.temporal.Duration;
import org.opengis.temporal.Period;

/**
 * @author Mehdi Sidhoum.
 */
public class Event implements Serializable {

    private Date dateBegin;
    private Date dateEnd;
    private boolean topological;
    private String title;
    private String description;
    private String image;
    private String link;
    private String icon;
    private Layer owner;

    /**
     * This is a geometric primitive in the temporal dimension, it can be an instant or a period. see ISO19108 specificatios.
     */
    private DefaultTemporalObject temporalObject;
    /**
     * priority attribute take one of three values : Low, High or Normal, a Default value is assigned if nothing.
     * This attribute determine the icon of the event in the timeline component.
     */
    private Priority priority;
    /**
     * this can be represented by a css code for colors example #ffffff or white.
     */
    private String color;
    /**
     * status attribute take one of three values : Not started, In progress or default, this attribute determines the color of the event.
     */
    private Status status;
    /**
     * this can takes css code for colors.
     */
    private String textColor;

    /**
     * This is a duration to define hotzones for iterators events during a period, @code null if no.
     */
    private Duration duration;

    /**
     * creates a new instance of Event with two Date objects begin and end.
     */
    public Event(Date dateBegin, Date dateEnd, Duration duration, boolean isTopological, String title,
            String description, String image, String link, String icon, Priority priority,
            String color, Status status, String textColor, Layer owner) {

        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.duration = duration;
        this.topological = isTopological;
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.icon = icon;
        this.priority = priority;
        this.color = color;
        this.status = status;
        this.textColor = textColor;
        this.owner = owner;
    }

    /**
     * creates a new instance of Event with a TemporalObject, to introduce the implementation of specifications ISO 19108.
     */
    public Event(DefaultTemporalObject temporalObject, boolean isTopological, String title,
            String description, String image, String link, String icon, Priority priority,
            String color, Status status, String textColor, Layer owner) {

        this.temporalObject = temporalObject;
        this.topological = isTopological;
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.icon = icon;
        this.priority = priority;
        this.color = color;
        this.status = status;
        this.textColor = textColor;
        this.owner = owner;
    }

    public Event(Period period, Duration duration, boolean isTopological, String title,
            String description, String image, String link, String icon, Priority priority,
            String color, Status status, String textColor, Layer owner) {
        this.temporalObject = (DefaultPeriod) period;
        this.duration = duration;
        this.topological = isTopological;
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.icon = icon;
        this.priority = priority;
        this.color = color;
        this.status = status;
        this.textColor = textColor;
        this.owner = owner;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(final Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(final Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isTopological() {
        return topological;
    }

    public void setDuration(final boolean isDuration) {
        this.topological = isDuration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(final String textColor) {
        this.textColor = textColor;
    }

    public DefaultTemporalObject getTemporalObject() {
        return temporalObject;
    }

    public void setTemporalObject(final DefaultTemporalObject temporalObject) {
        this.temporalObject = temporalObject;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    public Layer getOwner() {
        return owner;
    }

    public void setOwner(final Layer owner) {
        this.owner = owner;
    }
}
