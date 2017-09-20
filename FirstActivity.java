package capstone.firstactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button cameraButton;
    // Allows the user to press the camera button to go to camera activity
    public void pressCameraButton(){
        cameraButton = (Button)findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity is the first activity when app is opened
                // CameraActivity is opens the camera
                Intent camButton = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(camButton);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pressCameraButton();

    }
}
