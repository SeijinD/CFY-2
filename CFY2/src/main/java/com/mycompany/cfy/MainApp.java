package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
  
    @Override
    public void start(Stage stage) throws Exception 
    {      
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));       
        stage.initStyle(StageStyle.TRANSPARENT);       
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });  
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });       
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);   
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
