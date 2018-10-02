package com.example.android.novuscarta;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.novuscarta.data.BookContract.BookEntry;

/**
 * {@link BookCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of book data as its data source. This adapter knows
 * how to create list items for each row of book data in the {@link Cursor}.
 */

public class BookCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link BookCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the book data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the title for the current book can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        final TextView bookIdTextView = view.findViewById(R.id.book_id);
        TextView productNameTextView = view.findViewById(R.id.product_name);
        TextView productPriceTextView = view.findViewById(R.id.product_price);
        final TextView quantityTextView = view.findViewById(R.id.quantity);

        // Find the columns of book attributes that we're interested in
        int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int breedColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the book attributes from the Cursor for the current book
        String bookId = cursor.getString(idColumnIndex);
        String bookName = cursor.getString(nameColumnIndex);
        String bookPrice = cursor.getString(breedColumnIndex);
        final String bookQuantity = cursor.getString(quantityColumnIndex);

        // Update the TextViews with the attributes for the current book
        bookIdTextView.setText(bookId);
        productNameTextView.setText(bookName);
        productPriceTextView.setText(bookPrice);
        quantityTextView.setText(bookQuantity);

        // Set up the OnClickListener on the sold button
        Button soldButton = view.findViewById(R.id.sold_button);
        soldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current quantity from the TextView
                int currentQuantity =
                        Integer.parseInt(quantityTextView.getText().toString().trim());
                // If currentQuantity is greater than 0, sell a copy and amend the database
                if (currentQuantity > 0) {
                    currentQuantity -= 1;
                    quantityTextView.setText(Integer.toString(currentQuantity));
                    // Get the ID of the sold book from the hidden TextView and complete the
                    // update to the database
                    long bookIdNumber = Integer.parseInt(bookIdTextView.getText().toString());
                    Uri bookSold = ContentUris.withAppendedId(BookEntry.CONTENT_URI, bookIdNumber);
                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_PRODUCT_QUANTITY, currentQuantity);
                    int rowsUpdated = context.getContentResolver().update(bookSold, values,
                            null, null);
                    if (rowsUpdated != 0) {
                        // Database update was successful
                        Toast.makeText(context, R.string.book_sold, Toast.LENGTH_SHORT).show();
                    } else {
                        // There was an error updating the database
                        Toast.makeText(context, R.string.sold_database_error,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // The current quantity is 0 therefore a copy cannot be sold
                    Toast.makeText(context, R.string.insufficient_quantity,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
