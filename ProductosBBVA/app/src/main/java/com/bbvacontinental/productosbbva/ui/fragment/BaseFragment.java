package com.bbvacontinental.productosbbva.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bbvacontinental.productosbbva.presenter.BasePresenter;
import com.bbvacontinental.productosbbva.ui.activity.BaseActivity;
import com.bbvacontinental.productosbbva.view.IBaseView;

import butterknife.ButterKnife;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {
    protected Context CONTEXT;
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        hideErrorMessage();
        setupView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity) CONTEXT).hiddenKeyboard();
    }

    protected abstract int getFragmentLayout();
    protected abstract void setupView(View view);
    protected abstract BasePresenter getPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CONTEXT = context;
    }

    @Override
    public void startLoading() {
        ((BaseActivity) CONTEXT).startLoading();
    }

    @Override
    public void stopLoading() {
        ((BaseActivity) CONTEXT).stopLoading();
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity) CONTEXT).showErrorMessage(message);
    }

    @Override
    public void hideErrorMessage() {
        ((BaseActivity) CONTEXT).hideErrorMessage();
    }

    private void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void error(Throwable e) {

    }

    @Override
    public void error() {

    }
}
