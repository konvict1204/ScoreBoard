package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import util.PoolManager;

public class PlayerRepoBaseITTest {

    @AfterEach
    void cleanUp() {
        try (Session session = PoolManager.getSession()) {
            session.beginTransaction();
            session.createNativeMutationQuery(
                    "TRUNCATE TABLE players RESTART IDENTITY"
            ).executeUpdate();
            session.getTransaction().commit();
        }
    }

}
