package farrakhov.aydar.spendings.screen.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Shop;

public class ShopActivity extends AppCompatActivity implements ShopView {

    public static final String SHOP_ID_ATTR = "shop_id";

    private ShopPresenter mShopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mShopPresenter = new ShopPresenter(this);
        mShopPresenter.init(getIntent().getLongExtra(SHOP_ID_ATTR, 0));


    }

    @Override
    public void showShop(Shop shop) {
        this.setTitle(shop.getDisplayName());
    }
}
