package project.match;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Point {
    private int firstPlayer;
    private int secondPlayer;

    public Point() {
        firstPlayer = 0;
        secondPlayer = 0;
    }
    public Point(int playerOneScore, int playerTwoScore) {
        this.firstPlayer = playerOneScore;
        this.secondPlayer = playerTwoScore;
    }

    public void firstPlayerWins() {
        firstPlayer++;
    }
    public void secondPlayerWins() {
        secondPlayer++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point setPoint = (Point) o;
        return firstPlayer == setPoint.firstPlayer && secondPlayer == setPoint.secondPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPlayer, secondPlayer);
    }
}
