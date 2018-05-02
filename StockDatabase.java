package com.example.android.stockmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Harsimrat Kohli on 19-04-2018.
 */

public class StockDatabase extends SQLiteOpenHelper {

    public static final String DatabaseName = "Stock.db";
    public SQLiteDatabase db;

    // Dealer Table
    public static final String D_TableName = "Dealer_Table";
    public static final String D_Col1 = "Dealer_ID";
    public static final String D_Col2 = "Name";
    public static final String D_Col3 = "Contact_No";
    public static final String D_Col4 = "Email";


    // Customer Table
    public static final String C_TableName = "Customer_Table";
    public static final String C_Col1 = "Customer_ID";
    public static final String C_Col2 = "Name";
    public static final String C_Col3 = "Contact_No";
    public static final String C_Col4 = "Address";
    public static final String C_Col5 = "Email";

    // Product Table
    public static final String P_TableName = "Product_Table";
    public static final String P_Col1 = "Product_ID";
    public static final String P_Col2 = "Type";
    public static final String P_Col3 = "Quantity";
    public static final String P_Col4 = "Cost_Price";
    public static final String P_Col5 = "Commission";
    public static final String P_Col6 = "Company_ID";


    // OrderDetails Table
    public static final String O_TableName = "OrderDetails_Table";
    public static final String O_Col1 = "Order_ID";
    public static final String O_Col2 = "Date_of_Order";
    public static final String O_Col3 = "Date_of_Delivery";
    public static final String O_Col4 = "Date_of_Shipment";
    public static final String O_Col5 = "Shipping_Cost";
    public static final String O_Col6 = "STATUS";

    public StockDatabase(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(" CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s INTEGER,%s TEXT)", D_TableName, D_Col1, D_Col2, D_Col3, D_Col4));
        db.execSQL(String.format(" CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s INTEGER,%s TEXT,%s TEXT)", C_TableName, C_Col1, C_Col2, C_Col3, C_Col4, C_Col5));
        db.execSQL(String.format(" CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s INTEGER,%s INTEGER,%s INTEGER,%s INTEGER)", P_TableName, P_Col1, P_Col2, P_Col3, P_Col4, P_Col5, P_Col6));
        db.execSQL(String.format(" CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s INTEGER,%s TEXT,%s INTEGER,%s INTEGER)", O_TableName, O_Col1, O_Col2, O_Col3, O_Col4, O_Col5, O_Col6));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Dealer_Table");
        db.execSQL("DROP TABLE IF EXISTS Customer_Table");
        db.execSQL("DROP TABLE IF EXISTS Producr_Table");
        db.execSQL("DROP TABLE IF EXISTS OrderDetails_Table");
        onCreate(db);
    }
}


















