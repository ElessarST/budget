package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.helper.CategoryWithDetails;
import farrakhov.aydar.spendings.content.helper.Period;
import farrakhov.aydar.spendings.util.PriceUtil;

import static farrakhov.aydar.spendings.util.PriceUtil.colorize;

/**
 * @author Aydar Farrakhov
 */
public class CategoryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.category_name)
    TextView nameTV;

    @BindView(R.id.category_spending)
    TextView categorySpending;

    @BindView(R.id.left_for_spending)
    TextView leftForSpending;

    @NonNull
    public static CategoryHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.category_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new CategoryHolder(view);
    }

    private CategoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull CategoryWithDetails category, CategoryAdapter.OnItemClickListener listener, Context context) {
        nameTV.setText(category.getName());
        categorySpending.setText(PriceUtil.format(category.getTotal()));
        if (category.getPeriod().equals(Period.MONTH) || !category.isMonthly()) {
            float left = category.getPlanned() - category.getTotal();
            leftForSpending.setText(PriceUtil.format(category.getPlanned() - category.getTotal()));
            colorize(leftForSpending, left, context);
        } else {
            leftForSpending.setText("-");
        }

        itemView.setOnClickListener(v -> listener.onItemClick(category));
    }
}
