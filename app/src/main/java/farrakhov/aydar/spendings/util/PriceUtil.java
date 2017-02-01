package farrakhov.aydar.spendings.util;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by aydar farrakhov on 01.02.17.
 */

public class PriceUtil {

    public static final String RUBLE = Currency.getInstance(Locale.getDefault()).getSymbol();

    public static String format(Float price) {
        return String.format(Locale.getDefault(), "%.2f %s", price,
                RUBLE);

    }
}
