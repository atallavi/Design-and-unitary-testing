package portfolio;

import data.Currency;
import data.Money;
import data.Ticket;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;

public class FutureBuy implements Investment {

    Ticket ticket;
    int numShares;
    Money pricePerShare;

    public FutureBuy(Ticket ticket, int numShares, Money pricePerShare) {
        this.ticket = ticket;
        this.numShares = numShares;
        this.pricePerShare = pricePerShare;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {

        Money moneyOfTicket = stockExchange.value(this.ticket);
        BigDecimal ratio = moneyExchange.exchangeRatio(moneyOfTicket.getCurrency(), currencyTo);
        Money subtract = (moneyOfTicket.change(ratio, currencyTo)).subtract(pricePerShare);
        return subtract.multiply(numShares);
    }


}
