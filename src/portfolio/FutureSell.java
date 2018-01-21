package portfolio;

import data.Currency;
import data.Money;
import data.Ticket;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

public class FutureSell extends AbstractFuture {

    public FutureSell(Ticket ticket, int numShares, Money pricePerShare) {
        super(ticket, numShares, pricePerShare);
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange) throws EvaluationException {
        return super.evaluate(currencyTo, moneyExchange, stockExchange);
    }
}
