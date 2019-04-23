/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.invoice;

import flatemi.api.IndexedObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author daan-
 */
public class Invoice extends IndexedObject {

    private final int primary_key;
    private final LocalDateTime created;
    private LocalDateTime lastMod;
    private LocalDate date;

    private final SimpleStringProperty id;
    private final SimpleIntegerProperty companyKey;
    private final SimpleIntegerProperty customerKey;

    private final SimpleDoubleProperty tax;
    private final SimpleIntegerProperty paymentTerm;

    private final ObservableList<InvoiceItem> itemList;

    protected Invoice(int primaryKey, LocalDateTime created) {
        this.primary_key = primaryKey;
        this.created = created;
        this.lastMod = LocalDateTime.from(created);
        this.id = new SimpleStringProperty(String.valueOf(primary_key));
        this.date = LocalDate.now();
        this.companyKey = new SimpleIntegerProperty(-1);
        this.customerKey = new SimpleIntegerProperty(-1);
        this.tax = new SimpleDoubleProperty(0.21);
        this.paymentTerm = new SimpleIntegerProperty(14);
        this.itemList = FXCollections.observableArrayList();
    }

    public Invoice(int primaryKey, LocalDateTime created, LocalDateTime lastMod, String id, LocalDate date, int companyKey, int customerKey, double tax, int paymentTerm, List<InvoiceItem> itemList) {
        this.primary_key = primaryKey;
        this.created = created;
        this.lastMod = lastMod;
        this.id = new SimpleStringProperty(id);
        this.date = date;
        this.companyKey = new SimpleIntegerProperty(companyKey);
        this.customerKey = new SimpleIntegerProperty(customerKey);
        this.tax = new SimpleDoubleProperty(tax);
        this.paymentTerm = new SimpleIntegerProperty(paymentTerm);
        this.itemList = FXCollections.observableArrayList(itemList);
    }

    @Override
    public int getPrimaryKey() {
        return primary_key;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastMod() {
        return lastMod;
    }

    public void setLastMod(LocalDateTime lastMod) {
        this.lastMod = lastMod;
    }

    public LocalDate getDate() {
        return date;

    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty getIdProp() {
        return id;
    }

    public int getCompanyKey() {
        return companyKey.get();
    }

    public void setCompanyKey(int key) {
        this.companyKey.set(key);
    }

    public IntegerProperty getCompaneyKeyProp() {
        return companyKey;
    }

    public int getCustomerKey() {
        return customerKey.get();
    }

    public void setCustomerKey(int key) {
        this.customerKey.set(key);
    }

    public IntegerProperty getCustomerKeyProp() {
        return customerKey;
    }

    public double getTax() {
        return tax.get();
    }

    public void setTax(double tax) {
        this.tax.set(tax);
    }

    public DoubleProperty getTaxProp() {
        return tax;
    }

    public int getPaymentTerm() {
        return paymentTerm.get();
    }

    public void setPaymentTerm(int days) {
        this.paymentTerm.set(days);
    }

    public IntegerProperty getPaymentTermProp() {
        return paymentTerm;
    }

    public InvoiceItem[] getItemArray() {
        return itemList.toArray(new InvoiceItem[itemList.size()]);
    }

    public ObservableList<InvoiceItem> getItemList() {
        return itemList;
    }

    public double getTotalEx() {
        double total = 0.0;
        for (int i = 0; i < itemList.size(); i++) {
            total += itemList.get(i).getTotalExBtw();
        }
        return total;
    }

    public double getTotalTax() {
        return tax.get() * getTotalEx();
    }

    public double getTotal() {
        return (1.0 + tax.get()) * getTotalEx();
    }

    public void addItem(InvoiceItem item) {
        InvoiceItem hold = findByKey(item.getProductKey());
        if (hold == null) {
            itemList.add(item);
        } else {
            int q = hold.getQuantity() + item.getQuantity();
            hold.setQuantity(q);
        }
    }

    public void removeItem(InvoiceItem item) {
        InvoiceItem hold = findByKey(item.getProductKey());
        if (hold != null) {
            int q = hold.getQuantity() - item.getQuantity();
            if (q > 0) {
                hold.setQuantity(q);
            } else {
                itemList.remove(hold);
            }
        }
    }

    public InvoiceItem findByKey(int key) {
        InvoiceItem item = null;
        if (!itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getProductKey() == key) {
                    item = itemList.get(i);
                    break;
                }
            }
        }
        return item;
    }
}
