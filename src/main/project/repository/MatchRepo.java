package project.repository;

import org.hibernate.Session;
import project.entity.MatchEntity;
import project.util.PoolManager;

import java.util.List;

public class MatchRepo {

    public void persist(MatchEntity match) {
        try (Session session = PoolManager.getSession()) {
            session.beginTransaction();

            session.persist(match);

           session.getTransaction().commit();
        }

    }

    public Long countMatches() {
        try (Session session = PoolManager.getSession()) {
            session.beginTransaction();
            Long singleResult = session.createQuery("SELECT COUNT(*) FROM MatchEntity", Long.class).getSingleResult();
            session.getTransaction().commit();

            return singleResult;
        }
    }

    public List<MatchEntity> findMatches(Session session, int firstResult, int maxResults) {

        session.beginTransaction();
        List<MatchEntity> fromMatchEntity = session.createQuery("select m from MatchEntity m " +
                        "join fetch m.player1 " +
                        "join fetch m.player2 " +
                        "join fetch m.winner", MatchEntity.class)
                .setFirstResult((firstResult-1) * maxResults)
                .setMaxResults(maxResults)
                .list();
        session.getTransaction().commit();

        return fromMatchEntity;


    }
}
