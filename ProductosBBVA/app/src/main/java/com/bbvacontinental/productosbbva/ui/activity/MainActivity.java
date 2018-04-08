package com.bbvacontinental.productosbbva.ui.activity;

import android.os.Bundle;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.presenter.ProductsPresenter;
import com.bbvacontinental.productosbbva.ui.fragment.ProductsFragment;

public class MainActivity extends BaseNavigationActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(R.id.content, ProductsFragment.newInstance(), false, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
