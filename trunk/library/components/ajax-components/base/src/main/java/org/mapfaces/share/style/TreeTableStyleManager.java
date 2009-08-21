package org.mapfaces.share.style;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kevindelfour
 */
public class TreeTableStyleManager {

    private final static Map<String, String> CSS_STYLES;

    static {

        final HashMap<String, String> styles = new HashMap<String, String>();
        styles.put("NODE_STYLE", "treenode");
        styles.put("NODE_EXPAND_STYLE", "treeicon treenode-expanded");
        styles.put("NODE_COLLAPSE_STYLE", "treeicon treenode-collapsed");
        styles.put("NODE_EXPAND_SYMBOLE", "treeicon treenode-expand_symbol");
        styles.put("NODE_COLLAPSE_SYMBOLE", "treeicon treenode-collapse_symbol");
        styles.put("LEAF_STYLE", "treeleaf");
        styles.put("LEAF_EXPAND_STYLE", "treeicon treeleaf-expanded");
        styles.put("LEAF_COLLAPSE_STYLE", "treeicon treeleaf-collapsed");
        styles.put("ITEM_STYLE", "treeitem");
        styles.put("ELBOW_STYLE", "treeicon treeleaf-elbow");
        styles.put("ELBOW_LINE_STYLE", "treeicon treeelbow-line");
        styles.put("ELBOW_END_STYLE", "treeicon treeleaf-elbow-end");
        styles.put("UL_CLASS", "ul-pattern");

        styles.put("DEFAULT_WIDTH_COLUMN_VALUE", "150px");

        CSS_STYLES = Collections.unmodifiableMap(styles);

    }

    public enum Key {

        NODE_STYLE("NODE_STYLE"),
        NODE_EXPAND_STYLE("NODE_EXPAND_STYLE"),
        NODE_COLLAPSE_STYLE("NODE_COLLAPSE_STYLE"),
        NODE_EXPAND_SYMBOLE("NODE_EXPAND_SYMBOLE"),
        NODE_COLLAPSE_SYMBOLE("NODE_COLLAPSE_SYMBOLE"),
        LEAF_STYLE("LEAF_STYLE"),
        LEAF_EXPAND_STYLE("LEAF_EXPAND_STYLE"),
        LEAF_COLLAPSE_STYLE("LEAF_COLLAPSE_STYLE"),
        ITEM_STYLE("ITEM_STYLE"),
        ELBOW_STYLE("ELBOW_STYLE"),
        ELBOW_LINE_STYLE("ELBOW_LINE_STYLE"),
        ELBOW_END_STYLE("ELBOW_END_STYLE"),
        DEFAULT_WIDTH_COLUMN_VALUE("DEFAULT_WIDTH_COLUMN_VALUE"),
        UL_CLASS("UL_CLASS");
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
