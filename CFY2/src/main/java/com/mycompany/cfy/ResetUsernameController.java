package com.mycompany.cfy;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mycompany.cfy.InfoConnection.*;
import static com.mycompany.cfy.LoginController.userEdit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ResetUsernameController {
    
    @FXML
    private Label messageResetUsername;

    @FXML
    private TextField oldUsername, NewUsername, ConfirmUsername;

    @FXML
    private Button SaveResetUsername, closeButton;

    @FXML
    void Save_Reset_Username(ActionEvent event) 
    {      
        if(userEdit.equals(oldUsername.getText()))
        {
            if (NewUsername.getText().equals(ConfirmUsername.getText()))
            {
                String user = NewUsername.getText();
                
                Document doc = new Document("username", userEdit);
                Bson query = combine(set("username", user));
                
                account.updateOne(doc,query);
                
                messageResetUsername.setText("Username Changed");
            }
            else
            {
                messageResetUsername.setText("Different Usernames");
            }
        }
        else
        {
            messageResetUsername.setText("Old Username is false");
        }
    }

    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
