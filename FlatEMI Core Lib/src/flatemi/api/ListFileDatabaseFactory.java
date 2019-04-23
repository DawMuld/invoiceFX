/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.api;

import flatemi.com.impl.company.Company;
import flatemi.com.impl.company.CompanyFactory;
import flatemi.com.impl.company.CompanyFileAccessor;
import flatemi.com.impl.company.CompanyParser;
import flatemi.com.impl.customer.Customer;
import flatemi.com.impl.customer.CustomerFactory;
import flatemi.com.impl.customer.CustomerFileAccessor;
import flatemi.com.impl.customer.CustomerParser;
import flatemi.com.impl.product.Product;
import flatemi.com.impl.product.ProductFactory;
import flatemi.com.impl.product.ProductFileAccessor;
import flatemi.com.impl.product.ProductParser;

/**
 *
 * @author daan-
 */
public class ListFileDatabaseFactory {

    private static ListFileDataBase<Company> companyDB;
    private static ListFileDataBase<Customer> customerDB;
    private static ListFileDataBase<Product> productDB;

    public static ListFileDataBase<Company> getCompanyDB() {
        if (companyDB == null) {
            companyDB = new ListFileDataBase(new CompanyParser(), new CompanyFactory(), new CompanyFileAccessor());
        }
        return companyDB;
    }

    public static ListFileDataBase<Customer> getCustomerDB() {
        if (customerDB == null) {
            customerDB = new ListFileDataBase(new CustomerParser(), new CustomerFactory(), new CustomerFileAccessor());
        }
        return customerDB;
    }

    public static ListFileDataBase<Product> getProductDB() {
        if (productDB == null) {
            productDB = new ListFileDataBase<>(new ProductParser(), new ProductFactory(), new ProductFileAccessor());
        }
        return productDB;
    }

}
