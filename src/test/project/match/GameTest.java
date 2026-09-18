package project.match;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GameTest {

    Player playerOne = new Player("playerOne");
    Player playerTwo = new Player("playerTwo");
    Game game;

    @Test
    public void testAddingPoints() {
        game = new Game(playerOne, playerTwo);
        GamePoints expected = new GamePoints("15","15");

        game.pointWonBy(playerOne);
        game.pointWonBy(playerTwo);

        assertThat(game.getPoints()).isEqualTo(expected);
    }
    @Test
    public void testFirstPlayerWins() {
        game = new Game(playerOne, playerTwo);
        GamePoints expected = new GamePoints("G","30");

        game.pointWonBy(playerTwo);
        game.pointWonBy(playerTwo);
        for(int i=0; i<4; i++) {
            game.pointWonBy(playerOne);
        }



        assertThat(game.isFinished()).isTrue();
        assertThat(game.getPoints()).isEqualTo(expected);
    }

    @Test
    public void testSecondPlayerWins() {
        game = new Game(playerOne, playerTwo);
        GamePoints expected = new GamePoints("30","G");

        game.pointWonBy(playerOne);
        game.pointWonBy(playerOne);
        for(int i=0; i<4; i++) {
            game.pointWonBy(playerTwo);
        }


        assertThat(game.isFinished()).isTrue();
        assertThat(game.getPoints()).isEqualTo(expected);
    }
    @Test
    public void testDeuceStage() {
        game = new Game(playerOne, playerTwo);
        GamePoints expected = new GamePoints("A","40");

        for(int i=0; i<3; i++) {
            game.pointWonBy(playerOne);
        }
        for(int i=0; i<3; i++) {
            game.pointWonBy(playerTwo);
        }
        game.pointWonBy(playerOne);
        game.pointWonBy(playerTwo);
        game.pointWonBy(playerOne);


        assertThat(game.isFinished()).isFalse();
        assertThat(game.getPoints()).isEqualTo(expected);
    }

    @Test
    public void testExceptionIfMatchIsFinished() {
        game = new Game(playerOne, playerTwo);
        for(int i=0; i<4; i++) {
            game.pointWonBy(playerOne);
        }
        assertThatThrownBy(() -> game.pointWonBy(playerOne))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Игра уже завершена");
    }

    @Test
    public void testExceptionIfWrongUser() {
        game = new Game(playerOne, playerTwo);

        assertThatThrownBy(() -> game.pointWonBy(new Player("playerThree")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Игрок не участвует в матче");
    }

}
