package l01;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Main {
    public static void main(String[] args) {
        Multiset<String> names = HashMultiset.create();
        String name = "Антон";
        names.add(name);
        names.add(name);
        System.out.println(names.count(name));
    }
}
