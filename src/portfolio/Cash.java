package portfolio;

import data.Currency;
import data.Money;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;

import java.math.BigDecimal;

public class Cash implements Investment {

    private Money money;

    /**
     * @param money Initializes in the constructor
     */
    public Cash(Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    /**
     * @param currencyTo    The currency of the money returned.
     * @param moneyExchange It's a external service.
     * @param stockExchange It's a external service.
     * @return A new money evaluated.
     * @throws EvaluationException In case something went wrong
     */
    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange)
            throws EvaluationException {
        try {
            BigDecimal ratio = moneyExchange.exchangeRatio(this.money.getCurrency(), currencyTo);
            return this.money.getCurrency().equals(currencyTo) ? this.money : this.money.change(ratio, currencyTo);
        }catch (Exception e){
            throw new EvaluationException("No ratio");
        }

    }
}
