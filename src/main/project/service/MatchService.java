package project.service;

import project.dto.MatchDto;
import project.entity.Match;
import project.entity.Player;
import project.repository.MatchRepo;
import project.repository.PlayerRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MatchService {

    private static MatchService INSTANCE;
    private final PlayerRepo playerRepo = PlayerRepo.getInstance();
    private final MatchRepo matchRepo = MatchRepo.getInstance();
    Map<Integer, MatchDto> matches = new HashMap<>();

    private MatchService() {}

    public MatchDto startMatch(String playerName1, String playerName2) {

        Player player1 = playerRepo.findByName(playerName1)
                .orElseGet(() -> {
                            Player p = new Player(playerName1);
                            playerRepo.persist(p);
                            return p;
                        }
                );

        Player player2 = playerRepo.findByName(playerName2)
                .orElseGet(() -> {
                            Player p = new Player(playerName2);
                            playerRepo.persist(p);
                            return p;
                        }
                );

        Match match = new Match(player1, player2);
        matchRepo.persist(match);

        MatchDto matchDto =dtoMapper(match);
        matches.put(match.getId(), matchDto);

        return matchDto;
    }

    private static MatchDto dtoMapper(Match match){
        return new MatchDto(
                match.getId(),
                match.getPlayer1(),
                match.getPlayer2()
        );
    }

    public static MatchService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MatchService();
        }
        return INSTANCE;
    }
}
