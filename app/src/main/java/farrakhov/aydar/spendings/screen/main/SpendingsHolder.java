package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Spending;

/**
 * @author Aydar Farrakhov
 */
public class SpendingsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shop)
    TextView shopTV;

    @BindView(R.id.cost)
    TextView costTV;

    @BindView(R.id.date)
    TextView dateTV;

    @NonNull
    public static SpendingsHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.spending_item, null);
        return new SpendingsHolder(view);
    }

    private SpendingsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Spending spending, SpendingsAdapter.OnItemClickListener listener) {
        shopTV.setText(spending.getShop().getDisplayName());
        costTV.setText(String.valueOf(spending.getSum()));
        dateTV.setText(spending.getDate().toString());
        itemView.setOnClickListener(v -> listener.onItemClick(spending));
    }
}
