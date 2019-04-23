/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import flatemi.api.ListFileDataBase;
import flatemi.api.ListFileDatabaseFactory;
import flatemi.com.impl.company.Company;

/**
 *
 * @author daan-
 */
public class CompanyInstaller {
    
    
    public static void main(String[] args){
        ListFileDataBase<Company> db = ListFileDatabaseFactory.getCompanyDB();
        Company comp = db.createNew();
        comp.setBtw("1480.21.724.B03");
        comp.setCity("'s-Hertogenbosch");
        comp.setIban("NL74 ABNA 05233 9768");
        comp.setName("Chimneytotaal");
        comp.setNum(82);
        comp.setPhone("+316 22 77 13 83");
        comp.setStreet("Titaniumlaan");
        comp.setZip("5221 CK");
        db.update(comp);
                
    
    }
    
}
