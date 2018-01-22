package portfolio;

import data.Currency;
import data.Money;
import data.Ticket;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;

import java.math.BigDecimal;

/**
 * Uses abstract class in order to not duplicate code in FutureBuy and FutureSell
 */
public abstract class AbstractFuture implements Investment{

    private Ticket ticket;
    private int numShares;
    private Money pricePerShare;

    /**
     * @param ticket is the ticket used in a future operation
     * @param numShares is the number of tickets bought/sold
     * @param pricePerShare the price agreed to operate
     */
    public AbstractFuture(Ticket ticket, int numShares, Money pricePerShare) {
        this.ticket = ticket;
        this.numShares = numShares;
        this.pricePerShare = pricePerShare;
    }

    /**
     * @param currencyTo Is the currency that will be in the money returned,
     * @param moneyExchange It's a external service.
     * @param stockExchange It's a external service.
     * @return A new money produced by evaluating with the external services.
     * @throws EvaluationException is thrown in case that something went wrong and thrown another exception which gets
     * cached.
     */
    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException {

        try {
            Money valueOfTheTicket = stockExchange.value(this.ticket);
            BigDecimal ratio = moneyExchange.exchangeRatio(valueOfTheTicket.getCurrency(), currencyTo);
            Money money = operationToDo(valueOfTheTicket, pricePerShare);

            return valueOfTheTicket.getCurrency().equals(currencyTo) ? money.multiply(numShares) :
                    money.change(ratio, currencyTo).multiply(numShares);
        }catch (Exception e) {
            throw new EvaluationException(e.toString());

        }
    }

    /**
     * @param valueOfTheTicket The value calculated.
     * @param pricePerShare The value agreed.
     * @return Depends on override on the class that extends this method.
     */
    public abstract Money operationToDo (Money valueOfTheTicket, Money pricePerShare);

}
