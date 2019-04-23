/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.mycustomers;

import java.io.File;
import java.io.FileInputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

/**
 *
 * @author daan-
 */
public class TagListCell extends ListCell<Tag> {

    private static Font tagFont;

    public static ImageView createTagImageView() {
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\ICONS\\tag.png");
            Image image = new Image(new FileInputStream(file));
            ImageView view = new ImageView(image);
            view.setPreserveRatio(true);
            view.setFitHeight(22);
            return view;
        } catch (Exception e) {
            System.out.println("Couldnot load ImageView for tag");
        }
        return null;
    }

    public static Label createTagLabel(String text) {
        if (tagFont == null) {
            try {
                File file = new File(System.getProperty("user.dir") + "\\fonts\\roboto.ttf");
                tagFont = Font.loadFont(new FileInputStream(file), 20);
            } catch (Exception e) {
            }
        }
        Label label = new Label(text);
        label.setBackground(Background.EMPTY);
        label.setFont(tagFont);
        label.setPadding(new Insets(4, 4, 4, 8));
        label.setMinWidth(0);
        label.setPrefWidth(100);
        label.setWrapText(true);
        label.setTextOverrun(OverrunStyle.ELLIPSIS);
        return label;
    }

    @Override
    public void updateItem(Tag item, boolean empty) {
        super.updateItem(item, empty);
        this.setMinWidth(0);
        this.setPrefWidth(1);
        if (empty == true) {

            setText("");
            setGraphic(null);

        } else {
            ImageView iv = createTagImageView();
            Label label = createTagLabel(item.label());
            FlowPane pane = new FlowPane(iv, label);
            pane.setAlignment(Pos.CENTER_LEFT);
            pane.setPadding(new Insets(2, 2, 2, 16));
            this.setGraphic(pane);
            this.setMaxWidth(190);
        }
    }

}
