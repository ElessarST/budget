package farrakhov.aydar.spendings.content;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by aydar on 09.10.16.
 */

public class Shop extends RealmObject {

    @PrimaryKey
    private Long id;

    private String displayName;

    private Category category;

    private RealmList<ShopBankName> shopBankNames = new RealmList<>();

    private RealmList<Spending> spending;

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ShopBankName> getShopBankNames() {
        return shopBankNames;
    }

    public void setShopBankNames(RealmList<ShopBankName> shopBankNames) {
        this.shopBankNames = shopBankNames;
    }

    public List<Spending> getSpending() {
        return spending;
    }

    public void setSpending(RealmList<Spending> spending) {
        this.spending = spending;
    }
}
