package farrakhov.aydar.spendings.content;

import org.joda.time.DateTime;

/**
 * Created by aydar farrakhov on 10.10.16.
 */

public class SpendingTmpData {

    private String shopName;

    private CreditCardType type;

    private float sum;

    private DateTime date;

    private float credit;

    private String cardNumber;

    public SpendingTmpData() {
    }

    public String getShopName() {
        return shopName;
    }

    public SpendingTmpData setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public CreditCardType getType() {
        return type;
    }

    public SpendingTmpData setType(CreditCardType type) {
        this.type = type;
        return this;
    }

    public float getSum() {
        return sum;
    }

    public SpendingTmpData setSum(float sum) {
        this.sum = sum;
        return this;
    }

    public DateTime getDate() {
        return date;
    }

    public SpendingTmpData setDate(DateTime date) {
        this.date = date;
        return this;
    }

    public float getCredit() {
        return credit;
    }

    public SpendingTmpData setCredit(float credit) {
        this.credit = credit;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public SpendingTmpData setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }
}
