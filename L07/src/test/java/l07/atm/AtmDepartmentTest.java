package l07.atm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * • Приложение может содержать несколько ATM
 * • Departmant может собирать сумму остатков со всех ATM
 * • Department может инициировать событие – восстановить состояние всех ATM до начального.
 */


public class AtmDepartmentTest {

    private Atm atm1;
    private Atm atm2;
    private AtmDepartment atmDepartment;

    @Before
    public void setUp() {
        atmDepartment = new AtmDepartment();

        Map<Integer, Integer> initialBanknotesCount = new HashMap<>();
        initialBanknotesCount.put(10, 50);
        initialBanknotesCount.put(100, 5);
        initialBanknotesCount.put(1000, 6);
        atm1 = new Atm(initialBanknotesCount);
        atmDepartment.addAtm(atm1);


        initialBanknotesCount = new HashMap<>();
        initialBanknotesCount.put(5000, 2);
        atm2 = new Atm(initialBanknotesCount);
        atmDepartment.addAtm(atm2);

    }

    @Test
    public void getSum() {
        assertEquals(17000, atmDepartment.getSum());
    }


    @Test
    public void resetState() throws NotEnoughMoneyException {
        atm1.giveOutMoney(450);
        assertEquals(16550, atmDepartment.getSum());
        atmDepartment.resetState();
        assertEquals(17000, atmDepartment.getSum());
    }
}
