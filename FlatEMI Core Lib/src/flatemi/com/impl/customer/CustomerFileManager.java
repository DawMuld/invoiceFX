/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.customer;

import flatemi.api.LineMatcher;
import flatemi.com.pars.PrimeKeyParser;
import flatemi.com.core.StorageManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author daan-
 */
public class CustomerFileManager {

    private static ObservableList<Customer> liveItems;

    public static void update(Customer customer) {
        try {
            File source = StorageManager.getCustomerListFile();
            File temp = new File("temp");
            BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileWriter(temp));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == customer.getPrimaryKey()) {
                    customer.setLastMod(LocalDateTime.now());
                    String recLine = CustomerParser.fromCustomer(customer);
                    writer.println(recLine);
                } else {
                    writer.println(line);
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (Exception e) {
        }
    }

    public static void remove(Customer customer) {
        try {
            File exclude = StorageManager.getCustomerExclude();
            PrintWriter writer = new PrintWriter(new FileWriter(exclude, true));
            writer.print(String.valueOf(customer.getPrimaryKey()) + ";");
            writer.flush();
            writer.close();

        } catch (Exception e) {
        }
        if (liveItems != null) {
            liveItems.remove(customer);
        }
    }

    public static Customer createNew() {
        int primeKey = 1;
        File file = StorageManager.getCustomerListFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey != -1) {
                    primeKey = lineKey + 1;
                }
            }
            reader.close();
            
        } catch (Exception e) {
        }
        
        Customer customer = new Customer(primeKey, LocalDateTime.now());
        
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(CustomerParser.fromCustomer(customer));
            writer.flush();
            writer.close();
        }catch(Exception e){}
        
        if (liveItems != null) {
            liveItems.add(customer);
        }
        return customer;
    }

    public static Customer find(int primary_key) {
        Customer result = null;
        try {
            File file = StorageManager.getCustomerListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == primary_key) {
                    result = CustomerParser.toCustomer(line);
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return result;
    }

    public static List<Customer> collect(LineMatcher lineMatcher) {
        List<Customer> list = new ArrayList<>();
        try {
            File file = StorageManager.getCustomerListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                if (lineMatcher.collect(line)) {
                    list.add(CustomerParser.toCustomer(line));
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Customer> readAll() {
        List<Customer> list = new ArrayList<>();
        try {
            File file = StorageManager.getCustomerListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
                Customer customer = CustomerParser.toCustomer(line);
                if (customer != null) {
                    list.add(customer);
                }else{
                    System.out.println("NULL CUSTOMER FOR : " + line);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Customer> getItems() {
        if (liveItems == null) {
            liveItems = FXCollections.observableArrayList(readAll());
            List<Integer> excludeList = new ArrayList<>();
            try {
                File file = StorageManager.getCustomerExclude();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    String[] cells = line.split(";");
                    for (String cell : cells) {
                        String num = cell.replaceAll("\\D", "");
                        if (num.length() > 0) {
                            excludeList.add(Integer.parseInt(num));
                        }
                    }
                }
                reader.close();
                for (int i = liveItems.size() - 1; i > -1; i--) {
                    Customer customer = liveItems.get(i);
                    if (excludeList.contains(customer.getPrimaryKey())) {
                        liveItems.remove(i);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return liveItems;
    }

}
