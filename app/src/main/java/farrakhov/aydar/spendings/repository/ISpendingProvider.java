package farrakhov.aydar.spendings.repository;

import farrakhov.aydar.spendings.content.Sms;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.content.helper.Period;
import rx.Observable;

/**
 * Created by aydar farrakhov on 09.10.16.
 */

public interface ISpendingProvider {

    Observable<Spending> saveSpending(Observable<Sms> messages);

    Observable<Spending> getSpendings(Period period);

    Spending get(Long id);

    Spending getLastSpending();

    void deleteAllSpendings();

    void change(Long id, Float sum);
}
