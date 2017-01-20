package farrakhov.aydar.spendings.parser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import farrakhov.aydar.spendings.content.CreditCardType;
import farrakhov.aydar.spendings.content.SpendingTmpData;

/**
 * Created by aydar farrakhov on 09.10.16.
 */

public class AlfaBankParser extends CommonParser {

    private static final String BUY_PARAM = "Pokupka";
    private static final String SUM_PARAM = "Summa:";
    private static final String RUB_PARAM = "RUR";
    private static final String REST_PARAM = "Ostatok:";
    private static final String COMMA = ",";
    private static final String POINTER = ".";

    private static DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");


    @Override
    protected SpendingTmpData getSpendingData(String text) {
        String[] splitted = text.split(";");

        return new SpendingTmpData()
                .setCardNumber(splitted[0])
                .setType(CreditCardType.ALPHA_BANK)
                .setSum(getSum(splitted[3]))
                .setCredit(getCredit(splitted[4]))
                .setShopName(splitted[5].trim())
                .setDate(parseDate(splitted[6]));
    }

    private DateTime parseDate(String date) {
        return fmt.parseDateTime(date.trim());
    }

    private float getCredit(String credit) {
        return Float.parseFloat(credit.replaceAll(REST_PARAM, "")
                .replaceAll(RUB_PARAM, "")
                .replaceAll(COMMA, POINTER)
                .trim());
    }

    private float getSum(String sum) {
        return Float.parseFloat(sum.replaceAll(SUM_PARAM, "")
                .replaceAll(RUB_PARAM, "")
                .replaceAll(COMMA, POINTER)
                .trim());
    }

    @Override
    public boolean isSpending(String text) {
        String[] splitted = text.split(";");
        if (splitted.length < 7 || !splitted[1].contains(BUY_PARAM)){
            return false;
        }
        return true;
    }

}
