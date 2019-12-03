package com.chivan.qrcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ImageView qrCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrCodeView = findViewById(R.id.qr_code_view);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode);
                Bitmap bitMap1 = GenerateQrCode.addLogo(bitMap, BitmapFactory.decodeResource(getResources(), R.drawable.logo, null));
                qrCodeView.setImageBitmap(bitMap1);
            }
        });
    }
}
