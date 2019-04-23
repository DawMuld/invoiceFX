/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.invoice;

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
public class InvoiceFileManager {

    private static ObservableList<Invoice> liveItems;

    public static void update(Invoice invoice) {
        try {
            File source = StorageManager.getInvoiceListFile();
            File temp = new File("temp");
            BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileWriter(temp));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == invoice.getPrimaryKey()) {
                    invoice.setLastMod(LocalDateTime.now());
                    String recLine = InvoiceParser.fromInvoice(invoice);
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

    public static void remove(Invoice invoice) {
        try {
            File exclude = StorageManager.getInvoiceExclude();
            PrintWriter writer = new PrintWriter(new FileWriter(exclude, true));
            writer.print(String.valueOf(invoice.getPrimaryKey()) + ";");
            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
        if (liveItems != null) {
            liveItems.remove(invoice);
        }
    }

    public static Invoice createNew() {
        int primeKey = 1;
        try {
            File file = StorageManager.getInvoiceListFile();
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
        return new Invoice(primeKey, LocalDateTime.now());
    }

    public static Invoice find(int primary_key) {
        Invoice result = null;
        try {
            File file = StorageManager.getInvoiceListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == primary_key) {
                    result = InvoiceParser.toInvoice(line);
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return result;
    }

    public static List<Invoice> collect(LineMatcher lineMatcher) {
        List<Invoice> list = new ArrayList<>();
        try {
            File file = StorageManager.getInvoiceListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineMatcher.collect(line)) {
                    list.add(InvoiceParser.toInvoice(line));
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Invoice> readAll() {
        List<Invoice> list = new ArrayList<>();
        try {
            File file = StorageManager.getInvoiceListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                Invoice invoice = InvoiceParser.toInvoice(line);
                if (invoice != null) {
                    list.add(invoice);
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Invoice> getItems() {
        if (liveItems == null) {
            liveItems = FXCollections.observableArrayList(readAll());
            List<Integer> excludeList = new ArrayList<>();
            try {
                File file = StorageManager.getInvoiceExclude();
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
                    Invoice invoice = liveItems.get(i);
                    if (excludeList.contains(invoice.getPrimaryKey())) {
                        liveItems.remove(i);
                    }
                }
            } catch (Exception e) {
            }
        }
        return liveItems;

    }

}
