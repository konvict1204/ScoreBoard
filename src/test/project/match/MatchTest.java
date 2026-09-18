package project.match;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchTest {
    Player playerOne = new Player("playerOne");
    Player playerTwo  = new Player("playerTwo");
    Match match;

    @Test
    public void TestGameAdd(){
        match = new Match(playerOne, playerTwo);
        Point[] expectedMatchPoint = new Point[]{
                new Point(0,0),
                new Point(0,0),
                new Point(0,0),
        };
        GamePoints expectedGameScore = new GamePoints("15","0");

        match.pointWonBy(playerOne);

        assertThat(match.getCurrentGamePoints().isPresent());
        assertThat(match.getCurrentGamePoints().get()).isEqualTo(expectedGameScore);
        assertThat(match.getScore()).isEqualTo(expectedMatchPoint);
    }

    @Test
    public void TestSetAdd(){
        match = new Match(playerOne, playerTwo);
        Point[] expectedMatchPoint = new Point[]{
                new Point(6,0),
                new Point(1,0),
                new Point(),
        };

        for (int i=0; i<30; i++){
            match.pointWonBy(playerOne);
        }

        assertThat(match.getScore()).isEqualTo(expectedMatchPoint);
    }

    @Test
    public void TestPlayerOneWins(){
        match = new Match(playerOne, playerTwo);
        Point[] expectedMatchPoint = new Point[]{
                new Point(6,0),
                new Point(0,6),
                new Point(6,0)
        };

        for (int i=0; i<24; i++){
            match.pointWonBy(playerOne);
        }
        for (int i=0; i<24; i++){
            match.pointWonBy(playerTwo);
        }
        System.out.println();
        for (int i=0; i<24; i++){
            match.pointWonBy(playerOne);
        }
        assertThat(match.isFinished()).isTrue();
        assertThat(match.getScore()).isEqualTo(expectedMatchPoint);

    }
    @Test
    public void TestPlayerTwoWins(){
        match = new Match(playerOne, playerTwo);
        Point[] expectedMatchPoint = new Point[]{
                new Point(0,6),
                new Point(6,0),
                new Point(0,6)
        };
        for (int i=0; i<24; i++){
            match.pointWonBy(playerTwo);
        }
        for (int i=0; i<24; i++){
            match.pointWonBy(playerOne);
        }
        System.out.println();
        for (int i=0; i<24; i++){
            match.pointWonBy(playerTwo);
        }
        assertThat(match.isFinished()).isTrue();
        assertThat(match.getScore()).isEqualTo(expectedMatchPoint);
    }

    // добавить тесты для исключении
}
