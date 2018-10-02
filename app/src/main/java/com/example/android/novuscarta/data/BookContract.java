package com.example.android.novuscarta.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BookContract {

    private BookContract() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.novuscarta";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.novuscarta/books/ is a valid path for
     * looking at book data. content://com.example.android.novuscarta/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_BOOKS = "books";

    public static abstract class BookEntry implements BaseColumns {

        /** The content URI to access the book data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of books.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single book.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /** Name of database table for books */
        public static final String TABLE_NAME = "books";

        /**
         * Unique ID number for the book (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the book.
         *
         * Type: TEXT
         */
        public static final String COLUMN_PRODUCT_NAME = "title";

        /**
         * Price of the book.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_PRODUCT_PRICE = "price";

        /**
         * Quantity of the book in stock.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";

        /**
         * Category of the book.
         *
         * The only possible values are {@link #CATEGORY_UNKNOWN}, {@link #CATEGORY_FICTION},
         * {@link #CATEGORY_NONFICTION} or {@link #CATEGORY_REFERENCE}.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_PRODUCT_CATEGORY = "category";

        /**
         * Name of the supplier.
         *
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_NAME = "supplier";

        /**
         * Number of the supplier.
         *
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_NUMBER = "number";

        /**
         * Possible values for the category of the product
         */
        public static final int CATEGORY_UNKNOWN = 0;
        public static final int CATEGORY_FICTION = 1;
        public static final int CATEGORY_NONFICTION = 2;
        public static final int CATEGORY_REFERENCE = 3;

        /**
         * Returns whether or not the given category is {@link #CATEGORY_UNKNOWN},
         * {@link #CATEGORY_FICTION}, {@link #CATEGORY_NONFICTION} or {@link #CATEGORY_REFERENCE}.
         */
        public static boolean isValidCategory(int category) {
            if (category == CATEGORY_UNKNOWN || category == CATEGORY_FICTION
                    || category == CATEGORY_NONFICTION || category == CATEGORY_REFERENCE) {
                return true;
            }
            return false;
        }
    }
}
