package com.example.henry.couponer_x;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Barcode extends AppCompatActivity {

    private DatabaseHelper database;
    private ViewCoupons viewCoupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        database = database = new DatabaseHelper(this);
        ImageView imageView = (ImageView) findViewById(R.id.couponImage);
        viewCoupons = new ViewCoupons();
        /*
        Toast.makeText(this, viewCoupons.getStoreName() + "///"
        + viewCoupons.getCouponNumber() + "///"
        + database.getImage(viewCoupons.getStoreName(), viewCoupons.getCouponNumber()).length
        , Toast.LENGTH_LONG).show();
        */
        byte[] byteArray = database.getImage(viewCoupons.getStoreName(), viewCoupons.getCouponNumber());
        //Toast.makeText(this, ""+byteArray.length, Toast.LENGTH_LONG).show();
        //imageView.setImageBitmap(image);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageView.setImageBitmap(bmp);
    }

}
