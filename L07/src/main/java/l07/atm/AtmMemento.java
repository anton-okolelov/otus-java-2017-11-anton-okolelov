package l07.atm;

import java.util.Map;

public class AtmMemento {
    private Map<Integer, Integer> state;

    public void setState(Map<Integer, Integer> state) {
        this.state = state;
    }

    public Map<Integer, Integer> getState() {
        return this.state;
    }
}
