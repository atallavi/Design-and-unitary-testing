package portfolio;

import data.Currency;
import data.Money;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;

public class Portfolio implements Investment {

    private HashSet<Investment> investmentsInPortfolio;

    public Portfolio() {
        investmentsInPortfolio = new HashSet();
    }
    public void addInvestment(Investment investment) {
        investmentsInPortfolio.add(investment);

    }



    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyExchange, StockExchange stockExchange) throws EvaluationException{
      try {
          if (investmentsInPortfolio.isEmpty()) {
              return new Money(new BigDecimal("0.00"), currencyTo);
          }

          Money total = new Money(new BigDecimal("0.00"), currencyTo);

          Iterator<Investment> iterator = investmentsInPortfolio.iterator();
          while(iterator.hasNext()){
              total = total.add(iterator.next().evaluate(currencyTo, moneyExchange, stockExchange));
          }
          return total;
      }catch (Exception e) {
          throw new EvaluationException("Something gone wrong.");
      }
    }
}
