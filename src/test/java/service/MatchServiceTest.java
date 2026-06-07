package service;

import dto.MatchDto;
import entity.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchServiceTest {

    MatchService service;
    String player1;
    String player2;


    @Test
    public void happyPass(){
        service= MatchService.getInstance();

        MatchDto match = service.startMatch(player1, player2);

        assertThat(match).isNotNull();

    }
}
