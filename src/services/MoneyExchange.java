package services;

import data.Currency;
import portfolio.EvaluationException;

import java.math.BigDecimal;

/**
 * External Service
 */
public interface MoneyExchange {
    /**
     * @param from Currency by origin
     * @param to Currency which we want to obtain the ratio
     * @return the ratio between the Currency from and to
     * @throws RatioDoesNotExistException in case that the service was unable to get the ratio
     * @throws EvaluationException should not be here, its a error made by me
     */
    BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException;
}
