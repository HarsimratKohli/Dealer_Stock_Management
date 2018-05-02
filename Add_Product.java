package com.example.android.stockmanagement;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Add_Product extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

       public static final String TAG = "Add Product Activity";
       private EditText mTxtProductName;
       private EditText mTxtProductQuantity;
       private EditText mTxtCost;
       private EditText mTxtCommission;

        private Spinner mSpinnerCompany;
        private Button mBtnAdd;

        private DealerDAO mCompanyDao;
        private ProductDAO mProductDao;

        private Dealer mSelectedCompany;
        private SpinnerDealerAdapter mAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add__product);

            initViews();

            this.mCompanyDao = new DealerDAO(this);
            this.mProductDao = new ProductDAO(this);

            //fill the spinner with companies
            List<Dealer> listCompanies = mCompanyDao.getAllCompanies();
            if(listCompanies != null) {
                mAdapter = new SpinnerDealerAdapter(this,listCompanies);
                mSpinnerCompany.setAdapter(mAdapter);
                mSpinnerCompany.setOnItemSelectedListener(this);
            }
        }

        private void initViews() {
            this.mTxtProductName = (EditText) findViewById(R.id.txt_ProductF);
            this.mTxtProductQuantity = (EditText) findViewById(R.id.txt_QauntityF);
            this.mTxtCost = (EditText) findViewById(R.id.txt_CostF);
            this.mTxtCommission = (EditText) findViewById(R.id.txt_commissionF);
            this.mSpinnerCompany = (Spinner) findViewById(R.id.spinner_companies);
            this.mBtnAdd = (Button) findViewById(R.id.btn_add);

            this.mBtnAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add:
                    Editable Product = mTxtProductName.getText();
                    Editable Quantity = mTxtProductQuantity.getText();
                    Editable Cost = mTxtCost.getText();
                    Editable Commission = mTxtCommission.getText();

                    mSelectedCompany = (Dealer)mSpinnerCompany.getSelectedItem();

                    if (!TextUtils.isEmpty(Product) && !TextUtils.isEmpty(Quantity)
                            && !TextUtils.isEmpty(Cost) && !TextUtils.isEmpty(Commission)
                            && mSelectedCompany != null
                             ) {
                        // add the company to database

                        Product createdEmployee = mProductDao.createProduct(Product.toString(), Quantity.toString(), Cost.toString(), Commission.toString(), mSelectedCompany.getId());

                        Log.d(TAG, "added employee : "+ createdEmployee.getmType()+" "+createdEmployee.getDealer());
                        setResult(RESULT_OK);
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
            mCompanyDao.close();
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mSelectedCompany = mAdapter.getItem(position);
            Log.d(TAG, "selectedCompany : "+mSelectedCompany.getName());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
}



