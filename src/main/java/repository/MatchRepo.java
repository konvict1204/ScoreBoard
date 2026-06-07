package repository;

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

}
