/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.impl.company;

import flatemi.api.IndexedObject;
import java.time.LocalDateTime;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author daan-
 */
public class Company extends IndexedObject {

    private final ReadOnlyIntegerProperty primary_key;
    private final SimpleStringProperty name;
    private final SimpleStringProperty street;
    private final SimpleIntegerProperty num;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty city;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty iban;
    private final SimpleStringProperty btw;
    private final SimpleStringProperty kvk;

    protected Company(int primaryKey) {
        primary_key = new ReadOnlyIntegerWrapper(primaryKey);
        name = new SimpleStringProperty("-");
        street = new SimpleStringProperty("-");
        num = new SimpleIntegerProperty(0);
        zip = new SimpleStringProperty("-");
        city = new SimpleStringProperty("-");
        phone = new SimpleStringProperty("-");
        iban = new SimpleStringProperty("-");
        btw = new SimpleStringProperty("-");
        kvk = new SimpleStringProperty("-");
    }

    public Company(int primaryKey, String name, String street, int num, String zip, String city, String phone, String iban, String btw, String kvk) {
        this.primary_key = new ReadOnlyIntegerWrapper(primaryKey);
        this.name = new SimpleStringProperty(name);
        this.street = new SimpleStringProperty(street);
        this.num = new SimpleIntegerProperty(num);
        this.zip = new SimpleStringProperty(zip);
        this.city = new SimpleStringProperty(city);
        this.phone = new SimpleStringProperty(phone);
        this.iban = new SimpleStringProperty(iban);
        this.btw = new SimpleStringProperty(btw);
        this.kvk = new SimpleStringProperty(kvk);
    }

    @Override
    public int getPrimaryKey() {
        return primary_key.get();
    }

    @Override
    public void setLastMod(LocalDateTime ldt) {

    }

    @Override
    public LocalDateTime getLastMod() {
        return LocalDateTime.MIN;
    }

    public ReadOnlyIntegerProperty getPrimaryKeyProp() {
        return primary_key;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty getNameProp() {
        return name;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public SimpleStringProperty getStreetProp() {
        return street;
    }

    public int getNum() {
        return num.get();
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public SimpleIntegerProperty getNumProp() {
        return num;
    }

    public String getZip() {
        return zip.get();
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }

    public SimpleStringProperty getZipProp() {
        return zip;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public SimpleStringProperty getCityProp() {
        return city;
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty getPhoneProp() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getIban() {
        return iban.get();
    }

    public SimpleStringProperty getIbanProp() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban.set(iban);
    }

    public String getBtw() {
        return btw.get();
    }

    public SimpleStringProperty getBtwProp() {
        return btw;
    }

    public void setBtw(String btw) {
        this.btw.set(btw);
    }

    public String getKvk() {
        return kvk.get();
    }

    public SimpleStringProperty getKvkProp() {
        return kvk;
    }

}
