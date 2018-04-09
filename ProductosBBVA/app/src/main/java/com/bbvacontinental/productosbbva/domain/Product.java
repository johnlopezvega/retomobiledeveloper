package com.bbvacontinental.productosbbva.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.bbvacontinental.productosbbva.db.contract.ProductContract;

import java.text.ParseException;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public class Product implements Parcelable {
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

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.quantity);
    }

    public Product(Parcel in){
        this.code = in.readString();
        this.name = in.readString();
        this.description =  in.readString();
        this.quantity = in.readString();
    }
}
