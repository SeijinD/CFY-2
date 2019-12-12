package com.mycompany.cfy;

import static com.mycompany.cfy.InfoConnection.*;
import static com.mycompany.cfy.ProductsController.price;

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
import org.bson.types.ObjectId;

public class BasketController {
    
    @FXML
    private Button help,buy_products, remove_product,closeButton;

    @FXML
    private TableColumn<ProductsModel, String> TableView2Size, TableView2Name;
    
    @FXML
    private TableColumn<ProductsModel, Integer> TableView2Price;
    
    @FXML
    private TableColumn<ProductsModel, ImageView> TableView2Image;

    @FXML
    private TableView<ProductsModel> TableViewBasket;
    
    @FXML
    void Remove_product(ActionEvent event) throws Exception
    {
        ProductsModel productsModel = TableViewBasket.getSelectionModel().getSelectedItem();

        Document doc = new Document("_id",new ObjectId(productsModel.getId()));
            
        basket.deleteOne(doc);
        
        price -= productsModel.getPrice();
        this.initialize();
    }

    @FXML
    void Buy_Products(ActionEvent event) throws Exception
    {
        com.mycompany.cfy.InfoConnection.OpenWindow("Buy Products","Buy",500,400);
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
        ObservableList<ProductsModel> listviewBasket = FXCollections.observableArrayList();

        TableView2Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableView2Size.setCellValueFactory(new PropertyValueFactory<>("size")); 
        TableView2Price.setCellValueFactory(new PropertyValueFactory<>("price")); 
        TableView2Image.setCellValueFactory(new PropertyValueFactory<>("image"));

        ImageView imageView;

        List<Document> documents = (List<Document>) basket.find().into(new ArrayList<Document>());
        for (Document document : documents)
        {
            listviewBasket.add(new ProductsModel(
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

        TableViewBasket.setItems(listviewBasket);
    }
}
