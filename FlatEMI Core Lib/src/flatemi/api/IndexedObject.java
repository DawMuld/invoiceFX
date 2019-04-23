/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.api;

import java.time.LocalDateTime;

/**
 *
 * @author daan-
 */
public abstract class IndexedObject {
    

    public abstract int getPrimaryKey();

    public abstract void setLastMod(LocalDateTime ldt);

    public abstract LocalDateTime getLastMod();

}
