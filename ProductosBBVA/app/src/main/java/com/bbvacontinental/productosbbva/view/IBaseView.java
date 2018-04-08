package com.bbvacontinental.productosbbva.view;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public interface IBaseView {
    void error(Throwable e);
    void error();
    void showErrorMessage(String message);
    void hideErrorMessage();
    void startLoading();
    void stopLoading();
}

