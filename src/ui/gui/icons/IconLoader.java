/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.icons;

import java.io.File;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author daan-
 */
public class IconLoader {

    public static ImageView loadIcon(String fileName, double size) {
        try {
            File file = new File(System.getProperty("user.home") + "\\src\\ICONS\\" + fileName);
            Image image = new Image(new FileInputStream(file));
            ImageView iv = new ImageView(image);
            iv.setFitHeight(size);
            iv.setFitWidth(size);
            return iv;
        } catch (Exception e) {
        }
        return null;
    }
}
