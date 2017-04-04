package by.bsuir.games;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Game implements Serializable {
    protected String name = "New Game";
    private transient StringProperty nameProperty;
    protected int cost;
    protected String releasedate;
    protected String producer;

    public String getName() {
        return name;
    }

    public StringProperty getNameProperty(){
        if(nameProperty == null){nameProperty = new SimpleStringProperty();}
        nameProperty.setValue(name);
        return nameProperty;
    }

    public int getCost() {
        return cost;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public String getProducer() {
        return producer;
    }

    public void setName(String name) {
        this.name = name;
        if(nameProperty != null){
            nameProperty.setValue(name);
        }
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
