package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Category;

/**
 * @author Aydar Farrakhov
 */
public class CategoryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.category_name)
    TextView nameTV;



    @NonNull
    public static CategoryHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.category_item, null);
        return new CategoryHolder(view);
    }

    private CategoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Category category, CategoryAdapter.OnItemClickListener listener) {
        nameTV.setText(category.getName());
        itemView.setOnClickListener(v -> listener.onItemClick(category));
    }
}
