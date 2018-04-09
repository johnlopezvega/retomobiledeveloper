package com.bbvacontinental.productosbbva.presenter;

import android.content.Context;

import com.bbvacontinental.productosbbva.db.helper.ProductDBHelper;
import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.view.IEditProductView;

/**
 * Created by johnlopezvega on 9/04/18.
 */

public class EditProductsPresenter extends BasePresenter<IEditProductView> {
    private ProductDBHelper productDBHelper;

    public EditProductsPresenter(IEditProductView view) {
        super();
        this.view = view;
    }

    public void setContext(Context context) {
        productDBHelper = new ProductDBHelper(context);
    }

    public void updateProduct(Product product) {
        productDBHelper.updateByCode(product, product.getCode());
    }
}
