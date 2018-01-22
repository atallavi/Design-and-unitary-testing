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
            Money valueOfTheTicket = stockExchange.value(this.ticket);
            BigDecimal ratio = moneyExchange.exchangeRatio(valueOfTheTicket.getCurrency(), currencyTo);
            Money money = operationToDo(valueOfTheTicket, pricePerShare);

            return valueOfTheTicket.getCurrency().equals(currencyTo) ? money.multiply(numShares) :
                    money.change(ratio, currencyTo).multiply(numShares);
        }catch (Exception e) {
            throw new EvaluationException("Mssg");

        }
    }

    public abstract Money operationToDo (Money valueOfTheTicket, Money pricePerShare);

}
