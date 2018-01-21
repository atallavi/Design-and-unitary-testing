package portfolio;

import data.Currency;
import data.Money;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;

import java.math.BigDecimal;

public class Cash implements Investment {

    private Money money;

    public Cash(Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException {
        try {
            BigDecimal ratio = moneyExchange.exchangeRatio(this.money.getCurrency(), currencyTo);
            return this.money.change(ratio, currencyTo);
        }catch (Exception e){
            throw new EvaluationException("No ratio");
        }

    }
}
