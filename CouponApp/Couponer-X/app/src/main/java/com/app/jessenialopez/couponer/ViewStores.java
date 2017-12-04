package com.app.jessenialopez.couponer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewStores extends AppCompatActivity {

    DatabaseHelper database;
    List<String> allStores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list);

        final ListView listView = (ListView) findViewById(R.id.storeList);
        database = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = database.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }
        else {
            while (data.moveToNext()) {
                // Adds the store names into Arraylist and iterate through to check
                // if there is already store name inside the Arraylist.
                // If true, don't add into adapter. Else add into adapter
                allStores.add(data.getString(data.getColumnIndex("STORE_NAME")));
                boolean found = false;
                for(int i  = 0; i < allStores.size()-1; i++) {
                    if(data.getString(data.getColumnIndex("STORE_NAME")).equals(allStores.get(i))){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    theList.add(data.getString(data.getColumnIndex("STORE_NAME")));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                    listView.setAdapter(listAdapter);
                }
            }
        }
        if(listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ViewStores.this, ViewCoupons.class);
                    DatabaseHelper.store_flag =  true;
                    database.storeN = listView.getItemAtPosition(i).toString();
                    System.out.println(listView.getItemAtPosition(i));
                    startActivity(intent);
                }
            });
        }
    }
}
