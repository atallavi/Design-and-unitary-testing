package services;

import data.Currency;
import portfolio.EvaluationException;

import java.math.BigDecimal;

public interface MoneyExchange {
    BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException;
}
