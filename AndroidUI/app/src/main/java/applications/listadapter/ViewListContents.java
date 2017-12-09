package applications.listadapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {



    DatabaseHelper database;
    ArrayList<Information> couponList;
    ListView listView;
    Information info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        database = new DatabaseHelper(this);
        // Displays information from database into list view
        couponList = new ArrayList<>();
        Cursor data = database.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ViewListContents.this,
                    "The Database is empty",
                    Toast.LENGTH_LONG).show();
        }else{
            int i = 0;
            while(data.moveToNext()){
                info = new Information(data.getString(1),data.getString(2),data.getString(3));
                couponList.add(i,info);
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, couponList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}