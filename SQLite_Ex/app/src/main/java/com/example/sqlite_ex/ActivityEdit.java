package com.example.sqlite_ex;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Product;
import com.example.sqlite_ex.databinding.ActivityEditBinding;

public class ActivityEdit extends AppCompatActivity {

    ActivityEditBinding binding;
    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());

        getData();

        addEvents();
    }


    private void getData() {
        Intent intent = getIntent();
        if (intent.hasExtra("data")) {
            p = (Product) intent.getSerializableExtra("data");
            binding.edtName.setText(p.getProductName());
            binding.edtPrice.setText(String.valueOf(p.getProductPrice()));
        } else {
            // Handle case where "data" extra is missing
            Toast.makeText(this, "Error: Product data not found!", Toast.LENGTH_SHORT).show();
        }
    }


    private void addEvents() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MainActivity.COL_NAME, binding.edtName.getText().toString());
                values.put(MainActivity.COL_PRICE,Double.parseDouble(binding.edtPrice.getText().toString()));


                int numbOFRows = MainActivity.db.update(MainActivity.TBL_NAME, values, MainActivity.COL_ID + "=?", new String[]{String.valueOf(p.getProductCode())});

                if(numbOFRows > 0){
                    Toast.makeText(ActivityEdit.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ActivityEdit.this, "FALL!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}