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
    Currency currencyTo;

    @Before
    public void setUp() throws Exception {
        moneyExchangeDouble = new MoneyExchangeDoubleReturnsBigD();
        stockExchangeDouble = new StockExchangeDoubleReturnsMoney();
        stock = new Stock(new Ticket("TST"), 7);
        currencyTo = new Currency("anyCurrencyFrom");
    }

    @Test
    public void evaluate_returns_result() throws Exception {
        Money money = stock.evaluate(currencyTo, moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("1.55"), currencyTo), money);

    }

    public class StockExchangeDoubleReturnsMoney implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            return new Money(new BigDecimal("1.00"), new Currency("anyCurrencyTo"));
        }
    }

    public class MoneyExchangeDoubleReturnsBigD implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            return new BigDecimal("1.55");
        }
    }

    @Test (expected = RatioDoesNotExistException.class)
    public void evaluate_with_no_exchange_ratio_avaliable_throws_exception () throws Exception {
        Money money = stock.evaluate(currencyTo, new MoneyExchangeDoubleThrowsRatioException(), stockExchangeDouble);
    }
    public class MoneyExchangeDoubleThrowsRatioException implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            throw new RatioDoesNotExistException("No ratio available");
        }
    }

    @Test (expected = TicketDoesNotExistException.class)
    public void evaluate_with_unnexistent_ticket () throws Exception {
        Money money = stock.evaluate(currencyTo, moneyExchangeDouble, new StockExchangeDoubleThrowsTicketException());
    }

    public class StockExchangeDoubleThrowsTicketException implements StockExchange {

        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            throw new TicketDoesNotExistException("Ticket does not exist");
        }
    }




}