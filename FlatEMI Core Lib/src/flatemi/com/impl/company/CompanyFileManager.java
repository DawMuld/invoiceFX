/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.company;

import flatemi.api.LineMatcher;
import flatemi.com.core.SessionLog;
import flatemi.com.pars.PrimeKeyParser;
import flatemi.com.core.StorageManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author daan-
 */
public class CompanyFileManager {

    private static ObservableList<Company> liveItems;

    public static void update(Company entry) {
        try {
            File source = StorageManager.getCompanyListFile();
            File temp = new File("temp");
            BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileWriter(temp));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = Integer.parseInt(line.split(";")[0]);
                if (lineKey == entry.getPrimaryKey()) {
                    writer.println(CompanyParser.fromCompany(entry));
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
            SessionLog.println("Company update Error for \t" + CompanyParser.fromCompany(entry));
        }
    }

    public static void remove(Company entry) {
        try {
            File exclude = StorageManager.getCompanyExclude();
            PrintWriter writer = new PrintWriter(new FileWriter(exclude, true));
            writer.print(String.valueOf(entry.getPrimaryKey()) + ";");
            writer.flush();
            writer.close();
            
        } catch (Exception e) {

        }
        if (liveItems != null) {
            liveItems.remove(entry);
        }
    }

    public static Company createNew() {
        int primeKey = 1;
        try {
            File file = StorageManager.getCompanyListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey != -1) {
                    primeKey = 1 + lineKey;
                }
            }
        } catch (Exception e) {
        }
        Company company = new Company(primeKey);
        if (liveItems != null) {
            if (!liveItems.contains(company)) {
                liveItems.add(company);
            }
        }
        return company;
    }

    public static Company find(int primary_key) {
        Company result = null;
        try {
            File file = StorageManager.getCompanyListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                int lineKey = PrimeKeyParser.keyFromLine(line);
                if (lineKey == primary_key) {
                    result = CompanyParser.toCompany(line);
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return result;
    }

    public static List<Company> collect(LineMatcher lineMatcher) {
        List<Company> result = new ArrayList<>();
        try {
            File file = StorageManager.getCompanyListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                if (lineMatcher.collect(line)) {
                    result.add(CompanyParser.toCompany(line));
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return result;
    }

    public static List<Company> readAll() {
        List<Company> list = new ArrayList<>();
        try {
            File file = StorageManager.getCompanyListFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                Company company = CompanyParser.toCompany(line);
                if (company != null) {
                    list.add(company);
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<Company> getItems() {
        if (liveItems == null) {
            liveItems = FXCollections.observableArrayList(readAll());
            List<Integer> excludeList = new ArrayList<>();
            try {
                File file = StorageManager.getCompanyExclude();
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
                    Company comp = liveItems.get(i);
                    if (excludeList.contains(comp.getPrimaryKey())) {
                        liveItems.remove(i);
                    }
                }
            } catch (Exception e) {
            }

        }
        return liveItems;
    }

}
