package Test;

import data.Currency;
import data.Money;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MoneyTest {

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void add_money_with_different_currency_throws_exception () throws IllegalArgumentException{
        Money euros = new Money(new BigDecimal("3.00"), new Currency("euros"));
        Money dollars = new Money(new BigDecimal("3.00"), new Currency("dollars"));
        Money result = euros.add(dollars);
    }

    @org.junit.Test
    public void  add_money_gets_correct_result () {
        Currency currency = new Currency("money");
        Money moneyOne = new Money(new BigDecimal("3.00"), currency);
        Money moneyTwo = new Money(new BigDecimal("4.00"), currency);
        Money result = moneyOne.add(moneyTwo);
        Money expected = new Money(new BigDecimal("7.00"), currency);
        assertTrue(expected.equals(result));
    }

    @org.junit.Test
    public void toString_returns_correct_string () {
        Money money = new Money(new BigDecimal("3.00"), new Currency("money"));
        String expected = money.toString();
        assertTrue(expected.equals("Money{" +
                                    "quantity=" + money.getQuantity().toString() +
                                    ", currency=" + money.getCurrency().toString() +
                                    '}'));
    }

}