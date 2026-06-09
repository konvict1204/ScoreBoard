package project.repository;

import org.hibernate.Session;
import project.entity.Match;
import project.util.PoolManager;

import java.util.Optional;

public class MatchRepo {

    private static MatchRepo INSTANCE;

    private MatchRepo() {}



    public static MatchRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MatchRepo();
        }
        return INSTANCE;
    }

    public void persist(Match match) {
        try (Session session = PoolManager.getSession()) {
            session.beginTransaction();

            session.persist(match);

            session.getTransaction().commit();
        }

    }
}
