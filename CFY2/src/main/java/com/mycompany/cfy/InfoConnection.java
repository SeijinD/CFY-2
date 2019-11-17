package com.mycompany.cfy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoConnection {
    
    // Standar info connection
    static MongoClient mongoClient = new MongoClient( "localhost" , 27017 ); 
    static MongoDatabase cfy = mongoClient.getDatabase("cfy");
    static MongoCollection account = cfy.getCollection("account");
    static MongoCollection products = cfy.getCollection("products");
    static MongoCollection basket = cfy.getCollection("basket");
   //End 
    
    static double xOffset = 0;
    static double yOffset = 0;
    
    static String categoryProduct = "";
    
    public static void OpenWindow(String title, String fxml, int weight, int height) throws Exception
    {        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/" + fxml + ".fxml"));       
        stage.initStyle(StageStyle.TRANSPARENT);       
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        root.setOnMouseDragged((MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });       
        stage.setTitle(title);
        Scene scene = new Scene(root,weight,height);
        stage.setScene(scene);   
        stage.show();  
    }
    
    public static void OpenProduct(String title, String fxml, String categoryP) throws Exception
    {       
        categoryProduct = categoryP;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/" + fxml + ".fxml"));       
        stage.initStyle(StageStyle.TRANSPARENT);       
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        root.setOnMouseDragged((MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });       
        stage.setTitle(title);
        Scene scene = new Scene(root,800,500);
        stage.setScene(scene);   
        stage.show();  
    }
    
}
