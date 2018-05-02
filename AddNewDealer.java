package com.example.android.stockmanagement;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewDealer extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "AddCompanyActivity";

    private EditText mTxtCompanyName;
    private EditText mTxtPhoneNumber;
    private EditText mTxtWebsite;
    private Button mBtnAdd;

    private DealerDAO mDealerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dealer);
        initViews();

        this.mDealerDao = new DealerDAO(this);

    }


    private void initViews() {
        this.mTxtCompanyName = (EditText) findViewById(R.id.txt_company_name);
        this.mTxtPhoneNumber = (EditText) findViewById(R.id.txt_phone_number);
        this.mTxtWebsite = (EditText) findViewById(R.id.txt_website);
        this.mBtnAdd = (Button) findViewById(R.id.btn_add);

        this.mBtnAdd.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Editable companyName = mTxtCompanyName.getText();
                Editable phoneNumber = mTxtPhoneNumber.getText();
                Editable email = mTxtWebsite.getText();
                if (!TextUtils.isEmpty(companyName) && !TextUtils.isEmpty(email)
                        && !TextUtils.isEmpty(phoneNumber)) {
                    // add the company to database
                    Dealer createdCompany = mDealerDao.createDealer(companyName.toString(),email.toString(),phoneNumber.toString());

                    Log.d(TAG, "added company : "+ createdCompany.getName());
                    Intent intent = new Intent();
                    intent.putExtra(ListDealersActivity.EXTRA_ADDED_COMPANY, createdCompany);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDealerDao.close();
    }
}