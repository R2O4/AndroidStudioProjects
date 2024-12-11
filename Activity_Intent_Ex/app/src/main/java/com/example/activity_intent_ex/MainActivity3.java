package com.example.activity_intent_ex;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.Models.Product;
import com.example.activity_intent_ex.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();



    }

    private void getData(){
        Intent intent = getIntent();

//        int numb = intent.getIntExtra("Utils.NUMB", 0);
//        float grades = intent.getFloatExtra("grades",0f);
//        boolean checked = intent.getBooleanExtra("checked", false);
//        String say = intent.getStringExtra("say");

        Bundle bundle = intent.getBundleExtra("myData");
        assert bundle != null;
        int numb = bundle.getInt(Utils.NUMB, 0);
        float grades = bundle.getFloat(Utils.GRADES, 0.0f);
        boolean checked = bundle.getBoolean("checked",false);
        String say = bundle.getString("say");

        Product p = (Product) bundle.getSerializable("myProduct");

        binding.txtContent.setText("");
        binding.txtContent.append("Number: " + numb +"\n");
        binding.txtContent.append("Grades: " + grades +"\n");
        binding.txtContent.append("Checked: " + checked +"\n");
        binding.txtContent.append("Say: " + say + "\n");
    assert p != null;
        binding.txtContent.append("Product: " + p.getProductName() + " - " + p.getProductPrice());
    }
}