package data;

import java.math.BigDecimal;


public class Money {

    private BigDecimal quantity;
    private Currency currency;

    public Money(BigDecimal quantity, Currency currency) {
        this.quantity = quantity;
        this.quantity.setScale(2, BigDecimal.ROUND_UP);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Money add (Money other) throws IllegalArgumentException {
        if (this.currency != other.currency){
            throw new IllegalArgumentException();
        }
        else{
            BigDecimal bg1 = this.quantity;
            BigDecimal bg2 = other.quantity;
            BigDecimal result = bg1.add(bg2);
            result.setScale(2, BigDecimal.ROUND_UP);
            return new Money(result, this.currency);
        }
    }
    public Money subtract (Money other) {
        if (this.currency != other.currency){
            throw new IllegalArgumentException();
        }
        else{
            BigDecimal bd1 = this.quantity;
            BigDecimal bd2 = other.quantity;
            BigDecimal result = bd1.subtract(bd2);
            result.setScale(2, BigDecimal.ROUND_UP);
            return new Money(result, this.currency);
        }
    }


    @Override
    public int hashCode() {
        int result = quantity.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
                "quantity=" + quantity.toString() +
                ", currency=" + currency.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!quantity.equals(money.quantity)) return false;
        return currency.equals(money.currency);
    }
}
