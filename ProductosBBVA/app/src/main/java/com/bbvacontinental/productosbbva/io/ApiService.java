package com.bbvacontinental.productosbbva.io;

import com.bbvacontinental.productosbbva.io.request.ListProductRequest;
import com.bbvacontinental.productosbbva.io.response.ListProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.URL_PRODUCTS)
    Call<ListProductResponse> getListProducts();
}
