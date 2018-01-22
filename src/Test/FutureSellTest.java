package Test;

import data.Currency;
import data.Money;
import data.Ticket;
import org.junit.Before;
import org.junit.Test;
import portfolio.EvaluationException;
import portfolio.FutureSell;
import portfolio.Stock;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FutureSellTest {
    MoneyExchangeDoubleReturnsBigD moneyExchangeDouble;
    StockExchangeDoubleReturnsMoney stockExchangeDouble;
    FutureSell futureSell;
    Currency currencyFrom;
    Currency currencyTo;
    Ticket ticket;

    @Before
    public void setUp() throws Exception {
        moneyExchangeDouble = new MoneyExchangeDoubleReturnsBigD();
        stockExchangeDouble = new StockExchangeDoubleReturnsMoney();
        currencyFrom = new Currency("anyCurrencyFrom");
        currencyTo = new Currency("anyCurrencyTo");
        ticket = new Ticket("ANY");

    }

    public class StockExchangeDoubleReturnsMoney implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            return new Money(new BigDecimal("100"), currencyFrom);
        }
    }

    public class MoneyExchangeDoubleReturnsBigD implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            return new BigDecimal("1");
        }
    }

    @Test
    public void evaluate_different_currencies() throws Exception {
        futureSell = new FutureSell(ticket, 10, new Money(new BigDecimal("10"), currencyFrom));
        Money evaluated = futureSell.evaluate(currencyTo, moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("-900"), currencyTo), evaluated);
    }

    @Test (expected = EvaluationException.class)
    public void evaluate_when_ratio_does_not_exist () throws Exception {
        futureSell = new FutureSell(ticket, 10, new Money(new BigDecimal("10"), currencyFrom));
        Money evaluated = futureSell.evaluate(currencyTo, new MoneyExchangeDoubleNoRatioAvailable(), stockExchangeDouble);

    }
    public class MoneyExchangeDoubleNoRatioAvailable implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            throw new RatioDoesNotExistException("No ratio available");
        }
    }

    @Test(expected = EvaluationException.class)
    public void evaluate_when_ticket_does_not_exist () throws EvaluationException {
        futureSell = new FutureSell(ticket, 10, new Money(new BigDecimal("10"), currencyFrom));
        Money evaluated = futureSell.evaluate(currencyTo, moneyExchangeDouble, new StockExchangeDoubleNoTicketAvaliable());
    }
    public class StockExchangeDoubleNoTicketAvaliable implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException, RatioDoesNotExistException {
            throw new RatioDoesNotExistException("No ratio available");
        }
    }

}