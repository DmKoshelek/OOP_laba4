package by.bsuir.factory.forms;

import by.bsuir.games.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koshelek on 03.04.2017.
 */
public class FactoryForms {

    public final static double widthLabel = 120;
    public final static double widthField = 240;
    public final static double heightRow = 30;

    Map<Class<?>,FormProperty<?>> factoryMap = new HashMap<>();
    public FactoryForms(){
        FormProperty<Game> gameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        gameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        gameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        gameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        gameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        factoryMap.put(Game.class,gameForm);

        FormProperty<Tabletopgame> tabletopgameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        tabletopgameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        tabletopgameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        tabletopgameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        tabletopgameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        tabletopgameForm.addFieldToForm("minCountPlayers",(game->Integer.toString(game.getMinCountPlayers())),((game,fieldText)->game.setMinCountPlayers(Integer.parseInt(fieldText))));
        tabletopgameForm.addFieldToForm("maxCountPlayers",(game->Integer.toString(game.getMaxCountPlayers())),((game,fieldText)->game.setMaxCountPlayers(Integer.parseInt(fieldText))));
        factoryMap.put(Tabletopgame.class,tabletopgameForm);

        FormProperty<Tabletopgame> activegameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        activegameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        activegameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        activegameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        activegameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        factoryMap.put(Activegame.class,activegameForm);

        FormProperty<Tabletopgame> logicalgameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        logicalgameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        logicalgameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        logicalgameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        logicalgameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        factoryMap.put(Activegame.class,activegameForm);


    }
}
