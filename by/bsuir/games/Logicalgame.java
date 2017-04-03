package by.bsuir.games;

import java.io.Serializable;

/**
 * Created by Koshelek on 28.03.2017.
 */
public class Logicalgame extends Game implements Serializable {
    protected String complexity;

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }
}
