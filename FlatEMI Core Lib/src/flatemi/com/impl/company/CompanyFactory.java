/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.company;

import flatemi.api.EntryFactory;

/**
 *
 * @author daan-
 */
public class CompanyFactory extends EntryFactory<Company> {

    @Override
    public Company createNew(int primaryKey) {
        return new Company(primaryKey);
    }

}
