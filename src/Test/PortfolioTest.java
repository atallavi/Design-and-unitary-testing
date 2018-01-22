package Test;

import data.Currency;
import data.Money;
import data.Ticket;
import org.junit.Before;
import org.junit.Test;
import portfolio.*;
import services.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class PortfolioTest {

    Currency anyCurrency;
    Money money;
    Ticket ticket;
    MoneyExchangeDouble moneyExchangeDouble;
    StockExchangeDouble stockExchangeDouble;

    @Before
    public void setUp(){
        anyCurrency = new Currency("anyCurrency");
        money = new Money(new BigDecimal("100"), anyCurrency);
        ticket = new Ticket("anyTicket");
        moneyExchangeDouble = new MoneyExchangeDouble();
        stockExchangeDouble = new StockExchangeDouble();
    }
    @Test
    public void evaluate() throws Exception {
        Portfolio wallet = new Portfolio();
        wallet.addInvestment(new FutureSell(ticket, 2,new Money(new BigDecimal("200"), anyCurrency)));
        wallet.addInvestment(new Cash(money));
        wallet.addInvestment(new Stock(ticket, 10));
        Money cashWallet = wallet.evaluate(anyCurrency, moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("1300.00"), anyCurrency), cashWallet);

    }
    public class MoneyExchangeDouble implements MoneyExchange{
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            return new BigDecimal("1.00");
        }
    }
    public class StockExchangeDouble implements StockExchange {

        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            return money;
        }
    }


}