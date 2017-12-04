package applications.editablelistview;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.*;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper database;
    Button add,view;
    EditText storeName, expDate, couponNum;
    ListView stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the view of the buttons and text edits
        storeName = (EditText)findViewById(R.id.storeNameText);
        expDate = (EditText)findViewById(R.id.expirationDateText);
        couponNum = (EditText)findViewById(R.id.couponNumberText);
        add = (Button)findViewById(R.id.addButton);
        view = (Button)findViewById(R.id.viewButton);
        stores = (ListView)findViewById(R.id.storeList);
        database = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String store = storeName.getText().toString();
                String expiration = expDate.getText().toString();
                String coupon = couponNum.getText().toString();
                if(store.length() != 0 && expiration.length() != 0 && coupon.length() != 0){
                    addEntry(store, expiration, coupon);
                    storeName.setText("");
                    expDate.setText("");
                    couponNum.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Text fields cannot be left blank.",
                            Toast.LENGTH_LONG).show();
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

    public void addEntry(String storeName, String exp, String num) {

        boolean insertData = database.addData(storeName, exp, num);
        if(insertData == true) {
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Data not inserted.", Toast.LENGTH_LONG).show();
        }
    }

}
