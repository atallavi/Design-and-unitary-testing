package Test;

import data.Currency;
import data.Money;
import data.Ticket;
import org.junit.Before;
import org.junit.Test;
import portfolio.Cash;
import portfolio.EvaluationException;
import services.*;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class CashTest {

    private Currency currencyTo;
    private Cash cash;
    private StockExchangeDouble stockExchangeDouble;

    @Before
    public void setUp() {
        currencyTo = new Currency("anyCurrencyTo");
        cash = new Cash(new Money(new BigDecimal("7.0"), new Currency("anyCurrency")));
        stockExchangeDouble = new StockExchangeDouble();

    }


    @Test (expected = EvaluationException.class)
    public void evaluate_none_existent_ratio_throws_exception() throws EvaluationException {
        Money newMoney = cash.evaluate(currencyTo, new MoneyExchangeDoubleThrowsRatioException(), stockExchangeDouble);
    }

    public class MoneyExchangeDoubleThrowsRatioException implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency currencyFrom, Currency currencyTo) throws RatioDoesNotExistException {
            throw new RatioDoesNotExistException("No ratio existent");
        }
    }


    @Test (expected = EvaluationException.class)
    public  void  evaluate_gets_evaluaton_exception() throws EvaluationException, RatioDoesNotExistException {
        Money newMoney = cash.evaluate(currencyTo, new MoneyExchangeDoubleThrowsEvaluationException(),
                stockExchangeDouble);
    }

    public class MoneyExchangeDoubleThrowsEvaluationException implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency currencyFrom, Currency currencyTo) throws EvaluationException {
            throw new EvaluationException("Something gone wrong");
        }
    }

    @Test (expected = EvaluationException.class)
    public void evaluate_same_currencies() throws RatioDoesNotExistException, EvaluationException {
        Cash cash = new Cash(new Money(new BigDecimal("7.0"), currencyTo));
        Money newMoney = cash.evaluate(currencyTo, new MoneyExchangeDouble(), stockExchangeDouble);
    }

    public  class MoneyExchangeDouble implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency currencyFrom, Currency currencyTo) throws RatioDoesNotExistException {
            if (currencyFrom.equals(currencyTo)) {
                throw new RatioDoesNotExistException("No ratio existent");
            }
            return new BigDecimal("0.7");
        }
    }

    @Test
    public  void evaluate_with_proper_parameters_gets_correct_result () throws EvaluationException, RatioDoesNotExistException {
        Money evaluate = cash.evaluate(currencyTo, new MoneyExchangeDouble(), stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("4.9"), currencyTo),evaluate);
    }

    public class StockExchangeDouble implements StockExchange{

        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            throw new UnsupportedOperationException();
        }
    }



}