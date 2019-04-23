/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.customer;

import flatemi.api.LineParser;
import flatemi.com.pars.DateTimeParser;
import flatemi.com.pars.TaglistParser;
import java.time.LocalDateTime;

/**
 *
 * @author daan-
 */
public class CustomerParser implements LineParser<Customer>{

    public static Customer toCustomer(String line) {
        String[] cells = line.split(";");
        int primeKey = Integer.parseInt(cells[0]);
        LocalDateTime created = DateTimeParser.toDateTime(cells[1]);
        LocalDateTime lastMod = DateTimeParser.toDateTime(cells[2]);

        String id, name, street, num, zip, city, email, phone, mobile, taglist;

        id = cells[3].trim();
        name = cells[4].trim();
        street = cells[5].trim();
        num = cells[6].trim();
        zip = cells[7].trim();
        city = cells[8].trim();
        email = "-";
        phone = "-";
        mobile = "-";
        taglist = "";
        if (cells.length > 9) {
            email = cells[9].trim();
            if (cells.length > 10) {
                phone = cells[10].trim();
                if (cells.length > 11) {
                    mobile = cells[11].trim();
                    if (cells.length > 12) {
                        taglist = cells[12].trim();
                    }
                }
            }
        }
        return new Customer(primeKey, created, lastMod, id, name, street, num, zip, city, email, phone, mobile, taglist);
    }

    public static String fromCustomer(Customer c) {
        String line = String.valueOf(c.getPrimaryKey()) + ";"
                + DateTimeParser.fromDateTime(c.getCreated()) + ";"
                + DateTimeParser.fromDateTime(c.getLastMod()) + ";"
                + c.getId() + ";"
                + c.getName() + ";"
                + c.getStreet() + ";"
                + c.getNum() + ";"
                + c.getZip() + ";"
                + c.getCity() + ";"
                + c.getEmail() + ";"
                + c.getPhone() + ";"
                + c.getMobile() + ";"
                + TaglistParser.fromTagList(c.getTaglist());
        return line;
    }

    @Override
    public Customer fromString(String q) {
        return toCustomer(q);
    }

    @Override
    public String toString(Customer q) {
        return fromCustomer(q);
    }
}
