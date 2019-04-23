/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.invoice;

import flatemi.com.impl.product.Product;

/**
 *
 * @author daan-
 */
public class InvoiceItem {

    private int productKey;
    private int quantity;
    private double price;

    public InvoiceItem(int productKey, int quantity, double price) {
        this.productKey = productKey;
        this.quantity = quantity;
        this.price = price;
    }

    public InvoiceItem(Product product) {
        this.productKey = product.getPrimaryKey();
        this.quantity = 1;
        this.price = product.getSellingPrice();
    }

    public double getTotalExBtw() {
        return (double) quantity * price;
    }

    public int getProductKey() {
        return productKey;
    }

    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
