/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.home;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.gui.views.app.ViewManager;

/**
 * FXML Controller class
 *
 * @author daan-
 */
public class HomeViewController implements Initializable {

    private TranslateTransition showDrawerTransition;
    private TranslateTransition hideDrawerTransition;
    private Stage stage;
    @FXML
    private BorderPane contentPane;
    @FXML
    private AnchorPane drawerHeader;

    @FXML
    private VBox drawerPane;
    @FXML
    private Button closeWindowButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDrawerTransition = new TranslateTransition(Duration.millis(800), drawerPane);
        showDrawerTransition.setFromX(-350);
        showDrawerTransition.setToX(0);
        hideDrawerTransition = new TranslateTransition(Duration.millis(800), drawerPane);
        hideDrawerTransition.setFromX(0);
        hideDrawerTransition.setToX(-350);
        drawerHeader.setBackground(createBackground());
        // TODO
    }

    public void initCloseButton(Stage stage) {
        System.out.println("Initialized the stage");
        this.stage = stage;
    }

    public void closeWindow() {

        stage.close();
    }

    public void hideDrawer() {
        hideDrawerTransition.play();
    }

    public void showDrawer() {
        showDrawerTransition.play();
    }

    public void myCompany() {
        Parent parent = ViewManager.getMyCompanyView();
        contentPane.setCenter(parent);
        hideDrawer();
    }

    public void createInvoice() {
        Parent parent = ViewManager.getEditorView();
        contentPane.setCenter(parent);
        hideDrawer();
    }

    public void myInvoices() {

    }

    public void myCustomers() {
        Parent parent = ViewManager.getMyCustomersView();
        contentPane.setCenter(parent);
        hideDrawer();
    }

    public void myProducts() {
        Parent parent = ViewManager.getMyProductsView();
        contentPane.setCenter(parent);
        hideDrawer();
    }

    public void settings() {
    }

    public void logOut() {
    }

    public static Background createBackground() {
        File file = new File(System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\home\\bg1.jpg");
        try {
            Image image = new Image(new FileInputStream(file));
            BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, true, true)
            );
            return new Background(bgImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
