package com.bbvacontinental.productosbbva.io.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public class ListProductResponse extends BaseResponse {

    @SerializedName("listaprod")
    private List<ProductResponse> listProducts;

    public List<ProductResponse> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<ProductResponse> listProducts) {
        this.listProducts = listProducts;
    }
}
