package farrakhov.aydar.spendings.screen.main;

import java.util.List;

import farrakhov.aydar.spendings.content.Spending;

/**
 * Created by aydar on 09.10.16.
 */

public interface MainView {

    void requestSmsReadPermission();

    void showSpendings(List<Spending> spendingList);
}
