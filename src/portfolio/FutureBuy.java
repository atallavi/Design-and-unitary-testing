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

    private Ticket ticket;
    private int numShares;
    private Money pricePerShare;

    public FutureBuy(Ticket ticket, int numShares, Money pricePerShare) {
        this.ticket = ticket;
        this.numShares = numShares;
        this.pricePerShare = pricePerShare;
    }
    public Ticket getTicket() {
        return ticket;
    }

    public int getNumShares() {
        return numShares;
    }

    public Money getPricePerShare() {
        return pricePerShare;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {

        Money valueOfTicket = stockExchange.value(this.ticket);
        BigDecimal ratio = moneyExchange.exchangeRatio(valueOfTicket.getCurrency(), currencyTo);
        Money subtract = valueOfTicket.subtract(pricePerShare);
        return subtract.change(ratio, currencyTo).multiply(numShares);
    }


}
