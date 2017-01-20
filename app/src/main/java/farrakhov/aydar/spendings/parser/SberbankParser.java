package farrakhov.aydar.spendings.parser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import farrakhov.aydar.spendings.content.CreditCardType;
import farrakhov.aydar.spendings.content.SpendingTmpData;

/**
 * Created by aydar on 09.10.16.
 */

public class SberbankParser extends CommonParser {

    private static DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yy HH:mm");
    private static final String POKUPKA = "покупка";
    private static final String OPLATA = "оплата";
    private static final String RUB_PARAM = "р";
    private static final String DOLLAR_PARAM = "USD";
    private static final String BALANS = "Баланс:";

    @Override
    protected SpendingTmpData getSpendingData(String text) {
        String[] splitted = text.split(" ");
        int shift = 0;
        if (text.contains(OPLATA)){
            shift = 1;
        }
        return new SpendingTmpData()
                .setCardNumber(splitted[0])
                .setDate(parseDate(splitted[1] + " " + splitted[2]))
                .setType(CreditCardType.SBERBANK)
                .setSum(getSum(splitted[4 + shift]))
                .setShopName(getShopName(splitted, shift))
                .setCredit(getCredit(splitted, shift));
    }

    private float getCredit(String[] splitted, int shift) {
        int index = 6 + shift;
        while (!splitted[index].contains(BALANS)) index++;
        return Float.parseFloat(splitted[index + 1].replaceAll(RUB_PARAM, "")
                .trim());
    }

    private String getShopName(String[] splitted, int shift) {
        StringBuilder builder = new StringBuilder();
        int index = 5 + shift;
        do {
            builder.append(splitted[index++]);
        } while (!splitted[index].contains(BALANS));
        return builder.toString();
    }

    private float getSum(String s) {
        return Float.parseFloat(s.replaceAll(RUB_PARAM, "")
                .replaceAll(DOLLAR_PARAM, "")
                .trim());
    }

    @Override
    public boolean isSpending(String text) {
        String[] words = text.split(" ");
        return words.length > 2 && words[3].contains(POKUPKA);
    }

    private DateTime parseDate(String date) {
        return fmt.parseDateTime(date.trim());
    }
}
