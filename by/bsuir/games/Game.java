package by.bsuir.games;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Game {
    protected String name;
    protected int cost;
    protected String releasedate;
    protected String producer;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public String getProducer() {
        return producer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}