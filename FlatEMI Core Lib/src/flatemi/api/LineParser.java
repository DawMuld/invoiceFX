/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.api;

/**
 *
 * @author daan-
 * @param <T>
 */
public interface LineParser<T extends IndexedObject> {

    public T fromString(String q);

    public String toString(T q);

}
