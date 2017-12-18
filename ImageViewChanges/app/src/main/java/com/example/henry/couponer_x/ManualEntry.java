package com.example.henry.couponer_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.*;

public class ManualEntry extends AppCompatActivity {

    private DatabaseHelper database;
    private Button add,view;
    private EditText storeName, expDate, couponNum;
    private String couponNumber;
    private HomeScreen homeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        homeScreen = new HomeScreen();
        // Check if the coupon number has been extracted
        if(homeScreen.getCouponNumber() != null){
            //Toast.makeText(this, homeScreen.getCouponNumber().toString(), Toast.LENGTH_LONG).show();
            // Remove any new lines from extracted coupon
            if(homeScreen.getCouponNumber().contains("\n")){
                homeScreen.setCouponNumber(homeScreen.getCouponNumber().replace("\n", ""));
                // Set the coupon number string
                couponNumber = homeScreen.getCouponNumber();
                //Toast.makeText(this, couponNumber, Toast.LENGTH_LONG).show();
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_entry);
        database = new DatabaseHelper(this);
        // Get the view of the buttons and text edits
        storeName = (EditText)findViewById(R.id.storeNameText);
        expDate = (EditText)findViewById(R.id.expirationDateText);
        couponNum = (EditText)findViewById(R.id.couponNumberText);
        // If the coupon number string is not null, set it for the user
        if(couponNumber != null){
            couponNum.setText(couponNumber, EditText.BufferType.EDITABLE);
        }
        add = (Button)findViewById(R.id.addButton);
        view = (Button)findViewById(R.id.viewButton);

        // Add button will add entries into the database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String store = storeName.getText().toString();
                String expiration = expDate.getText().toString();
                String coupon = couponNum.getText().toString();
                // Makes sure the field is not left blank
                if(store.length() == 0 && expiration.length() == 0 && coupon.length() == 0){
                    Toast.makeText(ManualEntry.this,
                            "Text fields cannot be left blank.",
                            Toast.LENGTH_LONG).show();
                }
                // Makes sure the store name and coupon number is unique
                else if(database.findQuery(store, coupon)){
                    Toast.makeText(ManualEntry.this,
                            "Coupon already exist!",
                            Toast.LENGTH_LONG).show();
                    storeName.setText("");
                    expDate.setText("");
                    couponNum.setText("");
                }
                else{
                    addEntry(store, expiration, coupon, homeScreen.getArray());
                    storeName.setText("");
                    expDate.setText("");
                    couponNum.setText("");
                }
            }
        });
        // Views the all the store names of the coupon
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEntry.this, ViewStores.class);
                startActivity(intent);
            }
        });
    }
    // Helper function to add an entry with Toast method when it's complete.
    public void addEntry(String storeName, String exp, String num, byte[] image) {
        boolean insertData = database.addData(storeName, exp, num, image);
        if(insertData == true) {
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Data not inserted.", Toast.LENGTH_LONG).show();
        }
    }

    public void setCouponNumber(String couponNumber){
        this.couponNumber = couponNumber;
    }
}
