package farrakhov.aydar.spendings.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * @author Aydar Farrakhov
 */
public final class RepositoryProvider {

    private static ISpendingProvider sSpendingRepository;

    private static ICreditCardProvider sCreditCardProvider;

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
    public static ICreditCardProvider provideCreditCardProvider() {
        if (sCreditCardProvider == null) {
            sCreditCardProvider = new CreditCardProvider();
        }
        return sCreditCardProvider;
    }

    public static void setSpendingRepository(ISpendingProvider spendingRepository) {
        sSpendingRepository = spendingRepository;
    }

    @MainThread
    public static void init() {
        sSpendingRepository = new SpendingProvider();
        sCreditCardProvider = new CreditCardProvider();
    }
}
