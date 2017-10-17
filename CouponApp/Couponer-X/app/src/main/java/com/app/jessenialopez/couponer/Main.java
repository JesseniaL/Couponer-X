package com.app.jessenialopez.couponer;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//import java.util.LinkedList;
//import android.support.v4.app.DialogFragment;

import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;


public class Main extends AppCompatActivity {

    /*
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    } */
    //Displays image resources
    ImageView ImageView;
    private int count = 0;
    private long before, after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the main activity
        setContentView(R.layout.activity_main);

        //buttons linked with the one on the screen
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);


        //blank space on the screen linked
        ImageView = (ImageView) findViewById(R.id.imageView);

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
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;

                //start the camera
                startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
            }
        });
    }

    //represent the result of the camera, saving the picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //represent the image itself
        Bitmap photo = (Bitmap) data.getExtras().get("data");

        //set the screen to display the image
//            ImageView.setImageBitmap(photo);


        //invoking the saving method
        SavePicture(photo);

//        after = NumberOfImages(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/CouponX"); // "/mnt/sdcard/yourfolder"


    }

    //since the images are now in a specific directory, we can now chage the layout of our app
    void SavePicture(Bitmap coupon){
        //Saving into a directory
        //note the name of the directory is CouponX
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/CouponX";
        File myDir = new File(root);
        myDir.mkdirs();
        //count the initial number of images
        before = NumberOfImages(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/CouponX"); // "/mnt/sdcard/yourfolder"

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
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //count the number of images in the directory after the picture is saved
        after = NumberOfImages(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/CouponX"); // "/mnt/sdcard/yourfolder"


        //Toast the user that the coupon/picture has been saved
        //this should be modified, if the OCE do not recognize anything on the picture then it shouldn't save it, I think! lol
        if (before+1 == after)
            Toast.makeText(this, "Coupon Saved.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "ERROR! Coupon NOT Saved.", Toast.LENGTH_LONG).show();

            //set the screen to display the image
            ImageView.setImageBitmap(photo);
            //this is where we can store the images in the hashmap, or call the OCR function and then
            //store the info extracted from the images.

            //for example!!
            //list.add(photo);

        count++;
    }


        long NumberOfImages(String repName){
            File dir = new File(repName);
            int totalNumFiles = dir.listFiles().length;
            return totalNumFiles;
        }
    /*        //https://developer.android.com/guide/topics/ui/dialogs.html
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
            //Getting layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.activity_main, null))
                    //Adding action buttons
                    .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // sign in
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
                        public void onCLick(DialogInterface dialog, int id) {
                            LoginDialogFragment.this.getDialog().cancel();
                        }
                    });
        builder.create();

}
*/


            /*
            //Added below:
            btnpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = getFile();
                    camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(camera_intent, CAMERA_REQUEST);
                }
            });

        }


        //Hashed Database
        @Override
       /* protected void callHashMap() {


            //Linked button to view the database
            Button button2 = (Button) findViewById(R.id.button2);

            //When button2 is clicked
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }

            });
        }
        */


}