package com.bbvacontinental.productosbbva.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbvacontinental.productosbbva.R;
import com.bbvacontinental.productosbbva.domain.Product;

import java.util.List;

/**
 * Created by johnlopezvega on 8/04/18.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private List<Product> productList;

    public ProductsAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(itemView);
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textDescription.setText(product.getDescription());
        holder.icoState.setImageResource(R.drawable.ic_work_black_24dp);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView textDescription;
        private ImageView icoState;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            textDescription = (TextView) itemView.findViewById(R.id.textDescription);
            icoState = (ImageView) itemView.findViewById(R.id.ico_product);
        }
    }
}
