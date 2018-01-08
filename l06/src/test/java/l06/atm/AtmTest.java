package l06.atm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AtmTest {
    List<Banknote> banknotes;
    Atm atm;

    @Before
    public void setUp() {
        banknotes = new ArrayList<>();
        atm = new Atm();
    }


    @Test
    public void addBanknotes() throws IncorrectNominalException, AtmTemporarilyOutOfOrderException {

        banknotes.add(new Banknote(10));
        atm.add(banknotes);

        assertEquals(atm.getSum(), 10);
    }

    @Test
    public void getSum() throws IncorrectNominalException, AtmTemporarilyOutOfOrderException {
        banknotes.add(new Banknote(10));
        banknotes.add(new Banknote(100));
        banknotes.add(new Banknote(100));
        
        atm.add(banknotes);

        assertEquals(atm.getSum(), 210);
    }


    @Test
    public void giveOutMoney() throws IncorrectNominalException, AtmTemporarilyOutOfOrderException, NotEnoughMoneyException {
        banknotes.add(new Banknote(10));
        banknotes.add(new Banknote(100));
        banknotes.add(new Banknote(10));

        atm.add(banknotes);

        List<Banknote> resultBanknotes = atm.giveOutMoney(20);
        assertTrue(resultBanknotes.get(0).getNominal() == 10);
        assertTrue(resultBanknotes.get(1).getNominal() == 10);
        assertEquals(2, resultBanknotes.size());
    }
}
