package l10.hibernate;

import l10.model.AddressDataSet;
import l10.DbService;
import l10.model.UserDataSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DbServiceHibernateImpl implements DbService {

    private final SessionFactory sessionFactory;

    public DbServiceHibernateImpl(Configuration configuration) {

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDao dao = new UserDataSetDao(session);
            dao.save(dataSet);
        }
    }

    @Override
    public UserDataSet read(long id) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDao dao = new UserDataSetDao(session);
            return dao.read(id);
        }
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }

}
