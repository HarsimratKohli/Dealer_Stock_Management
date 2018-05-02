package com.example.android.stockmanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListDealersActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    public static final String TAG = "ListCompaniesActivity";

    public static final int REQUEST_CODE_ADD_COMPANY = 40;
    public static final String EXTRA_ADDED_COMPANY = "extra_key_added_company";

    private ListView mListviewDealer;
    private TextView mTxtEmptyListDealer;
    private ImageButton mBtnAddDealer;

    private ListDealersAdapter mAdapter;
    private List<Dealer> mListDealer;
    private DealerDAO mDealerDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dealers);

        // initialize views
        initViews();

        // fill the listView
        mDealerDao = new DealerDAO(this);
        mListDealer = mDealerDao.getAllCompanies();

        if(mListDealer != null && !mListDealer.isEmpty())
        {
            mAdapter = new ListDealersAdapter(this, mListDealer);
            mListviewDealer.setAdapter(mAdapter);
        }
        else {
            mTxtEmptyListDealer.setVisibility(View.VISIBLE);
            mListviewDealer.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        this.mListviewDealer = (ListView) findViewById(R.id.list_companies);
        this.mTxtEmptyListDealer = (TextView) findViewById(R.id.txt_empty_list_companies);
        this.mBtnAddDealer = (ImageButton) findViewById(R.id.btn_add_company);
        this.mListviewDealer.setOnItemClickListener(this);
        this.mListviewDealer.setOnItemLongClickListener(this);
        this.mBtnAddDealer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_company:
                Intent intent = new Intent(this, AddNewDealer.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_COMPANY);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_COMPANY) {
            if(resultCode == RESULT_OK) {
                // add the added company to the listCompanies and refresh the listView
                if(data != null) {
                    Dealer createdCompany = (Dealer) data.getSerializableExtra(EXTRA_ADDED_COMPANY);
                    if(createdCompany != null) {
                        if(mListDealer== null)
                            mListDealer = new ArrayList<Dealer>();
                        mListDealer.add(createdCompany);

                        if(mAdapter == null) {
                            if(mListviewDealer.getVisibility() != View.VISIBLE) {
                                mListviewDealer.setVisibility(View.VISIBLE);
                                mTxtEmptyListDealer.setVisibility(View.GONE);
                            }

                            mAdapter = new ListDealersAdapter(ListDealersActivity.this, mListDealer);
                            mListviewDealer.setAdapter(mAdapter);
                        }
                        else {
                            mAdapter.setItems(mListDealer);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDealerDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dealer clickedCompany = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : "+clickedCompany.getName());
        Intent intent = new Intent(this, List_Product.class);
        intent.putExtra(List_Product.EXTRA_SELECTED_COMPANY_ID, clickedCompany.getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Dealer clickedCompany = mAdapter.getItem(position);
        Log.d(TAG, "longClickedItem : "+clickedCompany.getName());
        showDeleteDialogConfirmation(clickedCompany);
        return true;
    }

    private void showDeleteDialogConfirmation(final Dealer clickedCompany) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure you want to delete the \""+clickedCompany.getName()+"\" company ?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the company and refresh the list
                if(mDealerDao != null) {
                    mDealerDao.deleteDealer(clickedCompany);
                    mListDealer.remove(clickedCompany);

                    //refresh the listView
                    if(mListDealer.isEmpty()) {
                        mListviewDealer.setVisibility(View.GONE);
                        mTxtEmptyListDealer.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setItems(mListDealer);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListDealersActivity.this, R.string.company_deleted_successfully, Toast.LENGTH_SHORT).show();
            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
}
