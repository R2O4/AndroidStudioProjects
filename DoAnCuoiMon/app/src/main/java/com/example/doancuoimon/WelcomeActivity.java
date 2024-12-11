package com.example.doancuoimon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);


    }


    public void register(View view){
        startActivities(new Intent[]{new Intent(WelcomeActivity.this, RegistrationActivity.class)});
    }

    public void dangnhap(View view) {
        startActivities(new Intent[]{new Intent(WelcomeActivity.this, LoginActivity.class)});
    }
}