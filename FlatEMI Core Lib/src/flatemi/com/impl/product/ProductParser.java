/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.product;

import flatemi.api.LineParser;
import flatemi.com.pars.DateTimeParser;
import flatemi.com.pars.PriceParser;
import flatemi.com.pars.TaglistParser;
import java.time.LocalDateTime;

/**
 *
 * @author daan-
 */
public class ProductParser implements LineParser<Product> {

    public static Product toProduct(String line) {
        String[] cells = line.split(";");
        int primeKey = Integer.parseInt(cells[0]);
        LocalDateTime created = DateTimeParser.toDateTime(cells[1]);
        LocalDateTime lastMod = DateTimeParser.toDateTime(cells[2]);

        String id, name, brand, details, taglist;
        double purchasePrice, sellingPrice;

        id = cells[3];
        name = cells[4];
        brand = cells[5];
        purchasePrice = PriceParser.toPrice(cells[6]);
        sellingPrice = PriceParser.toPrice(cells[7]);
        details = cells[8];
        taglist = cells[9];
        return new Product(primeKey, created, lastMod, id, name, brand, purchasePrice, sellingPrice, details, taglist);
    }

    public static String fromProduct(Product p) {
        String line = String.valueOf(p.getPrimaryKey()) + ";"
                + DateTimeParser.fromDateTime(p.getCreated()) + ";"
                + DateTimeParser.fromDateTime(p.getLastMod()) + ";"
                + p.getId() + ";"
                + p.getName() + ";"
                + p.getBrand() + ";"
                + PriceParser.fromPrice(p.getPurchasePrice()) + ";"
                + PriceParser.fromPrice(p.getSellingPrice()) + ";"
                + p.getDetails() + ";"
                + TaglistParser.fromTagList(p.getTaglist());
        return line;
    }

    @Override
    public Product fromString(String q) {
        return toProduct(q);
    }

    @Override
    public String toString(Product q) {
        return fromProduct(q);
    }

}
