package project.match;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SetTest {
    Player playerOne = new Player("playerOne");
    Player  playerTwo = new Player("playerTwo");
    Set set;

    @Test
    public void testAddingPoints(){
        set = new Set(playerOne,playerTwo);
        GamePoints expectedGameScore = new GamePoints("15","15");
        Point expectedSetPoint = new Point(0,0);

        set.pointWonBy(playerOne);
        set.pointWonBy(playerTwo);

        assertThat(set.getCurrentGameScore().isPresent());
        assertThat(set.getCurrentGameScore().get()).isEqualTo(expectedGameScore);
        assertThat(set.getSetPoint()).isEqualTo(expectedSetPoint);
    }
    @Test
    public void testAddingSetPoints(){
        set = new Set(playerOne,playerTwo);
        GamePoints expectedGameScore = new GamePoints("0","0");
        Point expectedSetPoint = new Point(1,1);

        for(int i=0; i<4; i++){
            set.pointWonBy(playerOne);
        }
        for(int i=0; i<4; i++){
            set.pointWonBy(playerTwo);
        }
        assertThat(set.getCurrentGameScore().isPresent());
        assertThat(set.getCurrentGameScore().get()).isEqualTo(expectedGameScore);
        assertThat(set.getSetPoint()).isEqualTo(expectedSetPoint);
    }
    @Test
    public void testFirstPlayerWins(){
        set = new Set(playerOne,playerTwo);
        Point expectedSetPoint = new Point(6,0);

        for(int i=0; i<24; i++){
            set.pointWonBy(playerOne);

        }

        assertThat(set.getSetPoint()).isEqualTo(expectedSetPoint);
        assertThat(set.isFinished()).isEqualTo(true);
    }
    @Test
    public void testSecondPlayerWins(){
        set = new Set(playerOne,playerTwo);
        Point expectedSetPoint = new Point(0,6);

        for(int i=0; i<24; i++){
            set.pointWonBy(playerTwo);
        }

        assertThat(set.getSetPoint()).isEqualTo(expectedSetPoint);
        assertThat(set.isFinished()).isEqualTo(true);
    }
    @Test
    public void testDeuceStage(){
        set = new Set(playerOne,playerTwo);
        Point expectedTiePoint = new Point(7,0);
        Point expectedSetPoint = new Point(7,6);

        for(int i=1; i<13; i++){
            if((i&2)==0){
                for(int j=0; j<4; j++){
                    set.pointWonBy(playerOne);
                }
            } else {
                for(int j=0; j<4; j++){
                    set.pointWonBy(playerTwo);
                }
            }
        }
        for(int j=0; j<7; j++){
            set.pointWonBy(playerOne);
        }
        assertThat(set.getCurrentGameScore().isPresent()).isFalse();
        assertThat(set.getTieScore().isPresent()).isTrue();
        assertThat(set.getTieScore().get()).isEqualTo(expectedTiePoint);
        assertThat(set.getSetPoint()).isEqualTo(expectedSetPoint);
        assertThat(set.winner).isNotNull();
        assertThat(set.winner).isEqualTo(playerOne);
    }

    // Добавить тест на ошибки
}
