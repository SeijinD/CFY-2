package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;
import static com.mycompany.cfy.LoginController.userEdit;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.bson.Document;

public class DeleteAccountController {
    
    @FXML
    private Button DeleteAccountButton, closeButton;

    @FXML
    private CheckBox sureBox;
    
    @FXML
    private Label messageDeleteAccount;

    @FXML
    void Delete_Account(ActionEvent event) throws Exception
    {
        if (sureBox.isSelected())
        {
            Document doc = new Document("username",userEdit);
            
            account.deleteOne(doc);
        
            Platform.exit();
        }
        else
        {
            messageDeleteAccount.setText("The checkBox is unselected.");
        }
    }   

    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
