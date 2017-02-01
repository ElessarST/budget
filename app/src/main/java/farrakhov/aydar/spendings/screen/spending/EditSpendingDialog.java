package farrakhov.aydar.spendings.screen.spending;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Spending;
import farrakhov.aydar.spendings.repository.RepositoryProvider;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class EditSpendingDialog extends DialogFragment {

    public interface EditSpendingDialogListener {
        void changeRest(Spending spending, Float sum);
    }

    private EditSpendingDialogListener mListener;

    private EditText sum;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        Spending spending= RepositoryProvider.provideSpendingRepository()
                .get(getArguments().getLong(SpendingActivity.SPENDING_ID_ATTR));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.change));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_credit_dialog, null);
        builder.setView(view)
                .setPositiveButton(R.string.change, (dialog, id) -> {
                    mListener.changeRest(spending, Float.valueOf(sum.getText().toString()));
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> EditSpendingDialog.this.getDialog().cancel());
        Dialog dialog =  builder.create();
        sum = (EditText) view.findViewById(R.id.credit_rest_value);
        sum.setText("");
        sum.append(String.valueOf(spending.getSum()));

        return dialog;
    }

    public void setListener(EditSpendingDialogListener listener) {
        mListener = listener;
    }
}
