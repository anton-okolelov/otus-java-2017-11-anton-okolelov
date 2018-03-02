package l10;

import l10.hibernate.DbServiceHibernateImpl;
import l10.hibernate.HibernateConfigurator;
import l10.model.AddressDataSet;
import l10.model.UserDataSet;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class dbServiceMyOrmImplTest {

    private DbServiceHibernateImpl dbServiceHibernate;

    @Before
    public void setUp() {
        String url = "jdbc:postgresql://localhost:5432/l09?user=anton&password=12345&ssl=false";

        Configuration hibernateConfiguration = (new HibernateConfigurator()).getTestConfiguration(url);
        dbServiceHibernate = new DbServiceHibernateImpl(hibernateConfiguration);
    }

    @Test
    public void saveNewUserHibernate() {
        UserDataSet user = new UserDataSet();
        user.setName("Ваня");
        user.setAge(15);

        dbServiceHibernate.save(user);
        UserDataSet result = dbServiceHibernate.read(1);

        assertEquals(15, result.getAge());
        assertEquals("Ваня", result.getName());
    }


    @Test
    public void saveNewUserWithAddressHibernate() {
        UserDataSet user = new UserDataSet();
        user.setName("Петя");
        user.setAge(20);
        user.setAddress(new AddressDataSet("Будапештская, 61"));

        dbServiceHibernate.save(user);
        UserDataSet result = dbServiceHibernate.read(1);

        assertEquals(20, result.getAge());
        assertEquals("Петя", result.getName());
        assertEquals("Будапештская, 61", result.getAddress().getStreet());
    }

    @After
    public void tearDown() {
        dbServiceHibernate.shutdown();
    }


}
