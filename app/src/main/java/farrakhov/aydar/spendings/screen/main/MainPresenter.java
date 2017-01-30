package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.util.SMSReader;

/**
 * Created by aydar on 09.10.16.
 */

public class MainPresenter {

    private final MainView mView;

    public MainPresenter(@NonNull MainView view) {
        mView = view;
    }

    public void init(Context context) {
        mView.requestSmsReadPermission();

        RepositoryProvider.provideSpendingRepository()
                .saveSpending(SMSReader.getNewSms(context));
        showCreditCards();
        showSpendings();
        showCategories();
    }

    private void showCategories() {
        List<Category> categories = new ArrayList<>();
        RepositoryProvider.provideCategoryRepository()
                .getAll()
                .subscribe(categories::add);
        mView.showCategories(categories);
    }

    private void showCreditCards() {
        List<CreditCard> creditCards = new ArrayList<>();
        RepositoryProvider.provideCreditCardProvider()
                .getAllCreditCards()
                .subscribe(creditCards::add);
        mView.showCreditCards(creditCards);
    }

    private void showSpendings() {
        List<Spending> spendingList = new ArrayList<>();
        RepositoryProvider.provideSpendingRepository().getSpendings(1)
                .subscribe(spendingList::add);
        mView.showSpendings(spendingList);
    }


    public void updateAll(Context context) {
        RepositoryProvider.provideSpendingRepository()
                .deleteAllSpendings();

        init(context);

    }
}
