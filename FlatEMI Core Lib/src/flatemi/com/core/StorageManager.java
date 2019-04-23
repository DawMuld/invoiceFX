/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.core;

import flatemi.com.pars.DateTimeParser;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author daan-
 */
public class StorageManager {

    private static final String PRODUCT_LIST_FILE = "\\products.listfile";
    private static final String CUSTOMER_LIST_FILE = "\\customers.listfile";
    private static final String INVOICE_LIST_FILE = "\\invoices.listfile";
    private static final String COMPANY_LIST_FILE = "\\companies.listfile";

    private static final String PRODUCT_EXCLUDE = "\\product.exclide";
    private static final String CUSTOMER_EXCLUDE = "\\customer.exclude";
    private static final String INVOICE_EXCLUDE = "\\invoice.exclude";
    private static final String COMPANY_EXCLUDE = "\\company.exclude";

    private static final String SESSION_LOG_FILE = "\\sessionlog.txt";

    public static File getProductListFile() {
        String path = getStoragePath() + PRODUCT_LIST_FILE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getCustomerListFile() {
        String path = getStoragePath() + CUSTOMER_LIST_FILE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getInvoiceListFile() {
        String path = getStoragePath() + INVOICE_LIST_FILE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getCompanyListFile() {
        String path = getStoragePath() + COMPANY_LIST_FILE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getCustomerExclude() {
        String path = getStoragePath() + CUSTOMER_EXCLUDE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getProductExclude() {
        String path = getStoragePath() + PRODUCT_EXCLUDE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getInvoiceExclude() {
        String path = getStoragePath() + INVOICE_EXCLUDE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getCompanyExclude() {
        String path = getStoragePath() + COMPANY_EXCLUDE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    public static File getSessionLogFile() {
        String path = getStoragePath() + SESSION_LOG_FILE;
        File file = new File(path);
        validateExistence(file);
        return file;
    }

    private static String getStoragePath() {
        File file = new File(System.getProperty("user.home") + "\\AppData\\local\\FlatEMI\\flatinvoice\\");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();

    }

    private static void validateExistence(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                SessionLog.println(DateTimeParser.makeLogStamp() + " :StorageManager error: Can't create new file and file does not exist " + file.getAbsolutePath());
            }
        }
    }
}
