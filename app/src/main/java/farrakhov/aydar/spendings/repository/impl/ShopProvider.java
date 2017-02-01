package farrakhov.aydar.spendings.repository.impl;

import java.util.List;

import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.content.ShopBankName;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.repository.IShopProvider;
import farrakhov.aydar.spendings.util.RealmUtil;
import io.realm.Realm;
import rx.Observable;

/**
 * Created by aydar farrakhov on 29.01.17.
 */

public class ShopProvider implements IShopProvider {

    @Override
    public Shop get(Long id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Shop.class)
                .equalTo("id", id)
                .findFirst();
    }

    @Override
    public void change(Long id, String name) {
        Shop shop = get(id);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        shop.setDisplayName(name);
        realm.commitTransaction();
    }

    @Override
    public Observable<Shop> getAll() {
        Realm realm = Realm.getDefaultInstance();
        List<Shop> shops = realm.where(Shop.class)
                .findAll();
        return Observable.just(shops)
                .flatMap(Observable::from);
    }

    @Override
    public void union(Shop mainShop, Shop unionShop) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<ShopBankName> bankNames = unionShop.getShopBankNames();
        for (ShopBankName bankName : bankNames) {
            bankName.setShop(mainShop);
        }
        mainShop.getShopBankNames().addAll(bankNames);
        List<Spending> spendings = unionShop.getSpending();
        for (Spending spending : spendings) {
            spending.setShop(mainShop);
        }
        mainShop.getSpending().addAll(spendings);
        RealmUtil.delete(unionShop.getId(), realm, Shop.class);
        realm.commitTransaction();
    }

    @Override
    public void change(Long id, Category category) {
        Shop shop = get(id);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        shop.setCategory(category);
        realm.commitTransaction();
    }
}
