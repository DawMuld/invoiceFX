/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.mycustomers;

import flatemi.com.impl.customer.Customer;
import flatemi.com.impl.customer.CustomerFileManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author daan-
 */
public class MyCustomersController implements Initializable {

    @FXML
    private ListView<Tag> labelListView;
    @FXML
    private ListView<Customer> customerList;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomers();
        Tag[] tags = new Tag[]{new Tag("B2B"), new Tag("B2C"), new Tag("Vast"), new Tag("Debiteur")};
        ObservableList<Tag> items = FXCollections.observableArrayList(tags);
        labelListView.setItems(items);
        labelListView.setCellFactory((param) -> {
            return new TagListCell();
        });
       

    }

    public void loadCustomers() {
        ObservableList<Customer> customers = CustomerFileManager.getItems();
        System.out.println("size = " + customers.size());
        customerList.setItems(customers);
        customerList.setCellFactory((param) -> {
            return new CustomerListCell();
        });

    }

}
