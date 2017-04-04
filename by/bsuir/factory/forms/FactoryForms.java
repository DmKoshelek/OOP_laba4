package by.bsuir.factory.forms;

import by.bsuir.games.*;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koshelek on 03.04.2017.
 */
public class FactoryForms {

    public final static double widthLabel = 120;
    public final static double widthField = 240;
    public final static double heightRow = 30;


    Map<Class<?>,FormProperty<? extends Game>> factoryMap = new HashMap<>();
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

        FormProperty<Cardgame> cardgameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        cardgameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        cardgameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        cardgameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        cardgameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        cardgameForm.addFieldToForm("minCountPlayers",(game->Integer.toString(game.getMinCountPlayers())),((game,fieldText)->game.setMinCountPlayers(Integer.parseInt(fieldText))));
        cardgameForm.addFieldToForm("maxCountPlayers",(game->Integer.toString(game.getMaxCountPlayers())),((game,fieldText)->game.setMaxCountPlayers(Integer.parseInt(fieldText))));
        cardgameForm.addFieldToForm("countCard",(game->Integer.toString(game.getCountCard())),((game,fieldText)->game.setCountCard(Integer.parseInt(fieldText))));
        factoryMap.put(Cardgame.class,cardgameForm);

        FormProperty<Boardgame> boardgameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        boardgameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        boardgameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        boardgameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        boardgameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        boardgameForm.addFieldToForm("minCountPlayers",(game->Integer.toString(game.getMinCountPlayers())),((game,fieldText)->game.setMinCountPlayers(Integer.parseInt(fieldText))));
        boardgameForm.addFieldToForm("maxCountPlayers",(game->Integer.toString(game.getMaxCountPlayers())),((game,fieldText)->game.setMaxCountPlayers(Integer.parseInt(fieldText))));
        boardgameForm.addFieldToForm("countCells",(game->Integer.toString(game.getCountCells())),((game,fieldText)->game.setCountCells(Integer.parseInt(fieldText))));
        factoryMap.put(Boardgame.class,boardgameForm);

        FormProperty<Activegame> activegameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        activegameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        activegameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        activegameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        activegameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        factoryMap.put(Activegame.class,activegameForm);

        FormProperty<Logicalgame> logicalgameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        logicalgameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        logicalgameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        logicalgameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        logicalgameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        logicalgameForm.addFieldToForm("complexity",(game->game.getComplexity()),((game,fieldText)->game.setComplexity(fieldText)));
        factoryMap.put(Logicalgame.class,logicalgameForm);

        FormProperty<Videogame> videogameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        videogameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        videogameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        videogameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        videogameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        videogameForm.addFieldToForm("platform",(game->game.getPlatform()),((game,fieldText)->game.setPlatform(fieldText)));
        factoryMap.put(Videogame.class,videogameForm);

        FormProperty<Roleplaygame> roleplaygameForm = new FormProperty<>(widthLabel,widthField,heightRow);
        roleplaygameForm.addFieldToForm("name",(game->game.getName()),((game,fieldText)->game.setName(fieldText)));
        roleplaygameForm.addFieldToForm("cost",(game->Integer.toString(game.getCost())),((game,fieldText)->game.setCost(Integer.parseInt(fieldText))));
        roleplaygameForm.addFieldToForm("releasedate",(game->game.getReleasedate()),((game,fieldText)->game.setReleasedate(fieldText)));
        roleplaygameForm.addFieldToForm("producer",(game->game.getProducer()),((game,fieldText)->game.setProducer(fieldText)));
        roleplaygameForm.addFieldToForm("platform",(game->game.getPlatform()),((game,fieldText)->game.setPlatform(fieldText)));
        roleplaygameForm.addFieldToForm("mainhero",(game->game.getMainhero()),((game,fieldText)->game.setMainhero(fieldText)));
        factoryMap.put(Roleplaygame.class,roleplaygameForm);
    }

    public AnchorPane GetForm(Game changeGame){
        return factoryMap.get(changeGame.getClass()).getForm(changeGame);
    }
}
