package com.example.activity_intent_ex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activity_intent_ex.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {


    ActivityMain4Binding binding;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvents();

    }

    private void addEvents() {
        binding.btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int numb = Integer.parseInt(binding.txtContent.getText().toString());
               double results = numb * numb;

                intent.putExtra("results", results);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    private  void getData(){
        intent = getIntent();
        binding.txtContent.setText(intent.getStringExtra("numb"));

    }

}