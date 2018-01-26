package l07.atm;


/**
 * Ячейка банкомата с определенным номиналом
 */
public class Box {
    private final int nominal;
    private final int maxBanknotesCount;
    private int banknotesCount = 0;

    Box(int nominal, int maxBanknotesCount) {
        this.nominal = nominal;
        this.maxBanknotesCount = maxBanknotesCount;

    }

    public int getNominal() {
        return nominal;
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    /**
     * Можно ли положить в ячейку заданное количество банкнот
     */
    public boolean canPut(int banknotesCount) {
        return (banknotesCount + this.banknotesCount <= maxBanknotesCount);
    }

    public void put(int banknotesCount) throws BoxOverflowException {
        if (!canPut(banknotesCount)) {
            throw new BoxOverflowException();
        }
        this.banknotesCount += banknotesCount;
    }

    public void set(int banknotesCount) {
        this.banknotesCount = banknotesCount;
    }

    public void get(int banknotesCount) {
        if (canGet(banknotesCount)) {
            this.banknotesCount -= banknotesCount;
        } else {
            throw new RuntimeException("Not enough money in box");
        }
    }

    public boolean canGet(int banknotesCount) {
        return banknotesCount <= this.banknotesCount;
    }

}
