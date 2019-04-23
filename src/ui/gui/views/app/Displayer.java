/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.views.app;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.gui.views.home.HomeViewController;

/**
 *
 * @author daan-
 */
public class Displayer extends Application{
    
    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{URL url = new URL("file:" + System.getProperty("user.dir") + "\\src\\ui\\gui\\views\\home\\HomeView.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        HomeViewController controller = loader.getController();
        controller.initCloseButton(primaryStage);
        primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
            
            
    }
    
}
    