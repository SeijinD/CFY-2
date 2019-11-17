package com.mycompany.cfy;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mycompany.cfy.InfoConnection.*;
import static com.mycompany.cfy.LoginController.userEdit;
import static com.mycompany.cfy.LoginController.passEdit;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ResetPasswordController {

    @FXML
    private Label messageResetPassword;
    
    @FXML
    private PasswordField NewPassword, oldPassword, ConfirmPassword;

    @FXML
    private Button SaveResetPassword, closeButton;
               
    @FXML
    void Save_Reset_Password(ActionEvent event)
    {      
        if(passEdit.equals(oldPassword.getText()))
        { 
            if (NewPassword.getText().equals(ConfirmPassword.getText()))
            {
                String pass = NewPassword.getText();
                
                Document doc = new Document("username", userEdit);
                Bson query = combine(set("password", pass));
                
                account.updateOne(doc,query);
                
                messageResetPassword.setText("Password Changed");
            }
            else
            {
                messageResetPassword.setText("Different Passwords");
            }
        }
        else
        {
            messageResetPassword.setText("Old Password is false");
        }
    }   

    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
