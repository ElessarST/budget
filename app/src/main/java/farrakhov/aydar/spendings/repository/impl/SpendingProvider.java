package farrakhov.aydar.spendings.repository.impl;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Sms;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.parser.ParserProvider;
import farrakhov.aydar.spendings.repository.ISpendingProvider;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by aydar farrakhov on 09.10.16.
 */

public class SpendingProvider implements ISpendingProvider {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public Observable<Spending> saveSpending(Observable<Sms> messages) {
        List<Spending> spending = new ArrayList<>();
        messages.sorted((m1, m2) -> m2.getDate().compareTo(m1.getDate()))
                .forEach(sms -> spending.add(ParserProvider.getParser(sms.getType())
                .parse(sms.getText(), sms.getDate(), sms.getId())));
        return Observable.just(spending)
                .flatMap(Observable::from);
    }

    @Override
    public Observable<Spending> getSpendings(int page) {

        int offset = (page - 1) * DEFAULT_PAGE_SIZE;
        int limit = DEFAULT_PAGE_SIZE;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Spending> spending = realm.where(Spending.class)
                .findAllSorted("date", Sort.DESCENDING);
        realm.commitTransaction();
        if (spending.size() > limit) {
            spending = spending.subList(offset, limit);
        }
        return  Observable.just(spending)
                .flatMap(Observable::from);
    }

    @Override
    public Spending get(Long id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Spending.class)
                .equalTo("id", id)
                .findFirst();
    }

    @Override
    public Spending getLastSpending() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Spending> spendings = realm.where(Spending.class)
                .findAllSorted("smsId", Sort.DESCENDING);
        return spendings.isEmpty() ? null : spendings.first();
    }

    @Override
    public void deleteAllSpendings() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(Spending.class);
        realm.commitTransaction();
    }



}
