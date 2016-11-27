package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

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

//        RepositoryProvider.provideSpendingRepository()
//                .getAllSpending();

        RepositoryProvider.provideSpendingRepository()
                .saveSpending(SMSReader.getNewSms(context));

        showSms();
    }

    private void showSms() {
        List<Spending> spendingList = new ArrayList<>();
        RepositoryProvider.provideSpendingRepository().getAllSpending()
                .subscribe(spendingList::add);
        mView.showSpendings(spendingList);
    }


}
