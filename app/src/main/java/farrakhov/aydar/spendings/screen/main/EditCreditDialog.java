package farrakhov.aydar.spendings.screen.main;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.CreditCard;
import farrakhov.aydar.spendings.repository.RepositoryProvider;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class EditCreditDialog extends DialogFragment {

    public interface EditCreditDialogListener {
        void changeRest(CreditCard creditCard, Float rest);
    }

    public static final String CREDIT_ID_ATTR = "credit_id";

    private EditCreditDialogListener mListener;

    private EditText creditRest;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        CreditCard creditCard = RepositoryProvider.provideCreditCardProvider()
                .get(getArguments().getLong(CREDIT_ID_ATTR));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.add));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_credit_dialog, null);
        builder.setView(view)
                .setPositiveButton(R.string.change, (dialog, id) -> {
                    mListener.changeRest(creditCard, Float.valueOf(creditRest.getText().toString()));
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> EditCreditDialog.this.getDialog().cancel());
        Dialog dialog =  builder.create();
        creditRest = (EditText) view.findViewById(R.id.credit_rest_value);
        creditRest.setText("");
        creditRest.append(String.valueOf(creditCard.getCredit()));

        return dialog;
    }

    public void setListener(EditCreditDialogListener listener) {
        mListener = listener;
    }
}
