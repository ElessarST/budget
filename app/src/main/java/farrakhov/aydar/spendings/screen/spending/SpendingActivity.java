package farrakhov.aydar.spendings.screen.spending;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.screen.shop.ShopActivity;

public class SpendingActivity extends AppCompatActivity implements SpendingView {

    public static final String SPENDING_ID_ATTR = "spending_id";

    private SpendingPresenter mSpendingPresenter;

    @BindView(R.id.spendings_value)
    public TextView mSpendings;
    @BindView(R.id.shop_value)
    public TextView mShop;
    @BindView(R.id.card_value)
    public TextView mCard;
    @BindView(R.id.open_shop)
    public CardView openShop;

    private Long shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSpendingPresenter = new SpendingPresenter(this);
        mSpendingPresenter.init(getIntent().getLongExtra(SPENDING_ID_ATTR, 0));


        openShop.setOnClickListener(l -> {
            Intent intent = new Intent(this, ShopActivity.class);
            intent.putExtra(ShopActivity.SHOP_ID_ATTR, this.shopId);
            startActivity(intent);
        });
    }


    @Override
    public void showSpending(Spending spending) {
        if (spending == null) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        this.setTitle(spending.getShop().getDisplayName());
        this.mSpendings.setText(String.valueOf(spending.getSum()));
        this.mShop.setText(spending.getShop().getDisplayName());
        this.mCard.setText(spending.getCard().getNumber());
        this.shopId = spending.getShop().getId();
    }
}
