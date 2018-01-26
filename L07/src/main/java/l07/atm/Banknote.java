package l07.atm;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Banknote {

    private int nominal;

    public Banknote(int nominal) throws IncorrectNominalException {

        if (!getAllowedNominals().contains(nominal)) {
            throw new IncorrectNominalException("Incorrect nominal");
        }

        this.nominal = nominal;
    }

    public static Set<Integer> getAllowedNominals() {
        return new HashSet<>(Arrays.asList(new Integer[]{10, 50, 100, 1000, 5000}));
    }

    public int getNominal() {
        return this.nominal;
    }
}
