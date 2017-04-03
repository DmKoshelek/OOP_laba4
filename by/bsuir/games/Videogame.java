package by.bsuir.games;

import java.io.Serializable;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Videogame extends Game implements Serializable {
    protected String platform;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
