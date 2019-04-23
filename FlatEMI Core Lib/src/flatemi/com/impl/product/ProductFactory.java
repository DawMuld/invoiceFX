/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.product;

import flatemi.api.EntryFactory;
import java.time.LocalDateTime;

/**
 *
 * @author daan-
 */
public class ProductFactory extends EntryFactory<Product> {

    @Override
    public Product createNew(int primaryKey) {
        return new Product(primaryKey, LocalDateTime.now());
    }

}
