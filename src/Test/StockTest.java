package Test;

import data.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import portfolio.EvaluationException;
import portfolio.Stock;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;
import java.math.BigDecimal;

public class StockTest {

    MoneyExchangeDoubleReturnsBigD moneyExchangeDouble;
    StockExchangeDoubleReturnsMoney stockExchangeDouble;
    Stock stock;
    Currency currencyFrom;

    @Before
    public void setUp() throws Exception {
        moneyExchangeDouble = new MoneyExchangeDoubleReturnsBigD();
        stockExchangeDouble = new StockExchangeDoubleReturnsMoney();
        stock = new Stock(new Ticket("TST"), 7);
        currencyFrom = new Currency("anyCurrencyFrom");
    }


    @Test
    public void evaluate_with_same_currency_returns_result() throws Exception {
        Money money = stock.evaluate(currencyFrom, moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("70.00"), currencyFrom), money);

    }
    @Test
    public void evaluate_returns_result() throws Exception {
        Money money = stock.evaluate(new Currency("anyCurrencyTo"), moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("108.5"), new Currency("anyCurrencyTo")), money);

    }

    public class StockExchangeDoubleReturnsMoney implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            return new Money(new BigDecimal("10.00"), currencyFrom);
        }
    }

    public class MoneyExchangeDoubleReturnsBigD implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            return new BigDecimal("1.55");
        }
    }

    @Test (expected = EvaluationException.class)
    public void evaluate_with_no_exchange_ratio_avaliable_throws_exception () throws EvaluationException {
        Money money = stock.evaluate(currencyFrom, new MoneyExchangeDoubleThrowsRatioException(), stockExchangeDouble);
    }
    public class MoneyExchangeDoubleThrowsRatioException implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException {
            throw new RatioDoesNotExistException("No ratio available");
        }
    }

    @Test (expected = EvaluationException.class)
    public void evaluate_with_none_existent_ticket () throws Exception {
        Money money = stock.evaluate(currencyFrom, moneyExchangeDouble, new StockExchangeDoubleThrowsTicketException());
    }

    public class StockExchangeDoubleThrowsTicketException implements StockExchange {

        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            throw new TicketDoesNotExistException("Ticket does not exist");
        }
    }

    @Test (expected = EvaluationException.class)
    public void evaluate_ () throws Exception {
        Money money = stock.evaluate(currencyFrom, new MoneyExchangeDoubleThrowsRatioException(), stockExchangeDouble);
    }

    public class MoneyExchangeDoubleRatioDoesNotExist implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            throw new RatioDoesNotExistException("Ratio does not exist");
        }
    }





}