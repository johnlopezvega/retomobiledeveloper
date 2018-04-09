package com.bbvacontinental.productosbbva;

import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.io.ApiService;
import com.bbvacontinental.productosbbva.io.request.ListProductRequest;
import com.bbvacontinental.productosbbva.io.response.ListProductResponse;
import com.bbvacontinental.productosbbva.io.response.ProductResponse;
import com.bbvacontinental.productosbbva.presenter.ProductsPresenter;
import com.bbvacontinental.productosbbva.ui.fragment.ProductsFragment;
import com.bbvacontinental.productosbbva.view.IProductView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    protected ApiService apiService;

    @Mock
    private ProductsFragment view;

    @Mock
    private ProductsPresenter productsPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchListProducts() {

        Call<ListProductResponse> response = null;

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setCode("1");
        product.setName("@");
        product.setDescription("!@#");
        product.setQuantity("12");
        productList.add(product);

        //when(apiService.getListProducts()).thenReturn(response);

        //ProductsPresenter productsPresenter = new ProductsPresenter(view);
        productsPresenter.getListProduct(new ListProductRequest());

        InOrder inOrder = Mockito.inOrder(productsPresenter);
        inOrder.verify(productsPresenter, times(1)).getListProduct(new ListProductRequest());
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}