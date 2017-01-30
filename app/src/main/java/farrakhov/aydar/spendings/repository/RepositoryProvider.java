package farrakhov.aydar.spendings.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import farrakhov.aydar.spendings.repository.impl.CategoryProvider;
import farrakhov.aydar.spendings.repository.impl.CreditCardProvider;
import farrakhov.aydar.spendings.repository.impl.ShopProvider;
import farrakhov.aydar.spendings.repository.impl.SpendingProvider;

/**
 * @author Aydar Farrakhov
 */
public final class RepositoryProvider {

    private static ISpendingProvider sSpendingRepository;
    private static ICreditCardProvider sCreditCardProvider;
    private static IShopProvider sShopProvider;
    private static ICategoryProvider sCategoryProvider;

    private RepositoryProvider() {
    }

    @NonNull
    public static ISpendingProvider provideSpendingRepository() {
        if (sSpendingRepository == null) {
            sSpendingRepository = new SpendingProvider();
        }
        return sSpendingRepository;
    }

    @NonNull
    public static ICategoryProvider provideCategoryRepository() {
        if (sCategoryProvider == null) {
            sCategoryProvider = new CategoryProvider();
        }
        return sCategoryProvider;
    }

    @NonNull
    public static ICreditCardProvider provideCreditCardProvider() {
        if (sCreditCardProvider == null) {
            sCreditCardProvider = new CreditCardProvider();
        }
        return sCreditCardProvider;
    }

    @NonNull
    public static IShopProvider provideShopRepository() {
        if (sShopProvider == null) {
            sShopProvider = new ShopProvider();
        }
        return sShopProvider;
    }

    @MainThread
    public static void init() {
        sSpendingRepository = new SpendingProvider();
        sCreditCardProvider = new CreditCardProvider();
        sShopProvider = new ShopProvider();
        sCategoryProvider = new CategoryProvider();
    }
}
