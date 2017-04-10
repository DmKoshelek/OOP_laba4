package by.bsuir.plugin.interfaces;

import by.bsuir.factory.forms.IFormGetter;
import by.bsuir.factory.games.IGameGetter;

/**
 * Created by Koshelek on 10.04.2017.
 */
public interface IGamePlugin {
        IFormGetter gerIForm();
        IGameGetter getIGame();
        String nameGame();
        void destroy();
}