package project.repository;


import org.hibernate.Session;
import project.entity.PlayerEntity;
import project.util.PoolManager;

import java.util.Optional;


public class PlayerRepo {


    public Optional<PlayerEntity> findByName(String player) {
        PlayerEntity maybePlayer;

        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();
            String query = "SELECT p FROM PlayerEntity p WHERE p.name = :player";

            maybePlayer = session.createQuery(query, PlayerEntity.class)
                    .setParameter("player", player)
                    .uniqueResult();

            session.getTransaction().commit();
        }

        return Optional.ofNullable(maybePlayer);
    }

    public void persist(PlayerEntity player) {
        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
        }

    }

}
