package services;

public class RatioDoesNotExistException extends Exception {
    public RatioDoesNotExistException(String message) {
        super(message);
    }
}
