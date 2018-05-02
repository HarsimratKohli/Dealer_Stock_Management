package com.example.android.stockmanagement;
import java.io.Serializable;

/**
 * Created by Harsimrat Kohli on 30-04-2018.
 */

public class Dealer implements Serializable  {

        public static final String TAG = "Dealer";
        private static final long serialVersionUID = -7406082437623008161L;

        private long mId;
        private String mName;
        private String mPhoneNumber;
        private String mEmail;

        public Dealer() {}


        public Dealer(String name, String phoneNumber, String Email) {
            this.mName = name;
            this.mPhoneNumber = phoneNumber;
            this.mEmail=Email;
        }


        public long getId()
        {
            return mId;
        }
        public void setId(long mId)
        {
            this.mId = mId;
        }
        public String getName()
        {
            return mName;
        }
        public void setName(String mFirstName)
        {
            this.mName = mFirstName;
        }
        public String getPhoneNumber()
        {
            return mPhoneNumber;
        }
        public void setPhoneNumber(String mPhoneNumber)
        {
            this.mPhoneNumber = mPhoneNumber;
        }
        public String getmEmail()
        {
            return mEmail;
        }
        public void setEmail(String Email)
        {
        this.mEmail = Email;
    }

    }
