package by.bsuir.plugin.loaders;

import by.bsuir.factory.forms.FactoryForms;
import by.bsuir.factory.games.FactoryGames;
import by.bsuir.plugin.PluginLoadException;
import by.bsuir.plugin.interfaces.PluginInfo;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koshelek on 10.04.2017.
 */
public class GamePlaginLoader {

    private Map<String,PluginInfo> plugins;

    public void plaginLoad() {
        File pluginDir = new File("plugins");

        File[] jars = pluginDir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".jar");
            }
        });

        plugins = new HashMap();

        for (File file : jars) {
            try {
                plugins.put(file.getName(), new PluginInfo(file));
            } catch (PluginLoadException e) {
                e.printStackTrace();
            }
        }
    }
    public void addClassesToElem(FactoryGames factoryGames,FactoryForms factoryForms,ObservableList<String> list){
        synchronized (plugins) {
            for (PluginInfo pluginInfo : plugins.values()) {
                final PluginInfo plugin = pluginInfo;
                list.add(plugin.getPluginNameGame());
                factoryGames.addNewElem(plugin.getPluginNameGame(),plugin.getPluginGameClass());
                factoryForms.AddToFactory(plugin.getPluginGameClass().get().getClass(),plugin.getPluginForm());
            }
        }
    }

}
