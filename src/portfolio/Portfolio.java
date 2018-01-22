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

    /**
     * Initializes a new HashSet
     */
    public Portfolio() {
        investmentsInPortfolio = new HashSet();
    }

    /**
     * @param investment adds the object which implements Investment interface to the hash set
     */
    public void addInvestment(Investment investment) {
        investmentsInPortfolio.add(investment);
    }

    /**
     * @param currencyTo    The currency of the money returned.
     * @param moneyExchange It's a external service.
     * @param stockExchange It's a external service.
     * @return 0 if the HashSet is empty, the result of the sum of evaluating every object of the Set by his own
     * evaluate method.
     * @throws EvaluationException In case something went wrong
     */
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
