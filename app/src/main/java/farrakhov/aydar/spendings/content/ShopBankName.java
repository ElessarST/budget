package farrakhov.aydar.spendings.content;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by aydar on 09.10.16.
 */

public class ShopBankName extends RealmObject {

    @PrimaryKey
    private Long id;

    private String type;

    private Shop shop;

    private String smsName;

    public ShopBankName() {

    }

    public ShopBankName(Long id, String smsName, Shop shop, CreditCardType type) {
        this.id = id;
        this.smsName = smsName;
        this.shop = shop;
        this.type = type.toString();
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getSmsName() {
        return smsName;
    }

    public void setSmsName(String smsName) {
        this.smsName = smsName;
    }
}
