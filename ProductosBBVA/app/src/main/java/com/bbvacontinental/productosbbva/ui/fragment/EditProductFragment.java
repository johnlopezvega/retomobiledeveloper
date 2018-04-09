package com.bbvacontinental.productosbbva.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.presenter.EditProductsPresenter;
import com.bbvacontinental.productosbbva.ui.activity.BaseActivity;
import com.bbvacontinental.productosbbva.view.IEditProductView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by johnlopezvega on 9/04/18.
 */

public class EditProductFragment extends BaseFragment<EditProductsPresenter> implements IEditProductView {

    private Product product;

    @BindView(R.id.textCode) EditText textCode;
    @BindView(R.id.textName) EditText textName;
    @BindView(R.id.textDescription) EditText textDescription;
    @BindView(R.id.textQuantity) EditText textQuantity;
    @BindView(R.id.buttonSave) Button buttonSave;

    public static EditProductFragment newInstance(){
        return new EditProductFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_edit_product;
    }

    @Override
    protected void setupView(View view) {
        setHasOptionsMenu(true);
        getPresenter().setContext(getActivity());
        product = getArguments().getParcelable("product");
        textCode.setText(product.getCode());
        textName.setText(product.getName());
        textDescription.setText(product.getDescription());
        textQuantity.setText(product.getQuantity());
    }

    @Override
    protected EditProductsPresenter getPresenter() {
        if (mPresenter == null)
            mPresenter = new EditProductsPresenter(this);
        return mPresenter;
    }

    @OnClick(R.id.buttonSave)
    public void onClickButtonSave(View view) {
        product.setName(textName.getText().toString());
        product.setDescription(textDescription.getText().toString());
        product.setQuantity(textQuantity.getText().toString());
        getPresenter().updateProduct(product);
        ((BaseActivity) CONTEXT).replaceFragment(R.id.content, ProductsFragment.newInstance(), false, true);
    }
}
