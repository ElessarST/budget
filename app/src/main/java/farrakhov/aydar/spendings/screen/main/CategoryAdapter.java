package farrakhov.aydar.spendings.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.helper.CategoryWithDetails;

/**
 * @author Aydar Farrakhov
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private final List<CategoryWithDetails> mCategories;
    private final OnItemClickListener listener;
    private Context mContext;

    public CategoryAdapter(OnItemClickListener listener, Context context) {
        mCategories = new ArrayList<>();
        this.mContext = context;
        this.listener = listener;
    }

    public void changeDataSet(@NonNull List<CategoryWithDetails> categories) {
        mCategories.clear();
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CategoryHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        CategoryWithDetails category = mCategories.get(position);
        holder.bind(category, listener, mContext);

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CategoryWithDetails item);
    }

}
