package com.foursquare.View.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foursquare.Model.Search.VenueListDTO;
import com.foursquare.R;

import java.util.ArrayList;

public class HomeScreenListViewAdapter extends RecyclerView.Adapter<HomeScreenListViewAdapter.ListViewItem> {
    private Context mContext;
    private ArrayList<VenueListDTO> mArrayListVenues = new ArrayList<>();


    public HomeScreenListViewAdapter(Context _mContext)
    {
        mContext = _mContext;
    }

    public void refresh(ArrayList<VenueListDTO> _mArrayListVenues) {
        mArrayListVenues.clear();
        ArrayList<VenueListDTO> mArrayListVenuesTemp = new ArrayList<>();
        mArrayListVenuesTemp.addAll(_mArrayListVenues);
        mArrayListVenues.addAll(mArrayListVenuesTemp);
    }

    public class ListViewItem extends RecyclerView.ViewHolder {
        public TextView mTextViewName, mTextViewDistance, mTextViewAddress;

        public ListViewItem(View view) {
            super(view);
            mTextViewName = (TextView)view.findViewById(R.id.textView4);
            mTextViewDistance = (TextView)view.findViewById(R.id.textView3);
            mTextViewAddress = (TextView)view.findViewById(R.id.textView6);
        }
    }

    @Override
    public HomeScreenListViewAdapter.ListViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_screen_recyclerview_item, parent, false);
        return new ListViewItem(itemView);
    }

    @Override
    public void onBindViewHolder(HomeScreenListViewAdapter.ListViewItem holder, int i) {
        holder.mTextViewName.setText(mArrayListVenues.get(i).getName());
        holder.mTextViewDistance.setText(mArrayListVenues.get(i).getDistance());
        holder.mTextViewAddress.setText(mArrayListVenues.get(i).getAddress() );
    }

    @Override
    public int getItemCount() {
        return mArrayListVenues.size();
    }
}
