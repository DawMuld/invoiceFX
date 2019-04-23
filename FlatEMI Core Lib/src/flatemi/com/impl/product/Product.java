/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.product;

import flatemi.api.IndexedObject;
import flatemi.com.pars.TaglistParser;
import java.time.LocalDateTime;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author daan-
 */
public class Product extends IndexedObject {

    private final int primary_key;
    private final LocalDateTime created;
    private LocalDateTime lastMod;

    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty brand;
    private final SimpleDoubleProperty purchasePrice;
    private final SimpleDoubleProperty sellingPrice;
    private final SimpleStringProperty details;

    private final SimpleStringProperty taglist;

    protected Product(int primaryKey, LocalDateTime created) {
        this.primary_key = primaryKey;
        this.created = created;
        this.lastMod = LocalDateTime.from(created);

        this.id = new SimpleStringProperty("-");
        this.name = new SimpleStringProperty("-");
        this.brand = new SimpleStringProperty("-");
        this.purchasePrice = new SimpleDoubleProperty(0.0);
        this.sellingPrice = new SimpleDoubleProperty(0.0);
        this.details = new SimpleStringProperty("-");

        this.taglist = new SimpleStringProperty("-");
    }

    public Product(int primaryKey, LocalDateTime created, LocalDateTime lastMod, String id, String name, String brand, double purchasePrice, double sellingPrice, String details, String taglist) {
        this.primary_key = primaryKey;
        this.created = created;
        this.lastMod = lastMod;

        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
        this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
        this.sellingPrice = new SimpleDoubleProperty(sellingPrice);
        this.details = new SimpleStringProperty(details);

        this.taglist = new SimpleStringProperty(taglist);
    }

    @Override
    public int getPrimaryKey() {
        return primary_key;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public LocalDateTime getLastMod() {
        return lastMod;
    }

    @Override
    public void setLastMod(LocalDateTime lastMod) {
        this.lastMod = lastMod;
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

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getNameProp() {
        return name;
    }

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public StringProperty getBrandProp() {
        return brand;
    }

    public double getPurchasePrice() {
        return purchasePrice.get();
    }

    public void setPurchasePrice(double price) {
        this.purchasePrice.set(price);
    }

    public DoubleProperty getPurchasePriceProp() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(double price) {
        this.sellingPrice.set(price);
    }

    public DoubleProperty getSellingPriceProp() {
        return sellingPrice;
    }

    public String getDetails() {
        return details.get();
    }

    public void setDetails(String details) {
        this.details.set(details);
    }

    public StringProperty getDetailsProp() {
        return details;
    }

    public String[] getTaglist() {
        return TaglistParser.toTagList(taglist.get());
    }

    public void addTag(String tag) {
        if (null != tag&& null != taglist.get()) {
            
            Set<String> set = TaglistParser.toTagSet(taglist.get());
            if (!set.contains(tag)) {
                
                    taglist.set(taglist.get() + "," + tag);
               
                    
                
            }
        }else if(null != tag){
            taglist.set(tag);
        }
        
    }

    public StringProperty getTaglistProp() {
        return taglist;
    }
}
