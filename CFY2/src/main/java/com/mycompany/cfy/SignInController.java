package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

public class SignInController {

    @FXML
    private TextField userNameCreate;
    @FXML
    private  PasswordField passwordCreate;
    @FXML 
    private CheckBox sureBox;
    @FXML
    private RadioButton userRadioButton, adminRadioButton;
 
    @FXML
    private Label messageSignIn;
    
    @FXML
    private Button createButton, closeButton;
    
    @FXML
    public void CreateAndGoLogin(ActionEvent event) throws Exception
    {
        String url_image = "file:/C:/GitHub/CFY/CFY/src/main/resources/images/profile.png";
        String user1 = userNameCreate.getText();
        String pass1 = passwordCreate.getText();
        int type_user1;
        if(userRadioButton.isSelected())
            type_user1 = 0;
        else
            type_user1 = 1;
        if(user1.equals("") || pass1.equals(""))
            messageSignIn.setText("Username or Password is empty!");
        else if (sureBox.selectedProperty().getValue() == false)
            messageSignIn.setText("You don't check the checkbox!");
        else            
        {
            Document doc = new Document("username", user1);
            if(account.find(doc).first() != null )
            {
                messageSignIn.setText("Username is already exist!");
            }
            else
            {
                Document document = new Document("username", user1);
                document.append("password", pass1);
                document.append("url_image", url_image);
                document.append("type_user", type_user1);
                document.append("name", null);
                document.append("surname", null);
                document.append("age", null);
                document.append("gender", null);
                document.append("email", null);
                account.insertOne(document);

                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close(); 
            }
        }      
    }
    
    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
