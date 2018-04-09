package com.bbvacontinental.productosbbva.presenter;

import android.content.Context;
import android.database.Cursor;

import com.bbvacontinental.productosbbva.db.helper.ProductDBHelper;
import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.io.request.ListProductRequest;
import com.bbvacontinental.productosbbva.io.response.ListProductResponse;
import com.bbvacontinental.productosbbva.io.response.ProductResponse;
import com.bbvacontinental.productosbbva.view.IProductView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public class ProductsPresenter extends BasePresenter<IProductView> implements Callback<ListProductResponse> {
    private ProductDBHelper productDBHelper;

    public ProductsPresenter(IProductView view) {
        super();
        this.view = view;
    }

    public void setContext(Context context) {
        productDBHelper = new ProductDBHelper(context);
    }

    public void getListProduct(ListProductRequest request) {
        //view.startLoading();
        Call<ListProductResponse> call = apiService.getListProducts();
        call.enqueue(this);
    }

    public void deleteProduct(String code) {
        productDBHelper.deleteByCode(code);
        productDBHelper.close();
    }

    public void printProducts() {
        Cursor c = productDBHelper.getAll();
        List<Product> productList = new ArrayList<>();
        if (c != null) {
            while (c.moveToNext()) {
                Product product = new Product();
                product.setCode(c.getString(0));
                product.setName(c.getString(1));
                product.setDescription(c.getString(2));
                product.setQuantity(c.getString(3));
                productList.add(product);
            }
            view.printListProduct(productList);
        }
        productDBHelper.close();
    }

    @Override
    public void onResponse(Call<ListProductResponse> call, Response<ListProductResponse> response) {
        if (response.isSuccessful()) {
            List<String> ids = new ArrayList<>();
            Cursor c = productDBHelper.getAll();
            if (c != null) {
                while (c.moveToNext()) {
                    ids.add(c.getString(0));
                }
                if (ids.size() > 0)
                    productDBHelper.delete(ids.toArray(new String[ids.size()]));
            }

            for (ProductResponse productResponse: response.body().getListProducts()) {
                Product product = new Product();
                product.setCode(productResponse.getCode());
                product.setName(productResponse.getName());
                product.setDescription(productResponse.getDescription());
                product.setQuantity(productResponse.getQuantity());
                productDBHelper.save(product);
            }
            productDBHelper.close();
            printProducts();
        } else {
            view.error();
        }
        //view.stopLoading();
    }

    @Override
    public void onFailure(Call<ListProductResponse> call, Throwable t) {
        view.error(t);
        //view.startLoading();
    }
}
