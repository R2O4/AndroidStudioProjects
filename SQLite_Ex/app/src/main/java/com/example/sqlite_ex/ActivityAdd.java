package com.example.sqlite_ex;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlite_ex.databinding.ActivityAddBinding;

public class ActivityAdd extends AppCompatActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MainActivity.COL_NAME, binding.edtName.getText().toString());
                values.put(MainActivity.COL_PRICE, Double.parseDouble(binding.edtPrice.getText().toString()));
               long numOfRows = MainActivity.db.insert(MainActivity.TBL_NAME, null, values);
                if(numOfRows > 0){
                    Toast.makeText(ActivityAdd.this, "Success!", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(ActivityAdd.this, "FALL!", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}