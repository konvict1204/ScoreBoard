package project.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import project.entity.MatchEntity;
import project.entity.PlayerEntity;
import project.util.PoolManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchRepoITTest {
    MatchRepo matchRepo;
    PlayerRepo playerRepo;

    public MatchRepoITTest() {
        this.matchRepo = new MatchRepo();
        this.playerRepo = new PlayerRepo();
    }

    @AfterEach
    public void tearDown() {
        try(Session session = PoolManager.getSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("""
        TRUNCATE TABLE matches
        RESTART IDENTITY CASCADE
        """).execute();
            transaction.commit();

        }
    }

    @Test
    public void persistTest() {
        PlayerEntity firstPlayer = new PlayerEntity("John");
        PlayerEntity secondPlayer = new PlayerEntity("Jack");
        playerRepo.persist(firstPlayer);
        playerRepo.persist(secondPlayer);
        MatchEntity m = new MatchEntity(firstPlayer,secondPlayer,firstPlayer);
        matchRepo.persist(m);

        assertThat(m.getId()).isEqualTo(1);
    }
    @Test
    public void countMatches() {
        PlayerEntity firstPlayer = new PlayerEntity("John");
        PlayerEntity secondPlayer = new PlayerEntity("Jack");
        playerRepo.persist(firstPlayer);
        playerRepo.persist(secondPlayer);
        MatchEntity m = new MatchEntity(firstPlayer,secondPlayer,firstPlayer);
        matchRepo.persist(m);

        Long l = matchRepo.countMatches();

        assertThat(l).isEqualTo(1);
    }

}
