package farrakhov.aydar.spendings.repository;

import farrakhov.aydar.spendings.content.CreditCard;
import rx.Observable;

/**
 * Created by aydar farrakhov on 14.12.16.
 */

public interface ICreditCardProvider {

    Observable<CreditCard> getAllCreditCards();

    CreditCard get(long id);

    void changeRest(Long id, Float rest);
}
