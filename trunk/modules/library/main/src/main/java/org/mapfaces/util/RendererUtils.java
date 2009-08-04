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

package org.mapfaces.util;

/**
 * This class extends the RendererUtils from A4J project
 * @author Olivier Terral (Geomatys)
 */
public class RendererUtils extends org.ajax4jsf.framework.renderer.RendererUtils{

	public interface HTML extends org.ajax4jsf.framework.renderer.RendererUtils.HTML{

		public static final String IMG_ELEM = "img";
		public static final String src_ATTRIBUTE = "src";
		public static final String TEXTJAVASCRIPT_VALUE = "text/javascript";
    }

}
