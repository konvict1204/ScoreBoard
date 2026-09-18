package project.service;

import project.dto.MatchDto;
import org.junit.jupiter.api.Test;
import project.dto.PlayerScoreDto;
import project.repository.MatchRepo;
import project.repository.PlayerRepo;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchServiceTest {

    MatchService service = new MatchService(new MatchRepo(), new PlayerRepo());
    String player1 = "player1";
    String player2 = "player2";


    @Test
    public void createMatchTest() {

        String uuid = service.startMatch(player1, player2);

        assertThat(uuid).isNotNull();
    }

    @Test
    public void pointAddTest(){
        String uuid = service.startMatch(player1, player2);
        service.pointWinsBy(uuid, player1);
        MatchDto matchDto = service.pointWinsBy(uuid, player2);

        assertThat(matchDto).isNotNull();
        assertThat(matchDto.firstPlayer().points()).isEqualTo("15");
        assertThat(matchDto.secondPlayer().points()).isEqualTo("15");
    }
    @Test
    public void firstPlayerWinTest(){
        String uuid = service.startMatch(player1, player2);
        MatchDto matchDto = null;
        for(int i=0; i<48; i++){
            matchDto = service.pointWinsBy(uuid, player1);
        }
        assertThat(matchDto).isNotNull();
        assertThat(matchDto.winnerName()).isNotNull();
        assertThat(matchDto.firstPlayer().sets()).isEqualTo(2);
        assertThat(matchDto.winnerName()).isEqualTo(player1);
    }
    @Test
    public void secondPlayerWinTest(){
        String uuid = service.startMatch(player1, player2);
        MatchDto matchDto = null;
        for(int i=0; i<48; i++){
            matchDto = service.pointWinsBy(uuid, player2);
        }
        assertThat(matchDto).isNotNull();
        assertThat(matchDto.winnerName()).isNotNull();
        assertThat(matchDto.secondPlayer().sets()).isEqualTo(2);
        assertThat(matchDto.winnerName()).isEqualTo(player2);
    }

    @Test
    public void tieBreakFuncTest(){
        String uuid = service.startMatch(player1, player2);
        MatchDto matchDto = null;

        for(int i=1; i<13; i++){
            if(i%2==0){
                for(int j=0; j<4; j++){
                    matchDto = service.pointWinsBy(uuid, player1);
                }
            } else {
                for(int j=0; j<4; j++){matchDto = service.pointWinsBy(uuid, player2);}
            }
        }

        PlayerScoreDto firstPlayer = matchDto.firstPlayer();
        PlayerScoreDto secondPlayer = matchDto.secondPlayer();

        assertThat(matchDto).isNotNull();
        assertThat(firstPlayer.games()).isEqualTo(6);
        assertThat(secondPlayer.games()).isEqualTo(6);
        assertThat(firstPlayer.points()).isNull();
        assertThat(secondPlayer.points()).isNull();
        assertThat(firstPlayer.tieBreakPoints()).isEqualTo(0);
        assertThat(secondPlayer.tieBreakPoints()).isEqualTo(0);
    }

    @Test
    public void getMatchTest(){
        String uuid = service.startMatch(player1, player2);

        MatchDto matchDto = service.getMatch(uuid);

        assertThat(matchDto).isNotNull();
    }

}
