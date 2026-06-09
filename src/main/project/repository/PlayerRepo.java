package project.repository;


import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;
import project.entity.Player;
import project.util.PoolManager;

import java.util.List;
import java.util.Optional;


public class PlayerRepo {
    private static final  PlayerRepo INSTANCE = new PlayerRepo();

    private PlayerRepo(){}

    public  Optional<Player> findById(String id) {
        Player player;
        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();

            player = session.get(Player.class, id);

            session.getTransaction().commit();
        }

        return Optional.ofNullable(player);
    }


    public Optional<Player> findByName(String player) {
        Player maybePlayer = null;

        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();
            String query = "SELECT p FROM Player p WHERE p.name = :player";

            maybePlayer = session.createQuery(query, Player.class)
                    .setParameter("player", player)
                    .uniqueResult();

            session.getTransaction().commit();
        }

        return Optional.ofNullable(maybePlayer);
    }

    public void persist(Player player) {
        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
        }

    }

    public static PlayerRepo getInstance() {
        return INSTANCE;
    }
}
