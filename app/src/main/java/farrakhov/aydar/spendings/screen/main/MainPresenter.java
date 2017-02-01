package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.content.helper.CategoryWithDetails;
import farrakhov.aydar.spendings.content.helper.Period;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.util.SMSReader;

/**
 * Created by aydar on 09.10.16.
 */

public class MainPresenter {

    private final MainView mView;
    private final Period mPeriod;

    public MainPresenter(@NonNull MainView view, Period period) {
        mView = view;
        mPeriod = period;
    }

    public void init(Context context) {
        mView.requestSmsReadPermission();

        RepositoryProvider.provideSpendingRepository()
                .saveSpending(SMSReader.getNewSms(context));
        showCreditCards();
        showSpendingsAndCategories();
    }

    private void showSpendingsAndCategories() {
        Map<Long, CategoryWithDetails> categories = new HashMap<>();
        for (CategoryWithDetails cat : getCategories()) {
            cat.setPeriod(mPeriod);
            categories.put(cat.getId(), cat);
        }
        List<Spending> spendings = getSpendings();
        for (Spending spending : spendings) {
            Long categoryId = spending.getShop().getCategory().getId();
            CategoryWithDetails categoryWithDetails = categories.get(categoryId);
            categoryWithDetails.addSpending(spending.getSum());
        }
        mView.showCategories(new ArrayList<>(categories.values()));
        mView.showSpendings(spendings);
    }

    private List<CategoryWithDetails> getCategories() {
        List<CategoryWithDetails> categories = new ArrayList<>();
        RepositoryProvider.provideCategoryRepository()
                .getAll()
                .map(CategoryWithDetails::new)
                .subscribe(categories::add);
        return categories;
    }

    private void showCreditCards() {
        List<CreditCard> creditCards = new ArrayList<>();
        RepositoryProvider.provideCreditCardProvider()
                .getAllCreditCards()
                .subscribe(creditCards::add);
        mView.showCreditCards(creditCards);
    }

    private List<Spending> getSpendings() {
        List<Spending> spendingList = new ArrayList<>();
        RepositoryProvider.provideSpendingRepository().getSpendings(mPeriod)
                .subscribe(spendingList::add);
        return spendingList;
    }


    public void updateAll(Context context) {
        RepositoryProvider.provideSpendingRepository()
                .deleteAllSpendings();

        init(context);

    }

    public void addCategory(String name) {
        RepositoryProvider.provideCategoryRepository().create(name);
        showSpendingsAndCategories();
    }

    public void changeRest(CreditCard creditCard, Float rest) {
        RepositoryProvider.provideCreditCardProvider()
                .changeRest(creditCard.getId(), rest);
        showCreditCards();
    }
}
