package com.bbvacontinental.productosbbva.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.presenter.BasePresenter;
import com.bbvacontinental.productosbbva.view.IBaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    @BindView(R.id.error_message) LinearLayout mErrorMessageView;
    @BindView(R.id.error_message_text) TextView mErrorText;
    @BindView(R.id.translucent_background) RelativeLayout translucentBackground;
    protected T mPresenter;

    private final Handler handler = new Handler();
    private Runnable runnable;

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hiddenKeyboard();
    }

    @Override
    public void startLoading() {
        hiddenKeyboard();
        translucentBackground.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        translucentBackground.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        if (mErrorMessageView != null && mErrorText != null) {
            if (runnable != null)
                handler.removeCallbacks(runnable);
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (mErrorMessageView != null) {
                        try {
                            if (mErrorMessageView.getVisibility() == View.VISIBLE)
                                mErrorMessageView.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            mErrorText.setText(message);
            mErrorMessageView.setVisibility(View.VISIBLE);
            handler.postDelayed(runnable, 5000);
        }
    }

    @Override
    public void hideErrorMessage() {
        if (runnable != null)
            handler.removeCallbacks(runnable);
        if (View.VISIBLE == mErrorMessageView.getVisibility())
            mErrorMessageView.setVisibility(View.GONE);
    }

    private void injectViews() {
        ButterKnife.bind(this);
    }

    public void replaceFragment(int res, Fragment fragment, boolean backStack, boolean clear) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (backStack)
            transaction.addToBackStack(null);
        if (clear)
            for (int i = 0; i < manager.getBackStackEntryCount(); ++i)
                manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.replace(res, fragment).commit();
    }

    public void hiddenKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void error(Throwable e) {

    }

    @Override
    public void error() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null)
            mPresenter.clearSubscriptions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detach();
    }
}
