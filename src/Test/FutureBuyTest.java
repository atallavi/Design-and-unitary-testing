package Test;

import data.Currency;
import data.Money;
import data.Ticket;
import org.junit.Before;
import org.junit.Test;
import portfolio.EvaluationException;
import portfolio.Stock;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FutureBuyTest {
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
    public void evaluate() throws Exception {

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

}