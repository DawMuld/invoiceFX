/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.product;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author daan-
 */
public class ProductFileManager {

    private static ObservableList<Product> liveItems;

    public static void update(Product product) {
        try {
            File source = StorageManager.getProductListFile();
            File temp = new File("temp");
            BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileWriter(temp));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == product.getPrimaryKey()) {
                    product.setLastMod(LocalDateTime.now());
                    String recLine = ProductParser.fromProduct(product);
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

    public void remove(Product product) {
        try {
            File exclude = StorageManager.getProductExclude();
            PrintWriter writer = new PrintWriter(new FileWriter(exclude, true));
            writer.print(String.valueOf(product.getPrimaryKey()) + ";");
            writer.flush();
            writer.close();

        } catch (Exception e) {
        }
        if (liveItems != null) {
            liveItems.remove(product);
        }
    }

    public Product createNew() {
        int primeKey = 1;
        try {
            File file = StorageManager.getProductListFile();
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
        return new Product(primeKey, LocalDateTime.now());
    }

    public static Product find(int primary_key) {
        Product result = null;
        try {
            File file = StorageManager.getProductListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == primary_key) {
                    result = ProductParser.toProduct(line);
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return result;
    }

    public static List<Product> collect(LineMatcher lineMatcher) {
        List<Product> list = new ArrayList<>();
        try {
            File file = StorageManager.getProductListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                if (lineMatcher.collect(line)) {
                    Product product = ProductParser.toProduct(line);
                    if (product != null) {
                        list.add(product);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Product> readAll() {
        List<Product> list = new ArrayList<>();
        try {
            File file = StorageManager.getProductListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                Product product = ProductParser.toProduct(line);
                if (product != null) {
                    list.add(product);
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Product> getItems() {
        if (liveItems == null) {
            liveItems = FXCollections.observableArrayList(readAll());
            Set<Integer> excludeSet = new HashSet<>();
            try {
                File file = StorageManager.getProductExclude();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    String[] cells = line.split(";");
                    for (String cell : cells) {
                        String num = cell.replaceAll("\\D", "");
                        if (num.length() > 0) {
                            excludeSet.add(Integer.parseInt(num));
                        }
                    }
                }
                reader.close();
                for (int i = liveItems.size() - 1; i > -1; i--) {
                    Product product = liveItems.get(i);
                    if (excludeSet.contains(product.getPrimaryKey())) {
                        liveItems.remove(i);
                    }
                }
            } catch (Exception e) {
            }
        }
        return liveItems;
    }

}
