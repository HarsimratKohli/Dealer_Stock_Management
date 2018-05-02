package com.example.android.stockmanagement;

/**
 * Created by Harsimrat Kohli on 20-04-2018.
 */

public class SetData {

    //Person (Dealer or Customer)
    int  ID;
    int ContactNo;
    String Name;
    String Email;
    String Address;

    //Product
    int Quantity;
    String Type;
    float CostPrice;
    float SellingPrice;

    //Comission
    float Commission;

    //OrderDetails
    String DateOfDelivery;
    String DateOfShipment;
    String DateofOrder;
    String Status;
    float ShippingCost;



    SetData()
    {
        //int
        this.ID=0;
        this.ContactNo=0;
        //string
        this.Address="";
        this.Name="";
        this.Email="";
        this.Type="";
        this.DateOfDelivery="";
        this.DateOfShipment="";
        this.DateofOrder="";
        this.Status="";

       //float
        this.CostPrice=0;
        this.SellingPrice=0;
        this.Commission=0;
        this.ShippingCost=0;
    }

    //for Dealer
    SetData(String Name , int ContactNo , String Email)
    {

        this.Name=Name;
        this.ContactNo=ContactNo;
        this.Email=Email;
    }

    //for Customer
    SetData(String Name , int ContactNo , String Email , String Address )
    {
        this.Name=Name;
        this.ContactNo=ContactNo;
        this.Email=Email;
        this.Address=Address;
    }

    //for Product
    SetData(String Type , int Quantity , float CostPrice , float commission )
    {
        this.Type=Type;
        this.Quantity=Quantity;
        this.CostPrice=CostPrice;
        this.Commission=commission;
        this.SellingPrice=((commission*CostPrice)/100)+CostPrice;
    }

    //for OrderDetails
    SetData(String Date_order , String Date_delivery , String Date_shipment , float shippingCost )
    {
        this.DateofOrder=Date_order;
        this.DateOfDelivery=Date_delivery;
        this.DateOfShipment=Date_shipment;
        this.ShippingCost=shippingCost;

        this.Status="NO";
    }


}
