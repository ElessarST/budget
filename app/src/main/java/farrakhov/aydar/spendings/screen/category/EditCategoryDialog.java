package farrakhov.aydar.spendings.screen.category;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.repository.RepositoryProvider;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class EditCategoryDialog extends DialogFragment {

    public EditText mChangeCategoryName;
    public EditText mCost;
    public CheckBox mMonthly;

    public interface EditCategoryDialogListener {
        void change(String name, float cost, boolean monthly);
    }

    private EditCategoryDialogListener mListener;


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);


        Category category = RepositoryProvider.provideCategoryRepository()
                .get(getArguments().getLong(CategoryActivity.CATEGORY_ID_ATTR));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.change));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_category, null);
        builder.setView(view)
                .setPositiveButton(R.string.change, (dialog, id) -> {
                    mListener.change(mChangeCategoryName.getText().toString(),
                            Float.valueOf(mCost.getText().toString()),
                            mMonthly.isChecked());
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> EditCategoryDialog.this.getDialog().cancel());
        Dialog dialog = builder.create();
        mChangeCategoryName = (EditText) view.findViewById(R.id.changeCategoryName);
        mChangeCategoryName.setText("");
        mChangeCategoryName.append(category.getName());

        mCost = (EditText) view.findViewById(R.id.budget_value);
        mMonthly = (CheckBox) view.findViewById(R.id.is_monthly);

        if (category.getSum() != null) {
            mCost.setText(String.valueOf(category.getSum()));
            mMonthly.setChecked(category.isMonthly());
        }

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (EditCategoryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditCategoryDialogListener");
        }
    }
}
