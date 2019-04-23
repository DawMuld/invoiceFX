/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import flatemi.com.impl.customer.Customer;
import flatemi.com.impl.customer.CustomerFileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author daan-
 */
public class CustomerConverter {

    /**
     * Klantnr Naam Adres nummer	Postcode Woonplaats Land Telefoon Email
     *
     */
    public static void convertCustomerFile(File sourceFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
            String line;
            reader.readLine();
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] cells = line.split(";");
                String id = cells[0];
                String name = capitalize(cells[1]);
                String street = capitalize(cells[2]);
                String num = cells[3];
                String zip = cells[4];
                String city;
                if (cells[5].toLowerCase().contains("bosch")) {
                    city = "s-Hertogenbosch";
                } else {
                    city = capitalize(cells[5]);
                }
                String phone = "";
                String mail = "";
                if (cells.length >= 8) {
                    phone = cells[7];
                }
                if (cells.length >= 9) {
                    mail = cells[8];
                }
                Customer customer = CustomerFileManager.createNew();
                customer.setId(id);
                customer.setName(name);
                customer.setStreet(street);
                customer.setNum(num);
                customer.setZip(zip);
                customer.setCity(city);
                customer.setPhone(phone);
                customer.setEmail(mail);
                CustomerFileManager.update(customer);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String capitalize(String value) {
        String[] words = value.split("\\s");
        String retval = "";
        if (words.length > 0) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() > 0) {
                    if (words[i].length() > 1) {
                        retval += " " + words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
                    } else {
                        retval += " " + words[i].substring(0, 1).toUpperCase();
                    }
                }
            }
        }
        return retval;
    }

}
