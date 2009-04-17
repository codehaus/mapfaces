/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author kdelfour
 */
public class MyTreeItem {

    private int ident;
    private String title;
    private String description;
    private int reference;

    public MyTreeItem(int ident, String title, String description, int reference) {
        this.ident = ident;
        this.title = title;
        this.description = description;
        this.reference = reference;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return getTitle();
    }
    
}
