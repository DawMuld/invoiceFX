/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.customer;

import flatemi.api.IndexedObject;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author daan-
 */
public class Customer extends IndexedObject {

    private final int primary_key;
    private final LocalDateTime created;
    private LocalDateTime lastMod;

    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty street;
    private final SimpleStringProperty num;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty city;

    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty mobile;

    private final SimpleStringProperty taglist;

    protected Customer(int primaryKey, LocalDateTime created) {
        this.primary_key = primaryKey;
        this.created = created;
        this.lastMod = LocalDateTime.from(created);

        this.id = new SimpleStringProperty(String.valueOf(primary_key));
        this.name = new SimpleStringProperty("-");
        this.street = new SimpleStringProperty("-");
        this.num = new SimpleStringProperty("-");
        this.zip = new SimpleStringProperty("-");
        this.city = new SimpleStringProperty("-");

        this.email = new SimpleStringProperty("-");
        this.phone = new SimpleStringProperty("-");
        this.mobile = new SimpleStringProperty("-");

        this.taglist = new SimpleStringProperty("");
    }

    public Customer(int primaryKey, LocalDateTime created, LocalDateTime lastMod, String id, String name, String street, String num, String zip, String city, String email, String phone, String mobile, String taglist) {
        this.primary_key = primaryKey;
        this.created = created;
        this.lastMod = LocalDateTime.from(created);

        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.street = new SimpleStringProperty(street);
        this.num = new SimpleStringProperty(num);
        this.zip = new SimpleStringProperty(zip);
        this.city = new SimpleStringProperty(city);

        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.mobile = new SimpleStringProperty(mobile);

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

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty getstreetProp() {
        return street;
    }

    public String getNum() {
        return num.get();
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public StringProperty getNumProp() {
        return num;
    }

    public String getZip() {
        return zip.get();
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }

    public StringProperty getZipProp() {
        return zip;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty getCityProp() {
        return city;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty getEmailProp() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty getPhoneProp() {
        return phone;
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public StringProperty getMobileProp() {
        return mobile;
    }

    public String[] getTaglist() {
        if (taglist.get().contains(",")) {

            return taglist.get().split(",");
        } else {
            return new String[]{taglist.get()};
        }
    }

    public void addTag(String tag) {
        if (taglist.get().length() > 1) {
            taglist.set(taglist.get() + "," + tag);
        } else {
            taglist.set(tag);
        }
    }

    public StringProperty getTaglistProp() {
        return taglist;
    }

}
