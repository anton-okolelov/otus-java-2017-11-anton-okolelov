package l06.atm;

import java.util.*;
import java.util.stream.Collectors;

public class Atm {

    private Map<Integer, Box> boxes;

    public Atm() {
        boxes = new HashMap<>();

        for (int nominal : Banknote.getAllowedNominals()) {
            boxes.put(nominal, new Box(nominal, 100));
        }
    }

    public void add(List<Banknote> banknotes) throws AtmTemporarilyOutOfOrderException {
        Map<Integer, Long> nominalsCount = banknotes.stream()
                .collect(Collectors.groupingBy(Banknote::getNominal, Collectors.counting()));


        for (Map.Entry<Integer, Long> entry : nominalsCount.entrySet()) {
            Integer nominal = entry.getKey();
            Box box = boxes.get(nominal);
            int count = entry.getValue().intValue();

            if (!box.canPut(count)) {
                throw new AtmTemporarilyOutOfOrderException();
            }
        }

        nominalsCount.forEach((nominal, count) -> {
            try {
                boxes.get(nominal).put(count.intValue());
            } catch (BoxOverflowException e) {
                throw new RuntimeException(e);
            }
        });

    }

    // выдать деньги
    public List<Banknote> giveOutMoney(long sum) throws NotEnoughMoneyException {
        if (sum > getSum()) {
            throw new NotEnoughMoneyException();
        }
        List<Banknote> banknotes = new ArrayList<>();

        List<Integer> nominals = new ArrayList<>(Banknote.getAllowedNominals());
        nominals.sort(Collections.reverseOrder());

        long collectedSum = 0;

        for (Integer nominal : nominals) {
            Box box = boxes.get(nominal);
            int banknotesToGet = Math.min((int) ((sum - collectedSum) / nominal), box.getBanknotesCount());
            if (banknotesToGet == 0 || box.getBanknotesCount() == 0) {
                continue;
            }

            box.get(banknotesToGet);
            collectedSum += banknotesToGet * nominal;
            for (int i = 0; i < banknotesToGet; i++) {
                try {
                    banknotes.add(new Banknote(nominal));
                } catch (IncorrectNominalException e) {
                    throw new RuntimeException(e);
                }
            }
            if (sum == collectedSum) {
                break;
            }
        }

        return banknotes;
    }

    // получпть количество денег в банкомате
    public long getSum() {
        return boxes.values().stream()
                .mapToLong(box -> box.getNominal() * box.getBanknotesCount())
                .sum();
    }
}
