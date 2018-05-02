package com.example.android.stockmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Harsimrat Kohli on 01-05-2018.
 */


public class ListProductAdapter extends BaseAdapter {
     public static final String TAG = "List Product Adapter";

        private List<Product> mItems;
        private LayoutInflater mInflater;

        public ListProductAdapter(Context context, List<Product> listCompanies) {
            this.setItems(listCompanies);
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0;
        }

        @Override
        public Product getItem(int position) {
            return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ViewHolder holder;
            if (v == null) {
                v = mInflater.inflate(R.layout.activity_list_item_product, parent, false);
                holder = new ViewHolder();
                holder.txtproductName = (TextView) v.findViewById(R.id.txt_Product_Type);
                holder.txtCompanyName = (TextView) v.findViewById(R.id.txt_dealer_name);
                holder.txtQuantity = (TextView) v.findViewById(R.id.txt_quantity);
                holder.txtCommission = (TextView) v.findViewById(R.id.txt_Commission);
                holder.txtCost = (TextView) v.findViewById(R.id.txt_costPrice);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }

            // fill row data
            Product currentItem = getItem(position);
            if (currentItem != null) {
                holder.txtproductName.setText(currentItem.getmType());
                holder.txtQuantity.setText(currentItem.getmQuantity());
                holder.txtCost.setText(currentItem.getmPrice());
                holder.txtCommission.setText(currentItem.getmCommision());

               holder.txtCompanyName.setText(currentItem.getDealer().getName());
            }

            return v;
        }

        public List<Product> getItems() {
            return mItems;
        }

        public void setItems(List<Product> mItems) {
            this.mItems = mItems;
        }

        class ViewHolder {
            TextView txtproductName;
            TextView txtCommission;
            TextView txtCost;
            TextView txtCompanyName;
            TextView txtQuantity;
        }

    }
