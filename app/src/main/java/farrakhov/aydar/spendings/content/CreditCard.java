package farrakhov.aydar.spendings.content;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by aydar on 09.10.16.
 */

public class CreditCard extends RealmObject {

    @PrimaryKey
    private Long id;

    private String type;

    private String number;

    private float credit;

    public CreditCard() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
}
