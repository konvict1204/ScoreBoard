package project.match;

import lombok.Getter;

public class TieBreak {
    Player firstPlayer;
    Player secondPlayer;
    @Getter
    Player winner;
    int firstPlayerPoints;
    int secondPlayerPoints;

    public TieBreak(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
    }

    public void pointWonBy(Player player){
        if(winner != null){
            throw new RuntimeException("Тай брейк уже завершен");
        }
        if (firstPlayer == player){
            firstPlayerPoints++;
        } else if(secondPlayer == player) {
            secondPlayerPoints++;
        }  else {
            throw new RuntimeException("Данный игрок не участвует в матче");
        }
        checkForWinner();
    }

    public void checkForWinner(){
        if(firstPlayerPoints >= 7 && firstPlayerPoints - secondPlayerPoints >= 2){
            winner = firstPlayer;
        } else if (secondPlayerPoints >= 7 && secondPlayerPoints - firstPlayerPoints >= 2){
            winner = secondPlayer;
        }
    }

    public boolean isFinished(){
        return winner != null;
    }


    public Point getScore(){
        return new Point(firstPlayerPoints, secondPlayerPoints);
    }

}
