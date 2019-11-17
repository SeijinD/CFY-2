package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bson.Document;

public class ProductsController {
    
    @FXML
    private Button help, closeButton, add_product;
    
    @FXML
    private TableColumn<ProductsModel, Integer> TableViewPrice;
    
    @FXML
    private TableColumn<ProductsModel, ImageView> TableViewImage;
    
    @FXML
    private TableColumn<ProductsModel, String> TableViewSize, TableViewName;
    
    @FXML
    private TableView<ProductsModel> TableViewProducts;

    private ObservableList<ProductsModel> listviewBasket = FXCollections.observableArrayList();
    
    ObservableList<ProductsModel> listview = FXCollections.observableArrayList();
       
    static int price = 0;
    
    @FXML
    void Add_Product(ActionEvent event) 
    {
        ProductsModel productsModel = TableViewProducts.getSelectionModel().getSelectedItem();
        listviewBasket.add(productsModel);
        
        Document document = new Document("name", productsModel.getName());
        document.append("size", productsModel.getSize());
        document.append("price", productsModel.getPrice());
        document.append("url_image", productsModel.getPath());
        basket.insertOne(document); 
        price += productsModel.getPrice();        
          
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
    
    public void initialize() 
    {
        TableViewName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableViewSize.setCellValueFactory(new PropertyValueFactory<>("size")); 
        TableViewPrice.setCellValueFactory(new PropertyValueFactory<>("price")); 
        TableViewImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        ImageView imageView;
        
        Document product = new Document("category", categoryProduct);
        
        List<Document> documents = (List<Document>) products.find(product).into(new ArrayList<Document>());
        for (Document document : documents)
        {
            listview.add(new ProductsModel(
                    (String) document.get("name"),
                    (String) document.get("size"),
                    (Integer) document.get("price"),
                    imageView = new ImageView((String) document.get("url_image")),
                    (String) document.get("url_image"),
                    (document.get("_id")).toString()
            ));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
        }
        TableViewProducts.setItems(listview);
    }
}
