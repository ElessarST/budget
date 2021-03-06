package farrakhov.aydar.spendings.repository;


import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.content.Shop;
import rx.Observable;

/**
 * Created by aydar farrakhov on 29.01.17.
 */

public interface IShopProvider {

    Shop get(Long id);

    void change(Long id, String name);

    Observable<Shop> getAll();

    /**
     *
     * @param mainShop основной магазин
     * @param unionShops присоединяемый магазин
     */
    void union(Shop mainShop, Shop unionShops);

    void change(Long id, Category category);
}
