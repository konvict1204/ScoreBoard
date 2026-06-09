package project.repository;

import org.junit.jupiter.api.Test;
import project.entity.Match;
import project.entity.Player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchRepoITTest {
    MatchRepo matchRepo = MatchRepo.getInstance();
    PlayerRepo playerRepo = PlayerRepo.getInstance();
    Player player1 = new Player("Player1");
    Player player2 = new Player("Player2");
    Match match = new Match(player1, player2);

    {
        playerRepo.persist(player1);
        playerRepo.persist(player2);
    }



    @Test
    void persistHP() {
        matchRepo.persist(match);

        assertThat(match.getId()).isEqualTo(1);
    }
}
