package l07.atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmDepartment {

    List<Atm> atmList = new ArrayList<>();

    Map<Atm, AtmMemento> mementoMap = new HashMap<>();

    public void addAtm(Atm atm) {
        atmList.add(atm);
        AtmMemento atmMemento = new AtmMemento();
        atmMemento.setState(atm.getState());
        mementoMap.put(atm, atmMemento);
    }

    public long getSum() {
        return atmList.stream()
                .mapToLong(Atm::getSum)
                .sum();
    }

    public void resetState() {
        mementoMap.forEach((atm, memento) -> atm.setState(memento.getState()));
    }
}
