package portfolio;

import data.Currency;
import data.Money;
import data.Ticket;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;

public class Stock implements Investment {

    private Ticket ticket;
    private int numShares;

    public Stock(Ticket ticket, int numShares) {
        this.ticket = ticket;
        this.numShares = numShares;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {

        Money moneyOfTicket = stockExchange.value(this.ticket);
        BigDecimal ratio = moneyExchange.exchangeRatio(moneyOfTicket.getCurrency(), currencyTo);
        return moneyOfTicket.change(ratio, currencyTo);
    }
}
