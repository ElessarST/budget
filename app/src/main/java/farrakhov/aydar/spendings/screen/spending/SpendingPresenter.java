package farrakhov.aydar.spendings.screen.spending;

import android.support.annotation.NonNull;

import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.repository.RepositoryProvider;

/**
 * Created by aydar farrakhov on 20.01.17.
 */

public class SpendingPresenter {

    private final SpendingView mView;

    public SpendingPresenter(@NonNull SpendingView view) {
        mView = view;
    }

    public void init(Long id) {
        Spending spending = RepositoryProvider.provideSpendingRepository()
                .get(id);
        mView.showSpending(spending);

    }

}
