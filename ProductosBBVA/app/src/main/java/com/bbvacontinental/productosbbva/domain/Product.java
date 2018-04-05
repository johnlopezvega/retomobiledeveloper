package com.bbvacontinental.productosbbva.domain;

import android.content.ContentValues;

import com.bbvacontinental.productosbbva.db.contract.ProductContract;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public class Product {
    private String code;
    private String name;
    private String description;
    private String quantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME_CODE, code);
        values.put(ProductContract.ProductEntry.COLUMN_NAME_NAME, name);
        values.put(ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(ProductContract.ProductEntry.COLUMN_NAME_QUANTITY, quantity);
        return values;
    }
}
