package farrakhov.aydar.spendings.repository.impl;

import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.repository.IShopProvider;
import io.realm.Realm;

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
}
