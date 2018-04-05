package com.bbvacontinental.productosbbva.db.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bbvacontinental.productosbbva.db.contract.ProductContract;
import com.bbvacontinental.productosbbva.domain.Product;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public class ProductDBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " (" +
                    ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY," +
                    ProductContract.ProductEntry.COLUMN_NAME_CODE + " TEXT," +
                    ProductContract.ProductEntry.COLUMN_NAME_NAME + " TEXT," +
                    ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ProductContract.ProductEntry.COLUMN_NAME_QUANTITY + " INTEGER" +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductContract.ProductEntry.TABLE_NAME;

    // Si cambia el esquema de la base de datos, debe incrementar la versión de la base de datos.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Product.db";

    public ProductDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Esta base de datos es solo un caché para datos en línea, por lo que su política de
        // actualización es simplemente descartar los datos y comenzar de nuevo
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    public long save(Product product) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                ProductContract.ProductEntry.TABLE_NAME, null, product.toContentValues());
    }

    public Cursor getAll() {
        return getReadableDatabase().query(ProductContract.ProductEntry.TABLE_NAME,
                null, null, null, null, null, null);
    }

    public Cursor getById(String id) {
        Cursor c = getReadableDatabase().query(
                ProductContract.ProductEntry.TABLE_NAME, null,
                ProductContract.ProductEntry._ID + " LIKE ?", new String[]{id}, null, null, null);
        return c;
    }

    public int delete(String id) {
        return getWritableDatabase().delete(
                ProductContract.ProductEntry.TABLE_NAME,
                ProductContract.ProductEntry._ID + " LIKE ?", new String[]{id});
    }

    public int delete(String[] ids) {
        return getWritableDatabase().delete(
                ProductContract.ProductEntry.TABLE_NAME,
                ProductContract.ProductEntry._ID + " IN (" + new String(new char[ids.length - 1]).replace("\0", "?,") + "?)", ids);
    }

    public int update(Product product, String id) {
        return getWritableDatabase().update(
                ProductContract.ProductEntry.TABLE_NAME, product.toContentValues(),
                ProductContract.ProductEntry._ID + " LIKE ?", new String[]{id}
        );
    }
}
