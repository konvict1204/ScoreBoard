package project.match;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TieBreakTest {

    Player playerOne = new Player("playerOne");
    Player playerTwo = new Player("playerTwo");
    TieBreak tieBreak;


    @Test
    public void testAddingPoints() {
        tieBreak = new TieBreak(playerOne, playerTwo);
        Point expected = new Point(1,1);

        tieBreak.pointWonBy(playerOne);
        tieBreak.pointWonBy(playerTwo);

        assertThat(tieBreak.getScore()).isEqualTo(expected);
    }
    @Test
    public void testFirstPlayerWins() {
        tieBreak = new TieBreak(playerOne, playerTwo);
        Point expected = new Point(7,2);

        tieBreak.pointWonBy(playerTwo);
        tieBreak.pointWonBy(playerTwo);
        for(int i=0; i<7; i++) {
            tieBreak.pointWonBy(playerOne);
        }

        assertThat(tieBreak.isFinished()).isTrue();
        assertThat(tieBreak.getScore()).isEqualTo(expected);
    }
    @Test
    public void testSecondPlayerWins() {
        tieBreak = new TieBreak(playerOne, playerTwo);
        Point expected = new Point(2,7);

        tieBreak.pointWonBy(playerOne);
        tieBreak.pointWonBy(playerOne);
        for(int i=0; i<7; i++) {
            tieBreak.pointWonBy(playerTwo);
        }

        assertThat(tieBreak.isFinished()).isTrue();
        assertThat(tieBreak.getScore()).isEqualTo(expected);
    }

    @Test
    public void testDeuceStage() {
        tieBreak = new TieBreak(playerOne, playerTwo);
        Point expected = new Point(8,7);

        for(int i=0; i<6; i++) {
            tieBreak.pointWonBy(playerOne);
        }
        for(int i=0; i<6; i++) {
            tieBreak.pointWonBy(playerTwo);
        }
        tieBreak.pointWonBy(playerOne);
        tieBreak.pointWonBy(playerTwo);
        tieBreak.pointWonBy(playerOne);

        assertThat(tieBreak.isFinished()).isFalse();
        assertThat(tieBreak.getScore()).isEqualTo(expected);
    }

    @Test
    public void testExceptionIfMatchIsFinished() {
        tieBreak = new TieBreak(playerOne, playerTwo);
        for(int i=0; i<7; i++) {
            tieBreak.pointWonBy(playerOne);
        }
        assertThatThrownBy(() -> tieBreak.pointWonBy(playerOne))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Тай брейк уже завершен");
    }

    @Test
    public void testExceptionIfWrongUser() {
        tieBreak = new TieBreak(playerOne, playerTwo);

        assertThatThrownBy(() -> tieBreak.pointWonBy(new Player("playerThree")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Данный игрок не участвует в матче");
    }
}
