package by.bsuir.games;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Tabletopgame extends Game {
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
