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
public abstract class EntryFactory<T extends IndexedObject> {

    public abstract T createNew(int primaryKey);

}
