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
import java.util.Objects;

import farrakhov.aydar.spendings.R;
import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.content.Shop;
import farrakhov.aydar.spendings.repository.RepositoryProvider;
import farrakhov.aydar.spendings.screen.shop.ShopActivity;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public class EditShopDialog extends DialogFragment {

    public EditText mChangeShopName;
    public Spinner mShopSpinner;
    public Spinner mCategoriesSpinner;
    public CheckBox mSaveName;
    public CheckBox mUnionShops;
    public CheckBox mChangeCategory;

    public interface EditShopDialogListener {
        void change(String name, Shop shop, Category category, boolean saveName);
    }

    private EditShopDialogListener mListener;

    private List<Shop> mShops = new ArrayList<>();
    private List<Category> mCategories = new ArrayList<>();


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
                            mUnionShops.isChecked() ? mShops.get(mShopSpinner.getSelectedItemPosition()) : null,
                            mChangeCategory.isChecked() ? mCategories.get(mCategoriesSpinner.getSelectedItemPosition()) : null,
                            mSaveName.isChecked());
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> EditShopDialog.this.getDialog().cancel());
        Dialog dialog = builder.create();
        mChangeShopName = (EditText) view.findViewById(R.id.changeShopName);
        mChangeShopName.setText("");
        mChangeShopName.append(shop.getDisplayName());

        mShopSpinner = (Spinner) view.findViewById(R.id.shops_spinner);
        mCategoriesSpinner = (Spinner) view.findViewById(R.id.categories_spinner);

        mSaveName = (CheckBox) view.findViewById(R.id.save_shop_name) ;
        mUnionShops = (CheckBox) view.findViewById(R.id.union_shops) ;
        mChangeCategory = (CheckBox) view.findViewById(R.id.change_category) ;

        initShopsSpinner(shop);
        initCategoriesSpinner(shop);

        return dialog;
    }

    private void initCategoriesSpinner(Shop shop) {
        List<String> categoriesNames = new ArrayList<>();
        RepositoryProvider.provideCategoryRepository().getAll()
                .filter(s -> !Objects.equals(s.getId(), shop.getCategory().getId()))
                .subscribe(s -> {
                    categoriesNames.add(s.getName());
                    mCategories.add(s);
                });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, categoriesNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategoriesSpinner.setAdapter(adapter);
    }

    private void initShopsSpinner(Shop shop) {
        List<String> shopNames = new ArrayList<>();
        RepositoryProvider.provideShopRepository().getAll()
                .filter(s -> !Objects.equals(s.getId(), shop.getId()))
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
