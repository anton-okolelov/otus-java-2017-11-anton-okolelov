package l10.hibernate;

import org.hibernate.cfg.Configuration;


public class HibernateConfigurator {
    public Configuration getTestConfiguration(String connectionUrl) {
        Configuration configuration = new Configuration();
        // пересоздание таблиц
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", connectionUrl);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return configuration;
    }
}
