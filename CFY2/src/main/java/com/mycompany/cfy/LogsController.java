package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LogsController {
    
    @FXML
    private Button closeButton;
    
    @FXML
    private ListView logs_view; 
     
    public void initialize() throws Exception 
    { 
        
         
         
    }
    
    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }   
}
