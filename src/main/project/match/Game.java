package project.match;

import lombok.Getter;

public class Game {
    @Getter
    Player winner;
    Player playerOne, playerTwo;
    int playerOnePoints;
    int playerTwoPoints;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void pointWonBy(Player player) {
        if (winner != null) {
            throw new RuntimeException("Игра уже завершена");
        }
        if(player.equals(playerOne)) {
            playerOnePoints += 1;
        } else if(player.equals(playerTwo)) {
            playerTwoPoints += 1;
        } else {
            throw new RuntimeException("Игрок не участвует в матче");
        }

        checkForWinner();
    }

    private void checkForWinner()  {
        if (playerOnePoints >= 4 && playerOnePoints - playerTwoPoints >= 2) {
            winner = playerOne;
        } else if (playerTwoPoints >= 4 && playerTwoPoints - playerOnePoints >= 2) {
            winner = playerTwo;
        }
    }

    public boolean isFinished() {
        return winner != null;
    }

    public GamePoints getPoints() {

        if(isFinished()) {
            return winner == playerOne ? new GamePoints("G",getPointName(playerTwoPoints)) : new GamePoints(getPointName(playerOnePoints), "G");
        }

        if(playerOnePoints >= 3 && playerTwoPoints >=3){
            if(playerOnePoints == playerTwoPoints) {
                return new GamePoints("40","40");
            }  else if(playerOnePoints - playerTwoPoints == 1) {
                return new GamePoints("A","40");
            }  else if(playerTwoPoints - playerOnePoints == 1) {
                return new GamePoints("40","A");
            }
        }
        return new GamePoints(getPointName(playerOnePoints), getPointName(playerTwoPoints));

    }

    private String getPointName(int points) {
        return switch (points) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            default -> "40";
        };
    }


}
