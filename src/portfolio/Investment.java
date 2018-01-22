package portfolio;

import data.Currency;
import data.Money;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

public interface Investment {

    /**
     * @param currencyTo The currency of the money returned.
     * @param moneyExchange It's a external service.
     * @param stockExchange It's a external service.
     * @return new Money properly evaluated
     * @throws EvaluationException In case something went wrong
     */
    Money evaluate (Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException;
}
