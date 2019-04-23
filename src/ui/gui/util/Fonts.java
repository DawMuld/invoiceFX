/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;

import java.io.File;
import java.io.FileInputStream;
import javafx.scene.text.Font;

/**
 *
 * @author daan-
 */
public class Fonts {

    private static Font roboto18;

    public static Font roboto(double size) {
        try {
            File file = new File(System.getProperty("user.dir") + "\\ui\\gui\\util\\roboto.ttf");
            return Font.loadFont(new FileInputStream(file), size);
        } catch (Exception e) {
        }
        return null;
    }

    public static Font roboto18() {
        if (roboto18 == null) {
            roboto18 = roboto(18);
        }
        return roboto18;
    }
}
