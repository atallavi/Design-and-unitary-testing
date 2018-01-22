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

    /**
     * @param ticket The ticket of the company
     * @param numShares the num of contracts in the operation
     */
    public Stock(Ticket ticket, int numShares) {
        this.ticket = ticket;
        this.numShares = numShares;
    }

    /**
     * @param currencyTo    The currency of the money returned.
     * @param moneyExchange It's a external service.
     * @param stockExchange It's a external service.
     * @return A new money result to evaluate with the params
     * @throws EvaluationException In case something went wrong
     */
    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException{

        try {
            Money moneyOfTicket = stockExchange.value(this.ticket);
            BigDecimal ratio = moneyExchange.exchangeRatio(moneyOfTicket.getCurrency(), currencyTo);
            return moneyOfTicket.getCurrency().equals(currencyTo) ? moneyOfTicket.multiply(numShares) :
                    moneyOfTicket.multiply(numShares).change(ratio, currencyTo);
        }catch (Exception e) {
            throw new EvaluationException("Evaluation exception in Stock");
        }

    }
}
