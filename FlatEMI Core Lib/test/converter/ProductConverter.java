/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import flatemi.api.ListFileDataBase;
import flatemi.api.ListFileDatabaseFactory;
import flatemi.com.impl.product.Product;
import flatemi.com.impl.product.ProductParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author daan-
 */
public class ProductConverter {
    
    public static void main(String[] args){
        File file = new File("D:\\temp\\products.csv");
        ListFileDataBase<Product> db = ListFileDatabaseFactory.getProductDB();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            for(line = reader.readLine(); null != line; line = reader.readLine()){
                String[] cells = line.split(";");
                String id = cells[1];
                String name = cells[2];
                double sellingPrice = Double.parseDouble(cells[3]);
                double purchasePrice = Double.parseDouble(cells[4]);
                String details = cells[5];
                String tagline = cells[6];
                Product product = db.createNew();
                product.setId(id);
                product.setName(name);
                product.setSellingPrice(sellingPrice);
                product.setPurchasePrice(purchasePrice);
                product.setDetails(details);
                product.addTag(tagline);
                System.out.println("___________________________________________________________________________________________________________\n" + line + "\n\tto: " + ProductParser.fromProduct(product));
                db.update(product);
                
                
            }
            reader.close();
        
        }catch(Exception e){
            e.printStackTrace();
        
        }
    }
    
}
