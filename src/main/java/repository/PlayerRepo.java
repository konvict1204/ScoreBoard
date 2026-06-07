package repository;

import entity.Player;
import org.hibernate.Session;
import util.PoolManager;

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


    public Player findByName(String playerName) {
        Player player;
        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();

            player = session.get(Player.class, playerName);

            session.getTransaction().commit();
        }
        return player;
    }

    public Integer persist(Player player) {
        try(Session session = PoolManager.getSession()) {
            session.beginTransaction();

            session.persist(player);
            List<Player> ls = session.createQuery("SELECT u FROM Player u", Player.class).getResultList();
            ls.forEach(System.out::println);



            session.getTransaction().commit();

        }

        return player.getId();
    }

    public static PlayerRepo getInstance() {
        return INSTANCE;
    }
}
