package by.bsuir.plugin;

/**
 * Created by Koshelek on 10.04.2017.
 */

public class PluginLoadException extends Exception {
    public PluginLoadException(Exception cause) {
        super(cause);
    }
    public PluginLoadException(String message) {
        super(message);
    }
}
