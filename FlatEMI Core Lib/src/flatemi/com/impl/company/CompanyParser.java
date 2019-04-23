/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.company;

import flatemi.api.LineParser;

/**
 *
 * @author daan-
 */
public class CompanyParser implements LineParser<Company> {

    @Override
    public Company fromString(String q) {
        return toCompany(q);
    }

    @Override
    public String toString(Company q) {
        return fromCompany(q);
    }

    public static Company toCompany(String q) {
        String[] cells = q.split(";");
        if (cells.length < 10) {
            return null;
        }
        return new Company(Integer.parseInt(cells[0]), cells[1], cells[2], Integer.parseInt(cells[3]), cells[4], cells[5], cells[6], cells[7], cells[8], cells[9]);
    }

    public static String fromCompany(Company company) {
        if(company == null){return "null";}
        String line = String.valueOf(company.getPrimaryKey()) + ";"
                + company.getName() + ";"
                + company.getStreet() + ";"
                + String.valueOf(company.getNum()) + ";"
                + company.getZip() + ";"
                + company.getCity() + ";"
                + company.getPhone() + ";"
                + company.getIban() + ";"
                + company.getBtw() + ";"
                + company.getKvk();
        return line;
    }

}
