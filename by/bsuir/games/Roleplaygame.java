package by.bsuir.games;

import java.io.Serializable;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Roleplaygame extends Videogame implements Serializable {
    public String getMainhero() {
        return mainhero;
    }

    public void setMainhero(String mainhero) {
        this.mainhero = mainhero;
    }

    protected String mainhero;
}
