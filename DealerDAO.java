package com.example.android.stockmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.database.*;

import java.util.ArrayList;
import java.util.List;

public class DealerDAO extends AppCompatActivity {

    public static final String TAG = "DealerDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private StockDatabase stockDatabase;



    private Context mContext;
    private String[] mAllColumns = {StockDatabase.D_Col1 ,StockDatabase.D_Col2, String.valueOf(StockDatabase.D_Col3),
            StockDatabase.D_Col4};

    public DealerDAO(Context context)
    {
        this.mContext = context;

        stockDatabase = new StockDatabase(context);
        // open the database
        try {
            open();

        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = stockDatabase.getWritableDatabase();
    }

    public void close()
    {
        stockDatabase.close();
    }

    public Dealer createDealer(String name, String website, String phoneNumber)
    {
        ContentValues values = new ContentValues();
        values.put(StockDatabase.D_Col2, name);
        values.put(StockDatabase.D_Col3, Integer.parseInt(phoneNumber));
        values.put(StockDatabase.D_Col4, website);

        long insertId = mDatabase.insert(StockDatabase.D_TableName, null, values);

        Cursor cursor = mDatabase.query(StockDatabase.D_TableName, mAllColumns, StockDatabase.D_Col1 + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Dealer newDealer = cursorToCompany(cursor);
        cursor.close();
        return newDealer;
    }

    public void deleteDealer(Dealer dealer) {

        long id = dealer.getId();
        // delete all employees of this company
        ProductDAO productDao = new ProductDAO(mContext);

        List<Product> listProducts = productDao.getProductsOfDealer(id);
        if (listProducts != null && !listProducts.isEmpty()) {
            for (Product e : listProducts)
            {
                productDao.deleteProduct(e);
            }
        }

        System.out.println("the deleted company has the id: " + id);
        mDatabase.delete(StockDatabase.D_TableName, StockDatabase.D_Col1 + " = " + id, null);
    }

    public List<Dealer> getAllCompanies() {
        List<Dealer> listCompanies = new ArrayList<>();

        Cursor cursor = mDatabase.query(StockDatabase.D_TableName, mAllColumns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Dealer company = cursorToCompany(cursor);
                listCompanies.add(company);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompanies;
    }

    public Dealer getCompanyById(long id) {

        Cursor cursor = mDatabase.query(StockDatabase.D_TableName, mAllColumns, StockDatabase.D_Col1 + " = ?", new String[] { String.valueOf(id) }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Dealer company = cursorToCompany(cursor);
        return company;
    }

    protected Dealer cursorToCompany(Cursor cursor) {

        Dealer company = new Dealer();
        company.setId(cursor.getLong(0));
        company.setName(cursor.getString(1));
        company.setPhoneNumber(cursor.getString(2));
        company.setEmail(cursor.getString(3));
        return company;
    }


}
