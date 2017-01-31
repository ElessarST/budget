package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.CreditCard;

/**
 * Created by aydar farrakhov on 14.12.16.
 */

public class CreditCardHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.number)
    TextView numberTV;

    @BindView(R.id.rest_val)
    TextView restValTV;

    @NonNull
    public static CreditCardHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.credit_card_item, null);
        return new CreditCardHolder(view);
    }

    private CreditCardHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull CreditCard creditCard, CreditCardAdapter.OnItemClickListener listener) {
        numberTV.setText(creditCard.getNumber());
        restValTV.setText(String.valueOf(creditCard.getCredit()));
        itemView.setOnClickListener(v -> listener.onItemClick(creditCard));
    }
}

