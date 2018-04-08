package com.bbvacontinental.productosbbva.domain;

import android.content.ContentValues;
import android.database.Cursor;

import com.bbvacontinental.productosbbva.db.contract.ProductContract;

import java.text.ParseException;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public class Product {
    private String code;
    private String name;
    private String description;
    private String quantity;

    public Product() {

    }

    public Product(Cursor cursor) throws ParseException {
        super();
        code = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME_CODE));
        name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME_NAME));
        description = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION));
        quantity = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME_QUANTITY));
    }

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
