package farrakhov.aydar.spendings.repository.impl;

import java.util.List;

import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.repository.ICategoryProvider;
import farrakhov.aydar.spendings.util.RealmUtil;
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

    @Override
    public void create(String name) {
        Realm realm = Realm.getDefaultInstance();
        Category category = realm.where(Category.class)
                .equalTo("name", name)
                .findFirst();
        if (category != null) return;
        realm.beginTransaction();
        Category newCategory = new Category();
        newCategory.setId(RealmUtil.getNextId(realm, Category.class));
        newCategory.setName(name);
        realm.copyToRealm(newCategory);
        realm.commitTransaction();
    }

    @Override
    public Category get(Long id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Category.class)
                .equalTo("id", id)
                .findFirst();
    }

    @Override
    public void change(Long id, String name, float cost, boolean monthly) {
        Realm realm = Realm.getDefaultInstance();
        Category category = get(id);
        realm.beginTransaction();
        category.setSum(cost);
        category.setMonthly(monthly);
        category.setName(name);
        realm.copyToRealm(category);
        realm.commitTransaction();
    }

    @Override
    public void delete(Long id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmUtil.delete(id, realm, Category.class);
        realm.commitTransaction();
    }
}
