package project.match;

import lombok.Getter;

import java.util.Optional;

public class Match {
    @Getter
    Player firstPlayer, secondPlayer;
    @Getter
    Player winner;
    @Getter
    int firstPlayerSets;
    @Getter
    int secondPlayerSets;
    Set[] sets;
    Point[] points;
    Set currentSet;
    int currentSetNumber = 0;

    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        sets = new Set[]{
                new Set(firstPlayer, secondPlayer),
                new Set(firstPlayer, secondPlayer),
                new Set(firstPlayer, secondPlayer)};
        currentSet = sets[0];
        points = new Point[]{sets[0].getSetPoint(), sets[1].getSetPoint(), sets[2].getSetPoint()};
    }

    public void pointWonBy(Player player) {
        if (winner != null) {
            throw new RuntimeException("Матч уже закончен.");
        }
        currentSet.pointWonBy(player.equals(firstPlayer) ? firstPlayer : secondPlayer);

        if(currentSet.isFinished()) {

            if(currentSet.winner == firstPlayer) {
                firstPlayerSets++;
            } else {
                secondPlayerSets++;
            }

            checkForWinner();

            currentSetNumber++;
            if(winner == null){
                currentSet = sets[currentSetNumber];
            }
        }
    }
    public Point[] getScore() {
        return points;
    }
    public boolean isFinished() {
        return winner != null;
    }

    public Point getCurrentSetGameScore() {
        return currentSet.getSetPoint();
    }

    public Optional<GamePoints> getCurrentGamePoints() {
        return currentSet.getCurrentGameScore();
    }
    public Optional<String> getWinnerName() {
        return winner==null ? Optional.empty() : Optional.of(winner.name);
    }
    public Optional<Point> getCurrentTieScore(){
        return currentSet.getTieScore();
    }
    

    private void checkForWinner() {
        if(firstPlayerSets == 2){
            winner = firstPlayer;
        } else if(secondPlayerSets == 2){
            winner = secondPlayer;
        }
    }
}
