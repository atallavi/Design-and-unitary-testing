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

    MoneyExchangeDouble moneyExchangeDouble;
    StockExchangeDouble stockExchangeDouble;
    Stock stock;
    Currency currencyTo;

    @Before
    public void setUp() throws Exception {
        moneyExchangeDouble = new MoneyExchangeDouble();
        stockExchangeDouble = new StockExchangeDouble();
        stock = new Stock(new Ticket("TST"), 7);
        currencyTo = new Currency("anyCurrencyfrom");
    }

    @Test
    public void evaluate_returns_result() throws Exception {
        Money money = stock.evaluate(currencyTo, moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("1.55"), currencyTo), money);

    }

    public class StockExchangeDouble implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            return new Money(new BigDecimal("1.00"), new Currency("anyCurrencyTo"));
        }
    }

    public class MoneyExchangeDouble implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            return new BigDecimal("1.55");
        }
    }

}