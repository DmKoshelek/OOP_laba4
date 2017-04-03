package by.bsuir.main;

import by.bsuir.factory.forms.FactoryForms;
import by.bsuir.factory.games.FactoryGames;
import by.bsuir.games.Game;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.util.ArrayList;

public class Controller implements Initializable {

    @FXML
    private ComboBox<String> cmboxGameItems;
    @FXML
    private AnchorPane paneGameEditor;
    @FXML
    private TableView<Game> tbGamesList;
    @FXML
    private TableColumn<Game, String> coumGameName;

    private ObservableList<Game> listGame = FXCollections.observableArrayList();
    private FactoryGames factoryGames = new FactoryGames();
    private FactoryForms factoryForms = new FactoryForms();

    @FXML
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        cmboxGameItems.getItems().addAll(
                "Game",
                "Activegame",
                "Logicalgame",
                "Videogame",
                "Roleplaygame",
                "Tabletopgame",
                "Boardgame",
                "Cardgame"
        );
        cmboxGameItems.setValue("Game");
        coumGameName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tbGamesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                factoryForms.GetForm(newValue);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Suitable form don't found", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        });
        tbGamesList.setItems(listGame);
    }

    public void onActionNewButton(){
        try{
            Game newItem = factoryGames.getGameByName(cmboxGameItems.getValue());
            if (newItem == null) {throw new NullPointerException();}
                listGame.add(newItem);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Coudn't create elem", "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
    public void onActionDeleteButton(){

    }
    public void onActionLoadButton(){

    }
    public void onActionSaveButton(){

    }
}
