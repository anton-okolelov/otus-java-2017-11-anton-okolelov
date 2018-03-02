package l10.hibernate;


import l10.model.UserDataSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDataSetDao {
    private Session session;

    public UserDataSetDao(Session session) {
        this.session = session;
    }

    public void save(UserDataSet dataSet) {
        Transaction transaction = session.beginTransaction();
        session.save(dataSet);
        transaction.commit();
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }

}