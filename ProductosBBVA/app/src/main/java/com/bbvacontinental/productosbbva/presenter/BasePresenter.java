package com.bbvacontinental.productosbbva.presenter;

import com.bbvacontinental.productosbbva.io.ApiConstants;
import com.bbvacontinental.productosbbva.io.ApiService;
import com.bbvacontinental.productosbbva.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public abstract class BasePresenter<T extends IBaseView> {
    protected CompositeDisposable compositeDisposable;
    protected T view;
    protected ApiService apiService;

    public BasePresenter() {
        compositeDisposable = new CompositeDisposable();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        this.apiService = retrofit.create(ApiService.class);
    }

    public void detach() {
        view = null;
    }

    public void clearSubscriptions() {
        compositeDisposable.clear();
    }

}
