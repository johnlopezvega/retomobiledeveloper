package com.bbvacontinental.productosbbva.ui.fragment;


import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.db.helper.ProductDBHelper;
import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.io.request.ListProductRequest;
import com.bbvacontinental.productosbbva.io.response.ListProductResponse;
import com.bbvacontinental.productosbbva.presenter.BasePresenter;
import com.bbvacontinental.productosbbva.presenter.ProductsPresenter;
import com.bbvacontinental.productosbbva.ui.activity.BaseActivity;
import com.bbvacontinental.productosbbva.ui.adapter.ProductsAdapter;
import com.bbvacontinental.productosbbva.ui.swipe.IProductSwipe;
import com.bbvacontinental.productosbbva.ui.swipe.ProductSwipe;
import com.bbvacontinental.productosbbva.view.IProductView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public class ProductsFragment extends BaseFragment<ProductsPresenter> implements IProductView {

    @BindView(R.id.listProducts) RecyclerView listProducts;
    private ProductSwipe productSwipe;
    ProductsAdapter productsAdapter;
    ItemTouchHelper itemTouchhelper;

    @BindView(R.id.buttonUpdate) Button buttonUpdate;

    public static ProductsFragment newInstance(){
        return new ProductsFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_products;
    }

    @Override
    protected void setupView(final View view) {
        setHasOptionsMenu(true);
        getPresenter().setContext(getActivity());
        productsAdapter = new ProductsAdapter(new ArrayList<Product>());
        listProducts.setAdapter(productsAdapter);
        listProducts.setLayoutManager(new LinearLayoutManager(getActivity()));

        productSwipe = new ProductSwipe(new IProductSwipe() {
            @Override
            public void onRightClicked(int position) {
                Product product = productsAdapter.getProductList().get(position);
                getPresenter().deleteProduct(product.getCode());
                productsAdapter.getProductList().remove(position);
                productsAdapter.notifyItemRemoved(position);
                productsAdapter.notifyItemRangeChanged(position, productsAdapter.getItemCount());
            }

            @Override
            public void onLeftClicked(int position) {
                Product product = productsAdapter.getProductList().get(position);
                EditProductFragment editProductFragment = EditProductFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putParcelable("product", product);
                editProductFragment.setArguments(bundle);
                ((BaseActivity) CONTEXT).replaceFragment(R.id.content, editProductFragment, true, false);
            }
        });

        itemTouchhelper = new ItemTouchHelper(productSwipe);
        itemTouchhelper.attachToRecyclerView(listProducts);
        listProducts.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                productSwipe.onDraw(c);
            }
        });

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
        productsAdapter.setProductList(productList);
        productsAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.buttonUpdate)
    public void onClickButtonUpdate(View v) {
        getPresenter().getListProduct(new ListProductRequest());
    }
}
