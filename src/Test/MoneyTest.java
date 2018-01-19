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
        Money moneyTwo = new Money(new BigDecimal("-4.00"), currency);
        Money result = moneyOne.add(moneyTwo);
        Money expected = new Money(new BigDecimal("-1.00"), currency);
        assertTrue(expected.equals(result));
    }

    //Fails
    @org.junit.Test
    public void  add_money_gets_correct_result_rounded () {
        Currency currency = new Currency("money");
        Money moneyOne = new Money(new BigDecimal("3.45"), currency);
        Money moneyTwo = new Money(new BigDecimal("4.006"), currency);
        Money result = moneyOne.add(moneyTwo);
        Money expected = new Money(new BigDecimal("7.46"), currency);
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

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void subtract_money_with_different_currency_throws_exception () throws IllegalArgumentException {
        Money euros = new Money(new BigDecimal("3.00"), new Currency("euros"));
        Money dollars = new Money(new BigDecimal("3.00"), new Currency("dollars"));
        Money result = euros.subtract(dollars);
    }

    @org.junit.Test
    public void subtract_money_gets_correct_result () {
        Currency currency= new Currency("currency");
        Money moneyOne = new Money(new BigDecimal("3.00"), currency);
        Money moneyTwo = new Money(new BigDecimal("10.07"), currency);
        Money result = moneyOne.subtract(moneyTwo);
        assertEquals(new Money(new BigDecimal("-7.07"), currency), result);
    }

    @org.junit.Test
    public void multiply_gets_correct_result () {
        Currency currency = new Currency("money");
        Money money = new Money(new BigDecimal("3.00"), currency);
        Money result = money.multiply(3);
        Money expected = new Money(new BigDecimal("9.00"), currency);
        assertTrue(expected.equals(money));
    }

}