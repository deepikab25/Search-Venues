package com.foursquare.View.Adapters;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.foursquare.R;

public class HistoryCursorAdapter extends SimpleCursorAdapter {
    public HistoryCursorAdapter(Context mContext, int mLayout, Cursor mCursor, String[] mFrom, int[] mTo, int mFlags) {
        super(mContext, mLayout, mCursor, mFrom, mTo, mFlags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView=(TextView)view.findViewById(R.id.textView4);
        textView.setText(cursor.getString(1));
    }
}
