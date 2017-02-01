package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.util.PriceUtil;

/**
 * @author Aydar Farrakhov
 */
public class SpendingsHolder extends RecyclerView.ViewHolder {

    DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM hh:mm");

    @BindView(R.id.shop)
    TextView shopTV;

    @BindView(R.id.cost)
    TextView costTV;

    @BindView(R.id.date)
    TextView dateTV;

    @NonNull
    public static SpendingsHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.spending_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new SpendingsHolder(view);
    }

    private SpendingsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Spending spending, SpendingsAdapter.OnItemClickListener listener) {
        DateTime date = new DateTime(spending.getDate());

        shopTV.setText(spending.getShop().getDisplayName());
        costTV.setText(PriceUtil.format(spending.getSum()));
        dateTV.setText(fmt.print(date));
        itemView.setOnClickListener(v -> listener.onItemClick(spending));
    }
}
