/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.customer;

import flatemi.api.EntryFactory;
import java.time.LocalDateTime;

/**
 *
 * @author daan-
 */
public class CustomerFactory extends EntryFactory<Customer> {

    @Override
    public Customer createNew(int primaryKey) {
        return new Customer(primaryKey, LocalDateTime.now());
    }

}
