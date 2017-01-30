package farrakhov.aydar.spendings.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Prop;

/**
 * @author Aydar Farrakhov
 */
public class PropHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.value)
    TextView value;

    @NonNull
    public static PropHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.spending_prop, null);
        return new PropHolder(view);
    }

    private PropHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Prop prop, PropAdapter.OnItemClickListener listener) {
        name.setText(prop.getName());
        value.setText(prop.getValue());
        itemView.setOnClickListener(v -> listener.onItemClick(prop));
    }
}
