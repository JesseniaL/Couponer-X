package com.app.jessenialopez.couponer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.LinkedList;


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
           // ImageView.setImageBitmap(photo);
            //this is where we can store the images in the hashmap, or call the OCR function and then
            //store the info extracted from the images.

            //for example!!
            list.add(photo);

        }

}


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

       /* private File getFile() {
            File folder = new File("sdcard/camera_app");
            if (!folder.exist()) {
                folder.mkdir();
            }
            File image_file = new File(folder, "couponer_img.jpg");
            return image_file;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           // String path = "sdcard/couponer_app/couponer_img.jpg";
           // imageTakenPic.setImageDrawable(Drawable.createFromPath(path));
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == CAMERA_REQUEST){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgTakenPic.setImageBitmap(bitmap);
            }

        }

        class btnTakePhotoClicker implements  Button.OnClickListener{

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
 */