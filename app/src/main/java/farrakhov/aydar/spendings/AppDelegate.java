package farrakhov.aydar.spendings;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import farrakhov.aydar.spendings.repository.RepositoryProvider;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by aydar on 09.10.16.
 */

public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupRealm();

        JodaTimeAndroid.init(this);

        RepositoryProvider.init();
    }

    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
