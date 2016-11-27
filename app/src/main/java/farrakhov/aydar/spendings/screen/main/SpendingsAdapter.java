package farrakhov.aydar.spendings.screen.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Spending;

/**
 * @author Artur Vasilov
 */
public class SpendingsAdapter extends RecyclerView.Adapter<SpendingsHolder> {

    private final List<Spending> mSpendings;


    public SpendingsAdapter() {
        mSpendings = new ArrayList<>();
    }

    public void changeDataSet(@NonNull List<Spending> movies) {
        mSpendings.clear();
        mSpendings.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public SpendingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SpendingsHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(SpendingsHolder holder, int position) {
        Spending movie = mSpendings.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return mSpendings.size();
    }

}
