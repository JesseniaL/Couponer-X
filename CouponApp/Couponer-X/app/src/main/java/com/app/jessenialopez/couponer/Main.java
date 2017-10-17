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
import java.util.LinkedList;
import android.support.v4.app.DialogFragment;

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
        LinkedList<Bitmap> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //set the main activity
            setContentView(R.layout.activity_main);

            //button linked with the one on the screen
            Button button1 = (Button) findViewById(R.id.button1);

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
        }

        //represent the result of the camera, saving the picture
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //represent the image itself
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            //set the screen to display the image
            ImageView.setImageBitmap(photo);
            //this is where we can store the images in the hashmap, or call the OCR function and then
            //store the info extracted from the images.

            //for example!!
            //list.add(photo);

        }

        //https://developer.android.com/guide/topics/ui/dialogs.html
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