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
    Portfolio wallet;

    @Before
    public void setUp(){
        anyCurrency = new Currency("anyCurrency");
        money = new Money(new BigDecimal("100"), anyCurrency);
        ticket = new Ticket("anyTicket");
        moneyExchangeDouble = new MoneyExchangeDouble();
        stockExchangeDouble = new StockExchangeDouble();
        wallet = new Portfolio();
    }
    @Test
    public void evaluate_empty_wallet () throws EvaluationException {
        Money emptyWallet = wallet.evaluate(anyCurrency, moneyExchangeDouble, stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("0.00"),anyCurrency), emptyWallet);
    }

    @Test
    public void evaluate_with_different_Investments() throws Exception {
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

    @Test
    public void evaluate_with_same_investment_evaluates_to_other_currency () throws EvaluationException {
        for(int i=0; i < 10; ++i ){
            wallet.addInvestment(new Cash(money));

        }
        Money cashWallet = wallet.evaluate(new Currency("anyOtherCurrency"), new MoneyExchangeDoubleReturnsHalf(),
                stockExchangeDouble);
        assertEquals(new Money(new BigDecimal("500"),new Currency("anyOtherCurrency") ), cashWallet);
    }
    public class MoneyExchangeDoubleReturnsHalf implements MoneyExchange{
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException, EvaluationException {
            return new BigDecimal("0.50");
        }
    }





}