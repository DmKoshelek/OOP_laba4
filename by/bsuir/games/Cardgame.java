package by.bsuir.games;

import java.io.Serializable;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Cardgame extends Tabletopgame implements Serializable {
    protected int countCard;

    public int getCountCard() {
        return countCard;
    }

    public void setCountCard(int countCard) {
        this.countCard = countCard;
    }
}
