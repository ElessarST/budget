package farrakhov.aydar.spendings.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import farrakhov.aydar.spendings.content.CreditCardType;
import farrakhov.aydar.spendings.content.Sms;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import rx.Observable;

/**
 * Created by aydar on 09.10.16.
 */

public class SMSReader {

    public static Observable<Sms> getNewSms(Context context) {
        Spending lastSpending = RepositoryProvider.provideSpendingRepository().getLastSpending();

        List<Sms> lstSms = new ArrayList<>();
        Sms objSms;
        Uri message = Uri.parse("content://sms/inbox");
        ContentResolver cr = context.getContentResolver();

        Cursor c = cr.query(message, null, null, null, null);
        int totalSMS = c != null ? c.getCount() : 0;

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                objSms = new Sms();
                String number = c.getString(c.getColumnIndexOrThrow("address"));
                objSms.setType(CreditCardType.getByNumber(number));
                objSms.setText(c.getString(c.getColumnIndexOrThrow("body")));
                objSms.setDate(new Date(c.getLong(c.getColumnIndexOrThrow("date"))));
                objSms.setId(c.getLong(c.getColumnIndexOrThrow("_id")));
                if (objSms.getType() != null) {
                    if (lastSpending == null ||
                            (lastSpending.getSmsId() < objSms.getId())) {
                        lstSms.add(objSms);
                    } else {
                        break;
                    }
                }
                c.moveToNext();
            }
        }
        c.close();

        return Observable.just(lstSms).flatMap(Observable::from);
    }
}
