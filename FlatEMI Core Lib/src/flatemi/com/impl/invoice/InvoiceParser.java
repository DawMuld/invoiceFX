/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.invoice;

import flatemi.com.impl.invoice.InvoiceItemParser;
import flatemi.com.impl.invoice.Invoice;
import flatemi.com.impl.invoice.InvoiceItem;
import flatemi.com.pars.DateTimeParser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daan-
 */
public class InvoiceParser {

    public static Invoice toInvoice(String line) {
        String[] cells = line.split(";");
        int primaryKey = Integer.parseInt(cells[0]);
        LocalDateTime created = DateTimeParser.toDateTime(cells[1]);
        LocalDateTime lastMod = DateTimeParser.toDateTime(cells[2]);

        String id = cells[3];
        LocalDate date = DateTimeParser.fromDateFormat(cells[4]);
        int companyKey = Integer.parseInt(cells[5]);
        int customerKey = Integer.parseInt(cells[6]);
        if (cells[7].contains(",")) {
            cells[7] = cells[7].replace(",", ".");
        }
        double tax = Double.parseDouble(cells[7]);
        int paymentTerm = Integer.parseInt(cells[8]);
        String itemListCell = cells[9];
        String[] itemCells;
        if (itemListCell.length() > 0) {
            if (itemListCell.contains(",")) {
                itemCells = itemListCell.split(",");
            } else {
                itemCells = new String[]{itemListCell};
            }
        } else {
            itemCells = new String[]{};
        }
        List<InvoiceItem> itemList = new ArrayList<>();
        if (itemCells.length > 0) {
            for (String itemCell : itemCells) {
                InvoiceItem item = InvoiceItemParser.toInvoiceItem(itemCell);
                if (item != null) {
                    itemList.add(item);
                }
            }
        }
        return new Invoice(primaryKey, created, lastMod, id, date, companyKey, customerKey, tax, paymentTerm, itemList);
    }

    public static String fromInvoice(Invoice i) {
        String line = String.valueOf(i.getPrimaryKey()) + ";"
                + DateTimeParser.fromDateTime(i.getCreated()) + ";"
                + DateTimeParser.fromDateTime(i.getLastMod()) + ";"
                + DateTimeParser.toDateFormat(i.getDate()) + ";"
                + i.getId() + ";"
                + String.valueOf(i.getCompanyKey()) + ";"
                + String.valueOf(i.getCustomerKey()) + ";"
                + String.valueOf(i.getTax()) + ";"
                + String.valueOf(i.getPaymentTerm()) + ";"
                + InvoiceItemParser.toItemListString(i.getItemArray());
        return line;
    }

}
