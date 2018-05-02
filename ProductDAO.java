package com.example.android.stockmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Harsimrat Kohli on 30-04-2018.
 */

public class ProductDAO {


    public static final String TAG = "ProductDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private StockDatabase mDbHelper;
    private String[] mAllColumns = { StockDatabase.P_Col1, StockDatabase.P_Col2, StockDatabase.P_Col3, StockDatabase.P_Col4,
            StockDatabase.P_Col5, StockDatabase.P_Col6 };

    public ProductDAO(Context context) {
        mDbHelper = new StockDatabase(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Product createProduct(String Type, String Quantity,
                                  String Price, String Commission,
                                  long CompanyID) {
        ContentValues values = new ContentValues();
        values.put(StockDatabase.P_Col2,Type);
        values.put(StockDatabase.P_Col3,Quantity);
        values.put(StockDatabase.P_Col4,Price);
        values.put(StockDatabase.P_Col5,Commission);
        values.put(StockDatabase.P_Col6,CompanyID);

        long insertId = mDatabase.insert(StockDatabase.P_TableName, null, values);

        Cursor cursor = mDatabase.query(StockDatabase.P_TableName, mAllColumns,
                StockDatabase.P_Col1 + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Product newProduct = cursorToProduct(cursor);
        cursor.close();
        return newProduct;
    }

    public void deleteProduct(Product product) {
        long id = product.getId();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(StockDatabase.P_TableName, StockDatabase.P_Col1
                + " = " + id, null);
    }

    public List<Product> getAllProducts() {
        List<Product> listEmployees = new ArrayList<Product>();

        Cursor cursor = mDatabase.query(StockDatabase.P_TableName, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            listEmployees.add(product);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEmployees;
    }

    public List<Product> getProductsOfDealer(long companyId) {
        List<Product> listEmployees = new ArrayList<Product>();

        Cursor cursor = mDatabase.query(StockDatabase.P_TableName, mAllColumns,
                StockDatabase.P_Col1 + " = ?",
                new String[] { String.valueOf(companyId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            listEmployees.add(product);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEmployees;
    }

    private Product cursorToProduct(Cursor cursor) {
        Product employee = new Product();
        employee.setId(cursor.getLong(0));
        employee.setmType(cursor.getString(1));
        employee.setmQuantity(cursor.getString(2));
        employee.setPrice(cursor.getString(3));
        employee.setmCommision(cursor.getString(4));

        // get The company by id
        long DealerId = cursor.getLong(5);
        DealerDAO  dao = new DealerDAO(mContext);
        Dealer company = dao.getCompanyById(DealerId);
        if (company != null)
            employee.setDealer(company);

        return employee;
    }

}
