package portfolio;

import data.Currency;
import data.Money;
import services.MoneyExchange;
import services.StockExchange;

public interface Investment {
    Money evaluate (Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange) throws EvaluationException;
}