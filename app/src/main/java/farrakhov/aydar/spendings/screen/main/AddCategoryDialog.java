package farrakhov.aydar.spendings.screen.main;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import farrakhov.aydar.spendings.R;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class AddCategoryDialog extends DialogFragment {

    public interface AddCategoryDialogListener {
        void add(String name);
    }

    private AddCategoryDialogListener mListener;

    private EditText categoryName;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.add));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_category_dialog, null);
        builder.setView(view)
                .setPositiveButton(R.string.add, (dialog, id) -> {
                    mListener.add(categoryName.getText().toString());
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> AddCategoryDialog.this.getDialog().cancel());
        Dialog dialog =  builder.create();
        categoryName = (EditText) view.findViewById(R.id.category_name_value);
        return dialog;
    }

    public AddCategoryDialog setListener(AddCategoryDialogListener listener) {
        mListener = listener;
        return this;
    }
}
