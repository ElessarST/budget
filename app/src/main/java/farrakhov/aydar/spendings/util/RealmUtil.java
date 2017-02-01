package farrakhov.aydar.spendings.util;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class RealmUtil {

    public static Long getNextId(Realm realm, Class c){
        Number number = realm.where(c).max("id");
        if (number == null) {
            return 1L;
        }
        return (long) (number) + 1;
    }

    public static <T extends RealmModel> void delete(Long id, Realm realm, Class<T> c) {
        RealmResults<T> result = realm.where(c).equalTo("id", id).findAll();
        result.deleteAllFromRealm();
    }

}
