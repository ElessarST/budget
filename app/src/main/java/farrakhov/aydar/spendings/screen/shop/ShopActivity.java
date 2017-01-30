package farrakhov.aydar.spendings.screen.shop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.screen.shop.dialog.EditShopDialog;

public class ShopActivity extends AppCompatActivity implements ShopView, EditShopDialog.EditShopDialogListener {

    public static final String SHOP_ID_ATTR = "shop_id";

    private ShopPresenter mShopPresenter;

    @BindView(R.id.category_value)
    public TextView mCategory;
    @BindView(R.id.shop_name_value)
    public TextView mShop;

    @BindView(R.id.fab)
    public FloatingActionButton editButton;

    private Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mShopPresenter = new ShopPresenter(this);
        mShopPresenter.init(getIntent().getLongExtra(SHOP_ID_ATTR, 0));

        editButton.setOnClickListener(l -> {
            Bundle bundle = new Bundle();
            bundle.putLong(SHOP_ID_ATTR, shop.getId());
            EditShopDialog dialog = new EditShopDialog();
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "dialog");
        });


    }

    @Override
    public void showShop(Shop shop) {
        this.setTitle(shop.getDisplayName());
        mShop.setText(shop.getDisplayName());
        mCategory.setText(shop.getCategory().getName());
        this.shop = shop;
    }

    @Override
    public void change(String name, Shop shop, Category category, boolean saveName) {
        if (!this.shop.getDisplayName().equals(name)) {
            RepositoryProvider.provideShopRepository().change(this.shop.getId(), name);
        }
        if (shop != null && !shop.getId().equals(this.shop.getId())) {
            if (saveName) {
                RepositoryProvider.provideShopRepository().union(this.shop, shop);
            } else {
                RepositoryProvider.provideShopRepository().union(shop, this.shop);
            }
        }

        if (category != null && !this.shop.getCategory().getId().equals(category.getId())) {
            RepositoryProvider.provideShopRepository().change(this.shop.getId(), category);
        }


        mShopPresenter.init(this.shop.getId());
    }
}
