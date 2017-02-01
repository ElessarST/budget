package farrakhov.aydar.spendings.screen.spending;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.screen.shop.ShopActivity;
import farrakhov.aydar.spendings.util.PriceUtil;

public class SpendingActivity extends AppCompatActivity implements SpendingView, EditSpendingDialog.EditSpendingDialogListener {

    public static final String SPENDING_ID_ATTR = "spending_id";
    public static final String PREFS_NAME = "prefs";

    private SpendingPresenter mSpendingPresenter;

    @BindView(R.id.spendings_value)
    public TextView mSpendings;
    @BindView(R.id.shop_value)
    public TextView mShop;
    @BindView(R.id.card_value)
    public TextView mCard;
    @BindView(R.id.open_shop)
    public CardView openShop;
    @BindView(R.id.fab)
    public FloatingActionButton editButton;

    @BindView(R.id.delete_spending)
    public Button deleteSpending;

    private Long shopId;
    private Long spendingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSpendingPresenter = new SpendingPresenter(this);
        spendingId = getIntent().getLongExtra(SPENDING_ID_ATTR, 0);
        if (spendingId == 0) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            spendingId = settings.getLong(SPENDING_ID_ATTR, 0);
        }
        mSpendingPresenter.init(spendingId);

        editButton.setOnClickListener(l -> {
            Bundle bundle = new Bundle();
            bundle.putLong(SPENDING_ID_ATTR, spendingId);
            EditSpendingDialog dialog = new EditSpendingDialog();
            dialog.setListener(this);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "dialog");
        });


        openShop.setOnClickListener(l -> {
            Intent intent = new Intent(this, ShopActivity.class);
            intent.putExtra(ShopActivity.SHOP_ID_ATTR, this.shopId);
            startActivity(intent);
        });

        deleteSpending.setOnClickListener(l -> {
            RepositoryProvider.provideSpendingRepository()
                    .delete(spendingId);
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(upIntent)
                        .startActivities();
            } else {
                NavUtils.navigateUpTo(this, upIntent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(SPENDING_ID_ATTR, shopId);

        editor.apply();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showSpending(Spending spending) {
        if (spending == null) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }

        this.setTitle(spending.getShop().getDisplayName());
        this.mSpendings.setText(PriceUtil.format(spending.getSum()));
        this.mShop.setText(spending.getShop().getDisplayName());
        this.mCard.setText(spending.getCard().getNumber());
        this.shopId = spending.getShop().getId();
        this.spendingId = spending.getId();
    }

    @Override
    public void changeRest(Spending spending, Float sum) {
        RepositoryProvider.provideSpendingRepository()
                .change(spending.getId(), sum);
        mSpendingPresenter.init(spending.getId());
    }
}
