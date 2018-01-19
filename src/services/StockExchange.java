package services;

import data.Money;
import data.Ticket;

public interface StockExchange {
    Money value(Ticket ticket) throws TicketDoesNotExistException;
}
