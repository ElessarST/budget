package farrakhov.aydar.spendings.content;

/**
 * Created by aydar on 09.10.16.
 */

public enum CreditCardType {
    ALPHA_BANK("Alfa-Bank"),
    SBERBANK("900");

    String number;

    CreditCardType(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public static CreditCardType getByNumber(String number) {
        for (CreditCardType type : values()) {
            if (type.getNumber().equals(number)) {
                return type;
            }
        }
        return null;
    }
}
