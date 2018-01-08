package l06.atm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BanknoteTest {

    @Test
    public void getNominal() throws IncorrectNominalException {
        Banknote banknote = new Banknote(10);
        assertEquals(10, banknote.getNominal());
    }



    @Test(expected = IncorrectNominalException.class)
    public void incorrectNominal() throws IncorrectNominalException {
        Banknote banknote = new Banknote(3);
    }
}
