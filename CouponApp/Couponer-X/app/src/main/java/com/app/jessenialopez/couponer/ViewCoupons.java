package com.app.jessenialopez.couponer;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class ViewCoupons extends AppCompatActivity {

    DatabaseHelper database;
    Information info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_data);

        ListView listView = (ListView) findViewById(R.id.couponList);
        ListView storeView = (ListView) findViewById(R.id.storeList);
        database = new DatabaseHelper(this);
        // Displays information from database into list view
        ArrayList<Information> coupons = new ArrayList<>();
        Cursor data = database.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(this,
                    "The Database is empty",
                    Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                int i = 0;
                String storeName;
                info = new Information(data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                coupons.add(i,info);
                i++;
            }
            TwoColumnAdapter adapter =  new TwoColumnAdapter(this,R.layout.display_information, coupons);
            listView.setAdapter(adapter);
        }
    }
}
