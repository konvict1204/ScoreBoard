package project.repository;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import project.util.PoolManager;

public class PlayerRepoBaseITTest {

    @AfterEach
    void cleanUp() {
        try (Session session = PoolManager.getSession()) {
            session.beginTransaction();
            session.createNativeMutationQuery(
                    "DELETE FROM players;" +
                            "ALTER TABLE players ALTER COLUMN id RESTART WITH 1;"
            ).executeUpdate();
            session.getTransaction().commit();
        }
    }

}
