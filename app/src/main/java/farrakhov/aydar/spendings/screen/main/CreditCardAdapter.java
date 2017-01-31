package farrakhov.aydar.spendings.screen.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.content.CreditCard;

/**
 * @author Aydar Farrakhov
 */
public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardHolder> {

    private final List<CreditCard> mCreditCards;

    private final OnItemClickListener mListener;

    public CreditCardAdapter(OnItemClickListener listener) {
        mCreditCards = new ArrayList<>();
        this.mListener = listener;
    }

    public void changeDataSet(@NonNull List<CreditCard> creditCards) {
        mCreditCards.clear();
        mCreditCards.addAll(creditCards);
        notifyDataSetChanged();
    }

    @Override
    public CreditCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CreditCardHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CreditCardHolder holder, int position) {
        CreditCard creditCard = mCreditCards.get(position);
        holder.bind(creditCard, mListener);

    }

    @Override
    public int getItemCount() {
        return mCreditCards.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CreditCard item);
    }
}
