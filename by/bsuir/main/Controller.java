package by.bsuir.main;

import by.bsuir.factory.forms.FactoryForms;
import by.bsuir.factory.games.FactoryGames;
import by.bsuir.games.Game;
import by.bsuir.plugin.loaders.GamePlaginLoader;
import by.bsuir.serialization.SerializationOfList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class Controller implements Initializable {

    public final static String filenameSerilizationFile= "games.objs";

    @FXML
    private ComboBox<String> cmboxGameItems;
    @FXML
    private AnchorPane paneGameEditor;
    @FXML
    private TableView<Game> tbGamesList;
    @FXML
    private TableColumn<Game, String> coumGameName;

    private GamePlaginLoader gamePlaginLoader = new GamePlaginLoader();
    private ObservableList<Game> listGame = FXCollections.observableArrayList();
    private ObservableList<String> cmboxlist = FXCollections.observableArrayList();
    private FactoryGames factoryGames = new FactoryGames();
    private FactoryForms factoryForms = new FactoryForms();
    private SerializationOfList<Game> serializator = new SerializationOfList<>();

    @FXML
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        cmboxlist.addAll(
                "Game",
                "Activegame",
                "Logicalgame",
                "Videogame",
                "Roleplaygame",
                "Tabletopgame",
                "Boardgame",
                "Cardgame"
        );
        try{
            gamePlaginLoader.plaginLoad();
            gamePlaginLoader.addClassesToElem(factoryGames,factoryForms,cmboxlist);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error loading plagins", "Error" , JOptionPane.ERROR_MESSAGE);
        }
        cmboxGameItems.setItems(cmboxlist);
        cmboxGameItems.setValue("Game");
        //coumGameName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        coumGameName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        tbGamesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                paneGameEditor.getChildren().clear();
                if(newValue != null){
                    AnchorPane newForm = factoryForms.GetForm(newValue);
                    AnchorPane.setLeftAnchor(newForm,0.0);
                    AnchorPane.setTopAnchor(newForm,0.0);
                    AnchorPane.setRightAnchor(newForm,0.0);
                    AnchorPane.setBottomAnchor(newForm,0.0);
                    paneGameEditor.getChildren().add(newForm);
                }
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
        int selectedIndex = tbGamesList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tbGamesList.getItems().remove(selectedIndex);
        }
    }
    public void onActionLoadButton(){
        listGame.clear();
        try {
            serializator.loadFromFile(listGame, filenameSerilizationFile);
        }catch (Exception e){
            listGame.clear();
            JOptionPane.showMessageDialog(null, "Coudn't deserializator list", "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
    public void onActionSaveButton(){
        try {
            serializator.saveToFileList(listGame, filenameSerilizationFile);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Coudn't serializator list", "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
}
