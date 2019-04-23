/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.mycustomers;

import flatemi.api.IndexedObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author daan-
 */
public class Tag {

    private final Set<IndexedObject> tagIndex = new HashSet<>();
    private final String label;

    public Tag(String label) {
        this.label = label;
    }

    public boolean isEmpty() {
        return tagIndex.isEmpty();
    }

    public int size() {
        return tagIndex.size();
    }

    public String label() {
        return label;
    }

    public boolean add(IndexedObject o) {
        if (!tagIndex.contains(o)) {
            tagIndex.add(o);
            return true;
        }
        return false;
    }

}
