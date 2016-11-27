package farrakhov.aydar.spendings.content;

import java.util.Date;

/**
 * Created by aydar on 09.10.16.
 */

public class Sms {

    private CreditCardType type;

    private String text;

    private Date date;

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
