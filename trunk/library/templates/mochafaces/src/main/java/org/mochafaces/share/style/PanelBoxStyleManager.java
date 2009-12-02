/*
 * MDweb - Open Source tool for cataloging and locating environmental resources
 *         http://mdweb.codehaus.org
 *
 *   Copyright (c) 2007-2009, Institut de Recherche pour le Développement (IRD)
 *   Copyright (c)      2009, Geomatys
 *
 * This file is part of MDweb.
 *
 * MDweb is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation;
 *   version 3.0 of the License.
 *
 * MDweb is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details:
 *         http://www.gnu.org/licenses/lgpl-3.0.html
 *
 */
package org.mochafaces.share.style;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kevindelfour
 */
public class PanelBoxStyleManager {

    private final static Map<String, String> CSS_STYLES;

    static {
        HashMap<String, String> styles = new HashMap<String, String>();
        styles.put("DEFAULT_PANELBOX_STYLE", "desktopPanelBox");
        styles.put("DEFAULT_PANELBOXHEADER_STYLE", "panel-header");
        styles.put("DEFAULT_PANELBOXHEADERCONTENT_STYLE", "panel-headerContent");
        styles.put("DEFAULT_PANELBOXTOOLBOX_STYLE", "toolbox");
        styles.put("DEFAULT_PANELBOX_BUTTON_SIZE", "width: 16px; height: 16px;");
        styles.put("DEFAULT_PANELBOX_BUTTON_COLL_STYLE", "panel-collapse icon16");
        styles.put("DEFAULT_PANELBOX_BUTTON_EXP_STYLE", "panel-expand icon16");
        styles.put("DEFAULT_PANELBOXCONTENT_STYLE", "desktopPanelContent");
        CSS_STYLES = Collections.unmodifiableMap(styles);
    }

    public enum Key {

        DEFAULT_PANELBOX_STYLE("DEFAULT_PANELBOX_STYLE"),
        DEFAULT_PANELBOXHEADER_STYLE("DEFAULT_PANELBOXHEADER_STYLE"),
        DEFAULT_PANELBOXHEADERCONTENT_STYLE("DEFAULT_PANELBOXHEADERCONTENT_STYLE"),
        DEFAULT_PANELBOXTOOLBOX_STYLE("DEFAULT_PANELBOXTOOLBOX_STYLE"),
        DEFAULT_PANELBOX_BUTTON_SIZE("DEFAULT_PANELBOX_BUTTON_SIZE"),
        DEFAULT_PANELBOX_BUTTON_COLL_STYLE("DEFAULT_PANELBOX_BUTTON_COLL_STYLE"),
        DEFAULT_PANELBOX_BUTTON_EXP_STYLE("DEFAULT_PANELBOX_BUTTON_EXP_STYLE"),
        DEFAULT_PANELBOXCONTENT_STYLE("DEFAULT_PANELBOXCONTENT_STYLE");

        private String key;

        Key(String key) {
            this.key = key;
        }

        public String value() {
            return this.key;
        }
    }

    public static String getAttributes(Key key) {
        return CSS_STYLES.get(key.value());
    }
}
