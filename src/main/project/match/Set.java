package project.match;

import lombok.Getter;

import java.util.Optional;

public class Set {
    Player firstPlayer;
    Player secondPlayer;
    @Getter
    Player winner;
    @Getter
    Point setPoint;
    Game currentGame;
    TieBreak tieBreak;


    public Set(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        currentGame = new Game(firstPlayer,secondPlayer);
        setPoint = new Point();
    }

    public void pointWonBy(Player player){
        if(winner != null){
            throw new RuntimeException("Сет уже завершен.");
        }
        if(tieBreak != null){
            tieBreak.pointWonBy(player.equals(firstPlayer) ? firstPlayer : secondPlayer);
            if(tieBreak.isFinished()){
                if(tieBreak.getWinner() == firstPlayer){
                    setPoint.firstPlayerWins();
                } else {
                    setPoint.secondPlayerWins();
                }
                checkForWinner();
            }
            return;
        }
        currentGame.pointWonBy(player.equals(firstPlayer) ? firstPlayer : secondPlayer);

        if (currentGame.isFinished()) {
            if (currentGame.getWinner() == firstPlayer) {
                setPoint.firstPlayerWins();
            } else {
                setPoint.secondPlayerWins();
            }
            currentGame = new Game(firstPlayer, secondPlayer);

            if (setPoint.getFirstPlayer() == 6 && setPoint.getSecondPlayer() == 6) {
                tieBreak = new TieBreak(firstPlayer, secondPlayer);
            } else {
                checkForWinner();
            }
        }


    }

    private void checkForWinner() {
        if (setPoint.getFirstPlayer() >= 6 && setPoint.getFirstPlayer() - setPoint.getSecondPlayer() >= 2) {
            winner = firstPlayer;
        } else if (setPoint.getSecondPlayer() >= 6 && setPoint.getSecondPlayer() - setPoint.getFirstPlayer() >= 2) {
            winner = secondPlayer;
        } else if (setPoint.getFirstPlayer() == 7 && setPoint.getSecondPlayer() == 6) {
            winner = firstPlayer;
        } else if (setPoint.getSecondPlayer() == 7 && setPoint.getFirstPlayer() == 6) {
            winner = secondPlayer;
        }
    }

    public boolean isFinished(){
        return winner != null;
    }


    public Optional<GamePoints> getCurrentGameScore(){

        return  tieBreak != null ? Optional.empty() : Optional.of(currentGame.getPoints());

    }

    public Optional<Point> getTieScore(){

        return tieBreak == null ? Optional.empty() : Optional.of(tieBreak.getScore());

    }

}
