package farrakhov.aydar.spendings.repository.impl;

import java.util.List;

import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.repository.ICategoryProvider;
import io.realm.Realm;
import rx.Observable;

/**
 * Created by aydar farrakhov on 14.12.16.
 */

public class CategoryProvider implements ICategoryProvider {


    @Override
    public Observable<Category> getAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Category> creditCards = realm.where(Category.class).findAll();
        realm.commitTransaction();
        return  Observable.just(creditCards)
                .flatMap(Observable::from);
    }
}
