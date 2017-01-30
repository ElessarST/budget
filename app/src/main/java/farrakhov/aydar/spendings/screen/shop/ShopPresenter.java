package farrakhov.aydar.spendings.screen.shop;

import android.support.annotation.NonNull;

import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.repository.RepositoryProvider;

/**
 * Created by aydar farrakhov on 29.01.17.
 */

public class ShopPresenter {

    private final ShopView mView;

    public ShopPresenter(@NonNull ShopView view) {
        mView = view;
    }

    public void init(Long id) {
        Shop shop = RepositoryProvider.providerShopRepository()
                .get(id);
        mView.showShop(shop);

    }

}
