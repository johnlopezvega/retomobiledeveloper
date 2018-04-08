package com.bbvacontinental.productosbbva.ui.fragment;


import android.database.Cursor;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.db.helper.ProductDBHelper;
import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.io.request.ListProductRequest;
import com.bbvacontinental.productosbbva.io.response.ListProductResponse;
import com.bbvacontinental.productosbbva.presenter.BasePresenter;
import com.bbvacontinental.productosbbva.presenter.ProductsPresenter;
import com.bbvacontinental.productosbbva.ui.adapter.ProductsAdapter;
import com.bbvacontinental.productosbbva.view.IProductView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public class ProductsFragment extends BaseFragment<ProductsPresenter> implements IProductView {

    @BindView(R.id.listProducts) RecyclerView listProducts;

    public static ProductsFragment newInstance(){
        return new ProductsFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_products;
    }

    @Override
    protected void setupView(View view) {
        setHasOptionsMenu(true);
        getPresenter().setContext(getActivity());
        getPresenter().getListProduct(new ListProductRequest());
        getPresenter().printProducts();
    }

    @Override
    protected ProductsPresenter getPresenter() {
        if (mPresenter == null)
            mPresenter = new ProductsPresenter(this);
        return mPresenter;
    }

    @Override
    public void printListProduct(List<Product> productList) {

        ProductsAdapter productsAdapter = new ProductsAdapter(productList);
        listProducts.setAdapter(productsAdapter);
        listProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        listProducts.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}
