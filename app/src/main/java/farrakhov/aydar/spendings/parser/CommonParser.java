package farrakhov.aydar.spendings.parser;

import java.util.Date;

import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.content.CreditCardType;
import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.content.ShopBankName;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.content.SpendingTmpData;
import farrakhov.aydar.spendings.util.RealmUtil;
import io.realm.Realm;

/**
 * Created by aydar farrakhov on 09.10.16.
 */

public abstract class CommonParser implements IParser {

    private static final String UNKNOWN_NAME = "Другие";

    @Override
    public Spending parse(String text, Date date, long smsId) {
        if (!isSpending(text)){
            return null;
        }
        return createSpending(getSpendingData(text), smsId);
    }

    private Spending createSpending(SpendingTmpData spendingData, long smsId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Spending spending = new Spending();
        spending.setId(RealmUtil.getNextId(realm, Spending.class));
        spending.setCard(getOrCreateCreditCard(spendingData.getCardNumber(), spendingData.getType(),
                spendingData.getCredit()));
        spending.setDate(spendingData.getDate().toDate());
        spending.setSum(spendingData.getSum());
        spending.setShop(getOrCreateShop(spendingData.getShopName(), spendingData.getType()));
        spending.setSmsId(smsId);
        spending = realm.copyToRealm(spending);
        realm.commitTransaction();

        return spending;
    }

    private Shop getOrCreateShop(String shopName, CreditCardType type) {
        Realm realm = Realm.getDefaultInstance();
        ShopBankName shopBankName = realm.where(ShopBankName.class)
                .equalTo("smsName", shopName)
                .equalTo("type", type.toString())
                .findFirst();
        if (shopBankName != null){
            return shopBankName.getShop();
        }
        return createShop(shopName, type, realm);
    }

    private Shop createShop(String shopName, CreditCardType type, Realm realm) {
        Shop shop = new Shop();
        shop.setId(RealmUtil.getNextId(realm, Shop.class));
        shop.setDisplayName(shopName);
        shop.getShopBankNames().add(new ShopBankName(RealmUtil.getNextId(realm, ShopBankName.class), shopName, shop, type));
        shop.setCategory(getOrCreateUnknownCategory());
        shop = realm.copyToRealm(shop);
        return shop;
    }

    private Category getOrCreateUnknownCategory() {
        Realm realm = Realm.getDefaultInstance();
        Category category = realm.where(Category.class)
                .equalTo("name", UNKNOWN_NAME)
                .findFirst();
        if (category == null){
            category = createUnknownCategory(realm);
        }
        return category;
    }

    private Category createUnknownCategory(Realm realm) {
        Category category = new Category();
        category.setId(RealmUtil.getNextId(realm, Category.class));
        category.setName(UNKNOWN_NAME);
        category = realm.copyToRealm(category);
        return category;
    }

    private CreditCard getOrCreateCreditCard(String cardNumber, CreditCardType type, float credit) {
        Realm realm = Realm.getDefaultInstance();
        CreditCard creditCard = realm.where(CreditCard.class)
                .equalTo("number", cardNumber)
                .equalTo("type", type.toString())
                .findFirst();
        if (creditCard == null) {
            creditCard = createCreditCard(cardNumber, type, realm);
        }
        creditCard.setCredit(credit);
        return creditCard;
    }

    private CreditCard createCreditCard(String cardNumber, CreditCardType type, Realm realm) {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(RealmUtil.getNextId(realm, CreditCard.class));
        creditCard.setNumber(cardNumber);
        creditCard.setType(type.toString());
        creditCard = realm.copyToRealm(creditCard);
        return creditCard;
    }

    protected abstract SpendingTmpData getSpendingData(String text);

    public abstract boolean isSpending(String text);

}
