package project.exceptions;

public class MatchDoesntExist extends RuntimeException {
    public MatchDoesntExist(String message) {
        super(message);
    }
}
