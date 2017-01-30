package farrakhov.aydar.spendings.repository;

import farrakhov.aydar.spendings.content.Shop;

/**
 * Created by aydar farrakhov on 29.01.17.
 */

public interface IShopProvider {

    Shop get(Long id);
}
