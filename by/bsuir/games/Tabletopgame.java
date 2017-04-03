package by.bsuir.games;

import java.io.Serializable;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Tabletopgame extends Game implements Serializable {
    protected int minCountPlayers;
    protected int maxCountPlayers;

    public int getMinCountPlayers() {
        return minCountPlayers;
    }

    public void setMinCountPlayers(int minCountPlayers) {
        this.minCountPlayers = minCountPlayers;
    }

    public int getMaxCountPlayers() {
        return maxCountPlayers;
    }

    public void setMaxCountPlayers(int maxCountPlayers) {
        this.maxCountPlayers = maxCountPlayers;
    }
}
