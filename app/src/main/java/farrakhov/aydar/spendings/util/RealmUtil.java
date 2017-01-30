package farrakhov.aydar.spendings.util;

import io.realm.Realm;

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

}
