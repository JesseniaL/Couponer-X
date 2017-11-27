package applications.listadapter;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText storeName, expDate, couponNum;
    Button add, view;
    DatabaseHelper database;

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
        // Creates database for this class
        database = new DatabaseHelper(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String store = storeName.getText().toString();
                String expiration = expDate.getText().toString();
                String coupon = couponNum.getText().toString();
                if(store.length() != 0 && expiration.length() != 0 && coupon.length() != 0){
                    addData(store, expiration, coupon);
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
    }
    // Adds item from to database
    public void addData(String name, String exp, String coup){
        if(database.addData(name,exp,coup)){
            Toast.makeText(MainActivity.this,
                    "Data inserted successfully",
                    Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "Data was not inserted",
                    Toast.LENGTH_LONG).show();
        }
    }
}
