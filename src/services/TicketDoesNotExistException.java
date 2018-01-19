package services;

public class TicketDoesNotExistException extends Exception {
    public TicketDoesNotExistException(String message) {
        super(message);
    }
}
