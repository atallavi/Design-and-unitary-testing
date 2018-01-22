package services;

import data.Money;
import data.Ticket;

/**
 * External Service
 */
public interface StockExchange {
    /**
     * @param ticket ticket used
     * @return the value of the ticket
     * @throws TicketDoesNotExistException in case that the ticket does not exist
     * @throws RatioDoesNotExistException should not be here, its a error made by me
     */
    Money value(Ticket ticket) throws TicketDoesNotExistException, RatioDoesNotExistException;
}
