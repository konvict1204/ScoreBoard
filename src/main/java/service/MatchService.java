package service;

import dto.MatchDto;
import entity.Player;
import repository.MatchRepo;
import repository.PlayerRepo;

public class MatchService {

    private static MatchService INSTANCE;
    private PlayerRepo playerRepo = PlayerRepo.getInstance();

    private MatchService() {}

    public MatchDto startMatch(String player1, String player2) {
        Player player = playerRepo.findByName(player1);
        if(player==null){
            playerRepo.persist(null);
        }

        return new MatchDto();
    }

    public static MatchService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MatchService();
        }
        return INSTANCE;
    }
}
