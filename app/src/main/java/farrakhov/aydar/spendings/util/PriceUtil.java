package farrakhov.aydar.spendings.util;

import android.content.Context;
import android.widget.TextView;

import java.util.Currency;
import java.util.Locale;

import farrakhov.aydar.spendings.R;

/**
 * Created by aydar farrakhov on 01.02.17.
 */

public class PriceUtil {

    public static final String RUBLE = Currency.getInstance(Locale.getDefault()).getSymbol();

    public static String format(Float price) {
        return String.format(Locale.getDefault(), "%.2f %s", price,
                RUBLE);
    }

    public static void colorize(TextView view, float cost, Context context) {
        int color = cost < 0 ? R.color.colorDanger : R.color.colorSuccess;
        view.setTextColor(context.getResources().getColor(color));
    }
}
