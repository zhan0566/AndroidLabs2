package com.cst2335.androidlabs2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {

    ActivityResultLauncher<String> mPhotoLauncher;
    public final static String TAG ="ProfileActivity";
    ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgv = findViewById( R.id.picture);

        ActivityResultLauncher<Intent> myPictureTakerLauncher =
                registerForActivityResult( new ActivityResultContracts.StartActivityForResult()
                        ,new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == Activity.RESULT_OK)
                                { Intent data = result.getData();
                                    Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
                                    imgv.setImageBitmap(imgbitmap);
                                }
                                else if(result.getResultCode() == Activity.RESULT_CANCELED)
                                    Log.i(TAG, "User refused to capture a picture.");
                            }
                        } );

        Button cam = findViewById( R.id.start_camera);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    myPictureTakerLauncher.launch(takePictureIntent);
                }
            }
        });

       Intent fromMain = getIntent();
        EditText email = findViewById(R.id.editTextTextPersonName4);
        String input = fromMain.getStringExtra("email");
        email.setText(input);

        Button back = findViewById( R.id.intent_return_button);
        back.setOnClickListener(  click ->  { finish(); } );
    }
    @Override //screen is visible but not responding
    protected void onStart() {
        super.onStart();

        Log.e(TAG, "In functyion onStart");
    }

    @Override //screen is visible but not responding
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "In function onResume");
    }

    @Override //screen is visible but not responding
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "In function onPause");
    }

    @Override //not visible
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "In function onStop");
    }

    @Override  //garbage collected
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "In function onDestroy");
    }

}