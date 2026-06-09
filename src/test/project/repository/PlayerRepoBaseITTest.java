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
                    "TRUNCATE TABLE matches RESTART IDENTITY"
            ).executeUpdate();
            session.getTransaction().commit();
        }
    }

}
