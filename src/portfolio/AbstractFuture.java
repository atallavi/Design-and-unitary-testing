package portfolio;

import data.Currency;
import data.Money;
import data.Ticket;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;

import java.math.BigDecimal;

public abstract class AbstractFuture implements Investment{

    private Ticket ticket;
    private int numShares;
    private Money pricePerShare;

    public AbstractFuture(Ticket ticket, int numShares, Money pricePerShare) {
        this.ticket = ticket;
        this.numShares = numShares;
        this.pricePerShare = pricePerShare;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException {

        try {
            Money valueOfTicket = stockExchange.value(this.ticket);
            BigDecimal ratio = moneyExchange.exchangeRatio(valueOfTicket.getCurrency(), currencyTo);
            Money subtract = valueOfTicket.subtract(pricePerShare);

            return (subtract.getCurrency() == currencyTo) ? subtract.multiply(numShares) :
                    subtract.change(ratio, currencyTo).multiply(numShares);

        }catch (Exception e) {
            throw new EvaluationException("Mssg");
        }
    }

}
