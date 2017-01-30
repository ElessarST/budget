package farrakhov.aydar.spendings.screen.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Category;

/**
 * @author Aydar Farrakhov
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private final List<Category> mCategories;
    private final OnItemClickListener listener;

    public CategoryAdapter(OnItemClickListener listener) {
        mCategories = new ArrayList<>();
        this.listener = listener;
    }

    public void changeDataSet(@NonNull List<Category> categories) {
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
        Category category = mCategories.get(position);
        holder.bind(category, listener);

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Category item);
    }

}
