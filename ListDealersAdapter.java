package com.example.android.stockmanagement;

import android.widget.TextView;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListDealersAdapter extends BaseAdapter {

    public static final String TAG = "List Dealer Adapter";

    private List<Dealer> mItems;
    private LayoutInflater mInflater;

    public ListDealersAdapter(Context context, List<Dealer> listCompanies) {
        this.setItems(listCompanies);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Dealer getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.activity_list_item_dealer, parent, false);
            holder = new ViewHolder();
            holder.txtCompanyName = (TextView) v.findViewById(R.id.txt_dealer_name);
            holder.txtPhoneNumber = (TextView) v.findViewById(R.id.txt_phone_number);
            holder.txtWebsite = (TextView) v.findViewById(R.id.txt_website);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Dealer currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtCompanyName.setText(currentItem.getName());
            holder.txtWebsite.setText(currentItem.getmEmail());
            holder.txtPhoneNumber.setText(currentItem.getPhoneNumber());
        }

        return v;
    }

    public List<Dealer> getItems()
    {
        return mItems;
    }

    public void setItems(List<Dealer> mItems)
    {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtCompanyName;
        TextView txtWebsite;
        TextView txtPhoneNumber;
    }

}

