package project.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PoolManager {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }


    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
