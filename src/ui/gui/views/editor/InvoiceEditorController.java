/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.editor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author daan-
 */
public class InvoiceEditorController implements Initializable {

    @FXML
    private BorderPane editorDrawer;
    @FXML
    private Button editInvoiceNumberButton;
    @FXML
    private Button editInvoiceDateButton;
    @FXML
    private Button editCreditorButton;
    @FXML
    private Button editDebtorButton;

    private TranslateTransition showDrawerTransition;
    private TranslateTransition hideDrawerTransition;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDrawerTransition = new TranslateTransition(Duration.millis(800), editorDrawer);
        showDrawerTransition.setFromX(300);
        showDrawerTransition.setToX(0);
        hideDrawerTransition = new TranslateTransition(Duration.millis(800), editorDrawer);
        hideDrawerTransition.setFromX(0);
        hideDrawerTransition.setToX(300);
        installOnButtonHover(editInvoiceNumberButton);
        installOnButtonHover(editInvoiceDateButton);
        installOnButtonHover(editDebtorButton);
        installOnButtonHover(editCreditorButton);
        
    }

    public void toggleDrawer() {
        if (editorDrawer.translateXProperty().get() > 0) {
            showDrawer();
        } else {
            hideDrawer();
        }
    }

    public void hideDrawer() {
        hideDrawerTransition.play();
    }

    public void showDrawer() {
        showDrawerTransition.play();
    }

    public void installOnButtonHover(Button button) {
        button.hoverProperty().addListener((o, c1, c2) -> {
            if (c2 == true) {
                button.setOpacity(1);
            } else {
                button.setOpacity(0.3);
            }
        });
    }

    public void handleEditInvoiceNumber() {
        System.out.println("handleEditInvoideNumber invoked");
    }

    public void handleEditInvoiceDate() {
        System.out.println("handleEditInvoiceDate invoked");
    }

    public void handleEditCreditor() {
        System.out.println("handleEditCreditor invoked");
    }

    public void handleEditDebtor() {
        System.out.println("handleEditDebtor invoked");
    }

}
