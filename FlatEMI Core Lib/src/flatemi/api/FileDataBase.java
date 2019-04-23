/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.api;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author daan-
 * @param <T>
 */
public interface FileDataBase<T extends IndexedObject> {

    public void update(T entry);

    public void remove(T entry);

    public T createNew();

    public T find(int primary_key);

    public List<T> collect(LineMatcher lineMatcher);

    public List<T> readAll();

    public ObservableList<T> getItems();
}
