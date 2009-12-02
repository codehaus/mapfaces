/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.share.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.geotoolkit.wms.xml.AbstractKeyword;

/**
 * Thus utils class contains all methods that can be usefull to use.
 * @author Mehdi Sidhoum (Geomatys).
 */
public class Utilities {

    /**
     * Returns true if the list contains a string in one of the list elements.
     * @param list
     * @param str
     * @return
     */
    public static boolean matchesStringfromList(final List<String> list, final String str) {
        boolean strAvailable = false;
        for (final String s : list) {
            final Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE | Pattern.CANON_EQ);
            final Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                strAvailable = true;
            }
        }
        return strAvailable;
    }

    /**
     * Returns true if the list contains a keyword that matches with the string passed in arg (in one of the list elements).
     * @param list
     * @param str
     * @return
     */
    public static boolean matchesKeywordfromList(List<? extends AbstractKeyword> list, String str) {
        boolean strAvailable = false;
        for (AbstractKeyword k : list) {
            final Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE | Pattern.CANON_EQ);
            final Matcher matcher = pattern.matcher(k.getValue());
            if (matcher.find()) {
                strAvailable = true;
            }
        }
        return strAvailable;
    }

    /**
     * This method replace all special characters encoded by urlFormat to valid XML which can be marshalled by JAXB.
     * @param str
     * @return
     */
    public static String convertSpecialCharsToValidXml(String str) {
        if (str != null) {
            str = str.replaceAll("%3C", "<");
            str = str.replaceAll("%3E", ">");
            str = str.replaceAll("%3D", "=");
            str = str.replaceAll("%22", "\"");
            str = str.replaceAll("%2F", "/");
            str = str.replaceAll("%23", "#");
            str = str.replaceAll("%20", " ");
            str = str.replace('+', ' ');
        }
        return str;
    }

    /**
     * Encode a string from xml to a valid Url format.
     * @param str
     * @return
     */
    public static String convertSpecialCharsToUrlXml(String str) {
        if (str != null) {
            str = str.replaceAll("<", "%3C");
            str = str.replaceAll(">", "%3E");
            str = str.replaceAll("=", "%3D");
            str = str.replaceAll("\"", "%22");
            str = str.replaceAll("/", "%2F");
            str = str.replaceAll("#", "%23");
            str = str.replaceAll(" ", "%20");
            str = str.replace(' ', '+');
        }
        return str;
    }

    /**
     * This method returns a number of occurences occ in the string s.
     */
    public static int getOccurence(String s, String occ) {
        if (!s.contains(occ)) {
            return 0;
        } else {
            int nbocc = 0;
            while (s.indexOf(occ) != -1) {
                s = s.substring(s.indexOf(occ) + 1);
                nbocc++;
            }
            return nbocc;
        }
    }

    /**
     * Returns true if the connection passed in args is working, otherwise returns false.
     * a meta table is requested.
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static boolean isValid(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                PreparedStatement stmt = connection.prepareStatement("select * from pg_database limit 1");
                if (stmt != null && !stmt.isClosed()) {
                    stmt.executeQuery();
                    stmt.close();
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
