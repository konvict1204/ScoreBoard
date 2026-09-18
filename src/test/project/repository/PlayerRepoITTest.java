package project.repository;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import project.entity.PlayerEntity;
import project.util.PoolManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerRepoITTest {
    PlayerRepo playerRepo;


    @AfterEach
    public void tearDown() {
        try(Session session = PoolManager.getSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("""
        TRUNCATE TABLE players
        RESTART IDENTITY CASCADE
        """).execute();
            transaction.commit();

        }
    }

    @Test
    void persistHappyCase(){
        PlayerEntity player = new PlayerEntity("Jack");
        playerRepo = new PlayerRepo();

        playerRepo.persist(player);

        assertThat(player.getId()).isEqualTo(1);
    }
}
