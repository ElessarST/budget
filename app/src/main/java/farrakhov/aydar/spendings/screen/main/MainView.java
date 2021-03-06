package farrakhov.aydar.spendings.screen.main;

import java.util.List;

import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.content.helper.CategoryWithDetails;

/**
 * Created by aydar on 09.10.16.
 */

public interface MainView {

    void requestSmsReadPermission();

    void showSpendings(List<Spending> spendingList);

    void showCreditCards(List<CreditCard> creditCards);

    void showCategories(List<CategoryWithDetails> categories);
}
