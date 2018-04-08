package com.bbvacontinental.productosbbva.view;

import com.bbvacontinental.productosbbva.domain.Product;
import com.bbvacontinental.productosbbva.io.response.ListProductResponse;

import java.util.List;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public interface IProductView extends IBaseView {
    void printListProduct(List<Product> productList);
}
