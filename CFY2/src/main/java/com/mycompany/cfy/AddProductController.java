package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bson.Document;

public class AddProductController {
    
    @FXML
    private TextField TextField_Size, TextField_Name, TextField_Price;

    @FXML
    private Button help, add_product, closeButton;

    @FXML
    private ComboBox<String> Category_to_add;
    
    @FXML
    private Label messageAddProduct;
    
    @FXML
    private Button upload_image_product;
     
    String url_image = "";
    
    
    @FXML
    void Upload_Image_Product(ActionEvent event) throws FileNotFoundException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Product Image");
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            url_image = file.toURI().toString();
        }
    }  

    @FXML
    void Add_Product(ActionEvent event) throws Exception
    {
        String name = TextField_Name.getText();
        String size = TextField_Size.getText();
        String price = TextField_Price.getText();
        String category = Category_to_add.getValue();
        
        if(name != null && size != null && price != null && category != null && url_image != null)
        {
            Document product = new Document("name", name);
//            product.append("size", size);
//            product.append("price", price);
//            product.append("category", category);
//            product.append("url_image", url_image);

            if(products.find(product).first() != null)
            {
                messageAddProduct.setText("Τhe product already exists!");           
            }
            else
            {
                Document document = new Document("name", name);
                document.append("size", size);
                document.append("price", Integer.parseInt(price));
                document.append("category", category);
                document.append("url_image", url_image);
                products.insertOne(document); 
                
                TextField_Name.clear();
                TextField_Size.clear();
                TextField_Price.clear();
            }         
        }
        else
        {
            messageAddProduct.setText("Υou must complete all fields!");
        }
        
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
}
