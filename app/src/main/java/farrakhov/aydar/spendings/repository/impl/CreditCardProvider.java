package farrakhov.aydar.spendings.repository.impl;

import java.util.List;

import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.repository.ICreditCardProvider;
import io.realm.Realm;
import rx.Observable;

/**
 * Created by aydar farrakhov on 14.12.16.
 */

public class CreditCardProvider implements ICreditCardProvider {


    @Override
    public Observable<CreditCard> getAllCreditCards() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<CreditCard> creditCards = realm.where(CreditCard.class).findAll();
        realm.commitTransaction();
        return  Observable.just(creditCards)
                .flatMap(Observable::from);
    }
}
