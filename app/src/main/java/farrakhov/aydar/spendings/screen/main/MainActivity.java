package farrakhov.aydar.spendings.screen.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.util.SMSReader;
import farrakhov.aydar.spendings.widget.DividerItemDecoration;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;

    private MainPresenter mPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mSpendingsRecycler;

    @BindView(R.id.creditRecyclerView)
    RecyclerView mCreditRecyclerView;

    @BindView(R.id.updateAllButton)
    Button mUpdateButton;

    private SpendingsAdapter mAdapter;
    private CreditCardAdapter mCreditCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle(getString(R.string.budget));

        initSpendings();
        initCreditCards();

        mPresenter = new MainPresenter(this);
        mPresenter.init(this);

        mUpdateButton.setOnClickListener(i -> {
            mPresenter.updateAll(this);
        });

    }

    private void initCreditCards() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        mCreditRecyclerView.setLayoutManager(layoutManager);
        mCreditRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mCreditCardAdapter = new CreditCardAdapter();
        mCreditRecyclerView.setAdapter(mCreditCardAdapter);
    }

    private void initSpendings() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        mSpendingsRecycler.setLayoutManager(layoutManager);
        mSpendingsRecycler.addItemDecoration(new DividerItemDecoration(this));
        mAdapter = new SpendingsAdapter();
        mSpendingsRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    SMSReader.getNewSms(this);
                }
            }

        }
    }

    @Override
    public void requestSmsReadPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS},
                    MY_PERMISSIONS_REQUEST_READ_SMS);

        } else {
            SMSReader.getNewSms(this);
        }
    }

    @Override
    public void showSpendings(List<Spending> spendingList) {
        mAdapter.changeDataSet(spendingList);
    }

    @Override
    public void showCreditCards(List<CreditCard> creditCards) {
        mCreditCardAdapter.changeDataSet(creditCards);
    }
}
