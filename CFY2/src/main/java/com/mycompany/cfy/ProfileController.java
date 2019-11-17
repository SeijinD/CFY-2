package com.mycompany.cfy;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mycompany.cfy.InfoConnection.*;
import static com.mycompany.cfy.LoginController.userEdit;
import static com.mycompany.cfy.LoginController.userType;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ProfileController {
    
    @FXML
    private Button closeButton, closeButton1, help, saveChanges, change_profile, open_logs,upload_image_profile;
    
    @FXML
    private ImageView image;

    @FXML
    private TextField change_age, change_name, change_email, change_surname;
    
    @FXML
    private ComboBox<String> change_gender;

    @FXML
    private Label gender, age, email, name, surname;
    
    @FXML
    private Pane profile_view, profile_change;
    
    String url_image = "";
    
    @FXML
    void Upload_Image_Profile(ActionEvent event) throws FileNotFoundException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Product Image");
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            url_image = file.toURI().toString();
        }
    }  
    
    @FXML
    void Save_Profile(ActionEvent event) 
    { 
      String s_name = change_name.getText();
      String s_surname = change_surname.getText();
      String s_email = change_email.getText();
      int s_age = 0;
      if (!change_age.getText().equals("") && change_age.getText() != null)
      {
        s_age = Integer.parseInt(change_age.getText());
      }
      
      int s_gender = 0;
        switch (change_gender.getValue()) {
            case "Male":
                s_gender = 1;
                break;
            case "Female":
                s_gender = 2;
                break;
            case "Other":
                s_gender = 3;
                break;
            default:
                break;
        }
        
        Document doc = new Document("username", userEdit);
        Bson query = combine(set("name", s_name),set("surname", s_surname),set("gender", s_gender),set("age", s_age),set("email", s_email),set("url_image", url_image));

        account.updateOne(doc,query);

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Change_Profile(ActionEvent event) 
    {
        profile_view.setVisible(false);
        profile_change.setVisible(true);
    }
       
    @FXML
    void Help(ActionEvent event) throws Exception
    {
        com.mycompany.cfy.InfoConnection.OpenWindow("Help","Help",800,500);
    }
    
    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void Open_Logs(ActionEvent event) throws Exception
    {
        com.mycompany.cfy.InfoConnection.OpenWindow("Logs","Logs",800,500);
    }
    
    public void initialize() 
    {
        if(userType.equals("Admin"))
        {
            open_logs.setVisible(true);
        }
            
        Document login = new Document("username", userEdit);
 
        url_image = (String) ((Document) account.find(login).first()).get("url_image");
        String v_name = (String) ((Document) account.find(login).first()).get("name");
        String v_surname = (String) ((Document) account.find(login).first()).get("surname");
        int v_age = (int) ((Document) account.find(login).first()).get("age");
        int v_gender = (int) ((Document) account.find(login).first()).get("gender");   
        String v_email = (String) ((Document) account.find(login).first()).get("email");

  
        switch (String.valueOf(v_gender)) {
            case "1":
                gender.setText("Male");
                break;
            case "2":
                gender.setText("Female");
                break;
            default:
                gender.setText("Other");
                break;
        }

        age.setText(String.valueOf(v_age));
        change_age.setText(String.valueOf(v_age));

        email.setText(v_email);
        change_email.setText(v_email);

        name.setText(v_name);
        change_name.setText(v_name);

        surname.setText(v_surname);
        change_surname.setText(v_surname);
     
        Image img = new Image(url_image);
        image.setImage(img);
        
    }  
}
