/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.mycustomers;

import flatemi.com.impl.customer.Customer;
import java.io.File;
import java.io.FileInputStream;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import ui.gui.icons.IconLoader;
import ui.gui.util.Fonts;

/**
 *
 * @author daan-
 */
public class CustomerListCell extends ListCell<Customer> {

    private Customer customer;

    @Override
    public void updateItem(Customer item, boolean empty) {
        super.updateItem(item, empty);
        if (empty == true) {
            setText("");
            setGraphic(null);
        } else {
            this.customer = item;
            setPrefWidth(900);
            BorderPane root = new BorderPane();
            root.setPrefWidth(900);
            root.setPrefHeight(40);
            root.setMinHeight(40);
            root.setMinWidth(800);
            FlowPane left = new FlowPane(createImageView());
            Label label = createLabel(customer.getName());
            label.setPrefWidth(500);
            FlowPane center = new FlowPane(label);
            Button button = createEditButton();
            FlowPane right = new FlowPane(button);
            right.setAlignment(Pos.CENTER_RIGHT);
            button.setVisible(false);
            hoverProperty().addListener((o, v1, v2) -> {
                if (v2 == true) {
                    button.setVisible(true);
                } else {
                    button.setVisible(false);
                }
            });

            root.setLeft(left);
            root.setCenter(center);
            root.setRight(right);

            setGraphic(root);

        }

    }

    private static ImageView createImageView() {
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\mycustomers\\contact.png");
            Image image = new Image(new FileInputStream(file));
            ImageView view = new ImageView(image);
            view.setFitWidth(40);
            view.setFitHeight(40);
            return view;
        } catch (Exception e) {
        }
        return null;
    }

    private static Label createLabel(String text) {
        Label label = new Label();
        label.setPrefWidth(400);
        label.setText(text);
        label.setWrapText(true);
        label.setTextOverrun(OverrunStyle.ELLIPSIS);
        label.setFont(Fonts.roboto18());
        return label;
    }

    private static Button createEditButton() {
        Button button = new Button();
        button.setBorder(Border.EMPTY);
        button.setBackground(Background.EMPTY);
        button.setGraphic(IconLoader.loadIcon("edit.png", 40));
        button.setOpacity(0.3);
        button.hoverProperty().addListener((o, v1, v2) -> {
            if (v2) {
                button.setOpacity(1.0);
            } else {
                button.setOpacity(0.3);
            }
        });
        return button;
    }

}
