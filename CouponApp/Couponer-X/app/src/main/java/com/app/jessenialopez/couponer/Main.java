package com.app.jessenialopez.couponer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import android.os.Environment;
import android.util.Log;

import static android.content.Intent.*;


public class Main extends AppCompatActivity {
    String storeName;
    private int count = 0;
    private boolean update = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the main activity
        setContentView(R.layout.activity_main);

        //buttons linked with the one on the screen
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button test = (Button) findViewById(R.id.button3);


        //represent the action when the button is clicked
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switch the original screen to the camera  screen
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //start the camera
                startActivityForResult(intent, 0);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switch the original screen to the camera  screen
                Intent intent = new Intent(ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;

                //start the camera
                startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
            }
        });


        //Database:
        test.setOnClickListener(new View.OnClickListener() {

            //set the storeName on the Database to be what is stored here

           // MainActivity m = new MainActivity();
            //m.str(storeName);

            public void onClick(View v) {
//                Toast.makeText(Main.this, "Im In" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main.this, ViewStores.class);
                if (intent != null)
                    Toast.makeText(Main.this, "Im In" , Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Main.this, "Im out" , Toast.LENGTH_SHORT).show();

//
//                startActivity(intent);
            }
            /*
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, com.app.jessenialopez.couponer.MainActivity.java);
                intent.putExtra("activitymain", "MainActivity");
                startActivity(intent);

        } */
        });



    }

//Scanner Alert dialog
    void Scan() {
        //test.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //public void onClick(View view) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(Main.this);
        View tView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
        aBuilder.setTitle("Product Store");
        final Spinner tSpinner = (Spinner) tView.findViewById(R.id.spinner);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(Main.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.stores));
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tSpinner.setAdapter(adp);

        aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!tSpinner.getSelectedItem().toString().equalsIgnoreCase("Choose a store name ")) {
                    storeName = tSpinner.getSelectedItem().toString();
                    Toast.makeText(Main.this, storeName , Toast.LENGTH_SHORT).show();

                    //EditText selection;
                    //data(selection);
                    dialogInterface.dismiss();

                }
            }
        });


        aBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        aBuilder.setView(tView);
        AlertDialog dialog = aBuilder.create();
        dialog.show();
        // }
        // });

        /*
        @Override
        protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            if(getIntent().getStringArrayExtra("activitymain").equals("MainActivity")) {
                m;
            }
        } */

    }

    //represent the result of the camera, saving the picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //represent the image itself
        Bitmap photo = (Bitmap) data.getExtras().get("data");

        //invoking the saving method
        SavePicture(photo);

    }

    //since the images are now in a specific directory, we can now chage the layout of our app
    void SavePicture(Bitmap coupon) {
        //Saving into a directory
        //note the name of the directory is CouponX
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera/CouponX";
        File myDir = new File(root);
        myDir.mkdirs();

        //count the initial number of images
        update = false;

        //each name of the pictures should be different, that is why there is a counter
        String fname = "Coupon" + count + ".png";
        File file = new File(myDir, fname);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        //exceptions, just in case
        try {
            FileOutputStream out = new FileOutputStream(file);
            coupon.compress(Bitmap.CompressFormat.PNG, 100, out);
            update = true;
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Toast the user that the coupon/picture has been saved
        //this should be modified, if the OCE do not recognize anything on the picture then it shouldn't save it, I think! lol
        if (update) {
            Toast.makeText(this, "Coupon Saved.", Toast.LENGTH_LONG).show();
            Scan();
        }

        else {
            Toast.makeText(this, "ERROR! Coupon NOT Saved.", Toast.LENGTH_LONG).show();
        }

        count++;
    }


    long NumberOfImages(String repName) {
        File dir = new File(repName);
        int totalNumFiles = dir.listFiles().length;
        return totalNumFiles;
    }
}