/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.app;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.gui.views.mycomp.MyCompanyController;
import ui.gui.views.mycustomers.MyCustomersController;
import ui.gui.views.myproducts.MyProductsController;
import ui.gui.views.mysettings.MySettingsController;

/**
 *
 * @author daan-
 */
public class ViewManager {

    private static Parent myCompanyView;
    private static Parent myCustomersView;
    private static Parent myProductsView;
    private static Parent mySettingsView;
    private static Parent editorView;

    public static Parent getMyCompanyView() {
        if (myCompanyView == null) {
            try {
                URL url = new URL("file:" + System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\mycomp\\MyCompanyView.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                loader.setController(new MyCompanyController());
                myCompanyView = loader.load();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return myCompanyView;

    }

    public static Parent getMyCustomersView() {
        if (myCustomersView == null) {
            try {
                URL url = new URL("file:" + System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\mycustomers\\MyCustomersView.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                myCustomersView = loader.load();
                MyCustomersController controller = loader.getController();
                controller.loadCustomers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (myCustomersView == null) {
            System.out.println("My Customers View is null");
        } else {
            System.out.println("My Customers View is not null");
        }
        return myCustomersView;

    }

    public static Parent getMyProductsView() {
        if (myProductsView == null) {
            try {
                URL url = new URL("file:" + System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\myproducts\\MyProductsView.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                loader.setController(new MyProductsController());
                myProductsView = loader.load();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return myProductsView;

    }

    public static Parent getMySettingsView() {
        if (mySettingsView == null) {
            try {
                URL url = new URL("file:" + System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\mysettings\\MySettingsView.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                loader.setController(new MySettingsController());
                mySettingsView = loader.load();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return mySettingsView;

    }

    public static Parent getEditorView() {
        if (editorView == null) {
            try {
                URL url = new URL("file:" + System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\mycustomers\\MyCustomersView.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                editorView = loader.load();
            } catch (Exception e) {
                System.out.println("OOOOOOOOOOOOOOOOOOOOOOOO");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return editorView;

    }

}
