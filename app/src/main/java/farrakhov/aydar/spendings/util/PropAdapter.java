package farrakhov.aydar.spendings.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.Prop;

/**
 * @author Aydar Farrakhov
 */
public class PropAdapter extends RecyclerView.Adapter<PropHolder> {

    private final List<Prop> mProps;
    private final OnItemClickListener listener;

    public PropAdapter(OnItemClickListener listener) {
        mProps = new ArrayList<>();
        this.listener = listener;
    }

    public void changeDataSet(@NonNull List<Prop> props) {
        mProps.clear();
        mProps.addAll(props);
        notifyDataSetChanged();
    }

    @Override
    public PropHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PropHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(PropHolder holder, int position) {
        Prop prop = mProps.get(position);
        holder.bind(prop, listener);

    }

    @Override
    public int getItemCount() {
        return mProps.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Prop item);
    }

}
