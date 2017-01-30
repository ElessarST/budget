package farrakhov.aydar.spendings.screen.shop.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.screen.shop.ShopActivity;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class EditShopDialog extends DialogFragment {

    public EditText mChangeShopName;
    public Spinner mShopSpinner;
    public CheckBox mSaveName;
    public CheckBox mUnionShops;

    public interface EditShopDialogListener {
        void change(String name, Shop shop, boolean saveName, boolean unionShops);
    }

    private EditShopDialogListener mListener;

    private List<Shop> mShops = new ArrayList<>();


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);


        Shop shop = RepositoryProvider.provideShopRepository()
                .get(getArguments().getLong(ShopActivity.SHOP_ID_ATTR));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.change));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_shop, null);
        builder.setView(view)
                .setPositiveButton(R.string.change, (dialog, id) -> {
                    mListener.change(mChangeShopName.getText().toString(),
                            mShops.get(mShopSpinner.getSelectedItemPosition()),
                            mSaveName.isChecked(),
                            mUnionShops.isChecked());
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> EditShopDialog.this.getDialog().cancel());
        Dialog dialog = builder.create();
        mChangeShopName = (EditText) view.findViewById(R.id.changeShopName);
        mChangeShopName.setText(shop.getDisplayName());

        mShopSpinner = (Spinner) view.findViewById(R.id.shops_spinner);

        mSaveName = (CheckBox) view.findViewById(R.id.save_shop_name) ;
        mUnionShops = (CheckBox) view.findViewById(R.id.union_shops) ;

        initShopsSpinner(shop);

        return dialog;
    }

    private void initShopsSpinner(Shop shop) {
        List<String> shopNames = new ArrayList<>();
        RepositoryProvider.provideShopRepository().getShops(shop.getId())
                .subscribe(s -> {
                    shopNames.add(s.getDisplayName());
                    mShops.add(s);
                });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, shopNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mShopSpinner.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (EditShopDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditShopDialogListener");
        }
    }
}
