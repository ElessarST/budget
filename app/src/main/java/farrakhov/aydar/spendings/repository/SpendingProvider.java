package farrakhov.aydar.spendings.repository;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Sms;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.parser.ParserProvider;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by aydar farrakhov on 09.10.16.
 */

public class SpendingProvider implements ISpendingProvider {

    @Override
    public Observable<Spending> saveSpending(Observable<Sms> messages) {
        List<Spending> spending = new ArrayList<>();
        messages.sorted((m1, m2) -> m2.getDate().compareTo(m1.getDate()))
                .forEach(sms -> spending.add(ParserProvider.getParser(sms.getType())
                .parse(sms.getText(), sms.getDate())));
        return Observable.just(spending)
                .flatMap(Observable::from);
    }

    @Override
    public Observable<Spending> getAllSpending() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Spending> spending = realm.where(Spending.class).findAllSorted("date", Sort.DESCENDING);
        realm.commitTransaction();
        return  Observable.just(spending)
                .flatMap(Observable::from);
    }

    @Override
    public Spending getLastSpending() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Spending> spendings = realm.where(Spending.class)
                .findAllSorted("date", Sort.DESCENDING);
        return spendings.isEmpty() ? null : spendings.first();
    }

}
