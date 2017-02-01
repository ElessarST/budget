package farrakhov.aydar.spendings.repository.impl;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Sms;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.content.helper.Period;
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
    public Observable<Spending> getSpendings(Period period) {


        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Spending> spending = realm.where(Spending.class)
                .findAllSorted("date", Sort.DESCENDING);
        realm.commitTransaction();
        return  Observable.just(spending)
                .flatMap(Observable::from)
                .filter(s -> isInPeriod(s, period));
    }

    private Boolean isInPeriod(Spending s, Period period) {
        DateTime now = DateTime.now();
        DateTime spending = new DateTime(s.getDate());
        switch (period) {
            case MONTH:
                return spending.getMillis() >= now.dayOfMonth().withMinimumValue().getMillis();
            case WEEK:
                return spending.getMillis() >= now.dayOfWeek().withMinimumValue().getMillis();
            case DAY:
                return spending.getMillis() >= now.withTimeAtStartOfDay().getMillis();
        }
        return true;
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
