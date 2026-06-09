package project.service;

import project.dto.MatchDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchServiceTest {

    MatchService service = MatchService.getInstance();;
    String player1 = "player1";
    String player2 = "player2";


    @Test
    public void happyPass(){

        MatchDto match = service.startMatch(player1, player2);

        assertThat(match).isNotNull();
        assertThat(match.getPlayer1().getName()).isEqualTo(player1);
        assertThat(match.getPlayer2().getName()).isEqualTo(player2);
        assertThat(match.getId()).isEqualTo(1);

    }
}
