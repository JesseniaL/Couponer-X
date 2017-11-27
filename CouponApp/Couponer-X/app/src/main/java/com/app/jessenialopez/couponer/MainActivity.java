package com.app.jessenialopez.couponer;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.*;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //Displays image resources
    android.widget.ImageView ImageView;

    DatabaseHelper database;
    String storeName;
    EditText productName, expDate, couponNum;
    Button add, view;
    ListView stores;

    public void str(String s) {
        this.storeName = s;
    }

    public String getStr() {
        return storeName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        // Get the view of the buttons and text edits
        productName = (EditText) findViewById(R.id.productNameText);
        expDate = (EditText) findViewById(R.id.expirationDateText);
        couponNum = (EditText) findViewById(R.id.couponNumberText);
        add = (Button) findViewById(R.id.addButton);
        view = (Button) findViewById(R.id.viewButton);
        stores = (ListView) findViewById(R.id.storeList);
        database = new DatabaseHelper(this);

        // Creates database for this class
        //database = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = productName.getText().toString();
                String expiration = expDate.getText().toString();
                String coupon = couponNum.getText().toString();
                if (product.length() != 0 && expiration.length() != 0 && coupon.length() != 0) {
                    //addData(store, product, expiration, coupon);
                    database.addData(storeName, product, expiration, coupon);
                    productName.setText("");
                    expDate.setText("");
                    couponNum.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Text fields cannot be left blank.", Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewStores.class);
                startActivity(intent);
            }
        });
    }

    // Adds item from to database
    public void addEntry(String store, String name, String exp, String coup) {
        boolean insertData = database.addData(store, name, exp, coup);
        if (insertData == true) {
            Toast.makeText(this,
                    "Data inserted successfully",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,
                    "Data was not inserted",
                    Toast.LENGTH_LONG).show();
        }
    }

}