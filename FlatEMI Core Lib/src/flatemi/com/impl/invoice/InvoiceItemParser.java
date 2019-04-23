/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.invoice;

import flatemi.com.impl.invoice.InvoiceItem;
import java.text.DecimalFormat;

/**
 *
 * @author daan-
 */
public class InvoiceItemParser {

    public static final DecimalFormat KEY_FORMATTER = new DecimalFormat("00000");
    public static final DecimalFormat Q_FORMATTER = new DecimalFormat("000");
    public static final DecimalFormat PRICE_FORMATTER = new DecimalFormat("#000");
    public static final int MIN_CELL_LENGTH = 11;

    public static String toItemListString(InvoiceItem[] array) {
        if (array == null || array.length <= 0) {
            return "";
        }
        String itemListCell = "";
        for (InvoiceItem item : array) {
            itemListCell += fromInvoiceItem(item) + ",";
        }
        return itemListCell;
    }

    public static InvoiceItem toInvoiceItem(String cell) {
        String item = cell.replaceAll("\\D", "");
        if (item.length() < MIN_CELL_LENGTH) {
            System.out.println("cannot parse Invoice item! " + cell);
            return null;
        }
        int productKey = Integer.parseInt(cell.substring(0, 5));
        int q = Integer.parseInt(cell.substring(5, 8));
        int value = Integer.parseInt(cell.substring(8));
        double price = toInvoiceItemPrice(value);
        return new InvoiceItem(productKey, q, price);

    }

    public static String fromInvoiceItem(InvoiceItem item) {
        String cell = KEY_FORMATTER.format(item.getProductKey())
                + Q_FORMATTER.format(item.getQuantity())
                + PRICE_FORMATTER.format(fromInvoiceItemPrice(item.getPrice()));
        return cell;
    }

    public static final int fromInvoiceItemPrice(double price) {
        double normalized = 100.0 * price;
        int value = (int) normalized;
        return value;
    }

    public static final double toInvoiceItemPrice(int value) {
        double dVal = (double) value * 1.0;
        if (dVal == 0.0) {
            return 0.0;
        } else {
            return dVal / 100.0;
        }
    }

}
