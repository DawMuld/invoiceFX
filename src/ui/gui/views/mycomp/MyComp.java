/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.mycomp;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author daan-
 */
public class MyComp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader;

        URL url = new URL("file:"+System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\mycomp\\MyCompanyView.fxml");
        System.out.println(null == url);
        
        loader= new FXMLLoader(url);
        loader.setController(new MyCompanyController());
        Parent parent;
        parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
