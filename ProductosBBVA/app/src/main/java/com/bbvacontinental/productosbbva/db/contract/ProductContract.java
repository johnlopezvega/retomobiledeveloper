package com.bbvacontinental.productosbbva.db.contract;

import android.provider.BaseColumns;

/**
 * Created by johnlopezvega on 4/04/18.
 */

public final class ProductContract {

    public ProductContract() {}

    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
    }
}
