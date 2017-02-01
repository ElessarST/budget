package farrakhov.aydar.spendings.screen.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.util.PriceUtil;

public class CategoryActivity extends AppCompatActivity implements
        CategoryView, EditCategoryDialog.EditCategoryDialogListener {


    private CategoryPresenter mPresenter;

    public static final String CATEGORY_ID_ATTR = "category_id";

    @BindView(R.id.category_name_value)
    public TextView categoryName;

    @BindView(R.id.budget_value)
    public TextView budgetValue;

    @BindView(R.id.delete_category)
    public Button deleteCategory;

    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->{
            Bundle bundle = new Bundle();
            bundle.putLong(CATEGORY_ID_ATTR, mCategory.getId());
            EditCategoryDialog dialog = new EditCategoryDialog();
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "dialog");
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mPresenter = new CategoryPresenter(this);
        mPresenter.init(getIntent().getLongExtra(CATEGORY_ID_ATTR, 0L));

        deleteCategory.setOnClickListener(l -> {
            RepositoryProvider.provideCategoryRepository().delete(mCategory.getId());
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
    public void show(Category category) {
        setTitle(category.getName());
        mCategory = category;
        categoryName.setText(category.getName());
        if (category.getSum() != null) {
            budgetValue.setText(PriceUtil.format(category.getSum()) +
                    (category.isMonthly() ? " в месяц" : " в день"));
        } else {
            budgetValue.setText(getString(R.string.not_specified));
        }
    }

    @Override
    public void change(String name, float cost, boolean monthly) {
        mPresenter.change(this.mCategory, name, cost, monthly);
        mPresenter.init(this.mCategory.getId());
    }
}
