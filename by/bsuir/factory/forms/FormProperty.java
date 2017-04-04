package by.bsuir.factory.forms;

import by.bsuir.games.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;

import javax.swing.*;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * Created by Koshelek on 03.04.2017.
 */
public class FormProperty <T extends Object> {

    private final static double anchhorMargin = 10.0;
    private Map<String,BiConsumer<T,String>> setFunctions = new HashMap<>();
    private Map<String,Callback<T,String>> getCallback = new HashMap<>();
    private Map<String,TextField> mapTextField = new HashMap<>();
    private Map<String,Label> mapLabel = new HashMap<>();
    private List<String> fields = new ArrayList<>();

    private AnchorPane formForField = new AnchorPane();
    private GridPane gridOfFields = new GridPane();

    private T changeObject = null;

    private int freeRow = 0;

    private double heightRow;

    public FormProperty(double widthLabel,double widthField,double heightRow){
        this.heightRow = heightRow;
        ColumnConstraints column1 = new ColumnConstraints(widthLabel);
        column1.setMaxWidth(widthLabel);
        column1.setMinWidth(widthLabel);
        ColumnConstraints column2 = new ColumnConstraints(widthField);
        column2.setPrefWidth(widthField);
        gridOfFields.setPrefWidth(widthLabel + widthField+anchhorMargin*2);
        gridOfFields.getColumnConstraints().add(column1);
        gridOfFields.getColumnConstraints().add(column2);
        AnchorPane.setLeftAnchor(gridOfFields,anchhorMargin);
        AnchorPane.setTopAnchor(gridOfFields,anchhorMargin);
        AnchorPane.setRightAnchor(gridOfFields,anchhorMargin);

        Button savebutton = new Button("Save");
        savebutton.setOnAction((event -> setTextToObject()));

        AnchorPane.setBottomAnchor(savebutton,anchhorMargin);
        AnchorPane.setRightAnchor(savebutton,anchhorMargin);


        formForField.getChildren().addAll(gridOfFields,savebutton);
    }

    private void setObjectToChange(T newObject){
        changeObject = newObject;
    }

    public void addFieldToForm(String name,Callback<T,String> getFunction,BiConsumer<T,String> setFunction){
        fields.add(name);
        setFunctions.put(name,setFunction);
        getCallback.put(name,getFunction);
        gridOfFields.getRowConstraints().add(new RowConstraints(heightRow));
        Label nameProperty = new Label(name);
        mapLabel.put(name,nameProperty);
        GridPane.setConstraints(nameProperty, 0,freeRow);

        TextField filedProperty = new TextField();
        mapTextField.put(name,filedProperty);
        GridPane.setConstraints(filedProperty, 1,freeRow);
        freeRow++;

        gridOfFields.getChildren().addAll(nameProperty, filedProperty);
    }

    private void getTextToField(){
        for (String field : fields) {
            mapTextField.get(field).setText(getCallback.get(field).call(changeObject));
        }
    }

    private void setTextToObject(){
        try {
            for (String field : fields) {
                setFunctions.get(field).accept(changeObject, mapTextField.get(field).getText());
            }
        }
        catch(Exception e){
                JOptionPane.showMessageDialog(null, "Please, input correct value", "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    public AnchorPane getForm(Object newObject){
        setObjectToChange((T)newObject);
        getTextToField();
        return formForField;
    }
}
