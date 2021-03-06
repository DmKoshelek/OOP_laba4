package by.bsuir.plugin.interfaces;

import by.bsuir.plugin.PluginLoadException;
import by.bsuir.plugin.loaders.JarVerifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Koshelek on 10.04.2017.
 */
public class PluginInfo {
    private IGamePlugin instance;

    public PluginInfo(File jarFile) throws PluginLoadException {
        try {
            JarFile jar = new JarFile(jarFile);
            if(!checkJarFile(jar)){
                throw new IllegalArgumentException(jarFile.getName()+ " - No good certificate found");
            }

            Properties props = getPluginProps(jar);
            if (props == null)
                throw new IllegalArgumentException("No props file found");

            String pluginClassName = props.getProperty("main.class");
            if (pluginClassName == null || pluginClassName.length() == 0) {
                throw new PluginLoadException("Missing property main.class");
            }

            URL jarURL = jarFile.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
            Class pluginClass = classLoader.loadClass(pluginClassName);
            instance = (IGamePlugin) pluginClass.newInstance();
        } catch (Exception e) {
            throw new PluginLoadException(e);
        }
    }

    public IGamePlugin getPluginInstance() {
        return instance;
    }

    public String getPluginNameGame() {return instance.nameGame();}
    public IFormGetter getPluginForm() {return instance.gerIForm();}
    public IGameGetter getPluginGameClass() {return instance.getIGame();}

    private Properties getPluginProps(JarFile jar) throws IOException,ClassCastException {
        Properties result = null;
        Enumeration entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = (JarEntry)entries.nextElement();
            if (entry.getName().equals("plugin.properties")) {
                // That's it! Load props
                InputStream is = null;
                try {
                    is = jar.getInputStream(entry);
                    result = new Properties();
                    result.load(is);
                } finally {
                    if (is != null)
                        is.close();
                }
            }
        }
        return result;
    }

    private boolean checkJarFile(JarFile jar) throws IOException,SecurityException{
        try {
            JarVerifier jarVerifier = new JarVerifier();
            jarVerifier.verify(jar);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}