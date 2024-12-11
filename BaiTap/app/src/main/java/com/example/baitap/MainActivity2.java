package com.example.baitap;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baitap.databinding.ActivityMain2Binding;
import com.example.baitap.databinding.ActivityMainBinding;
import com.example.models.Product;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;

    ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intAdapter();
        addEvents();

    }

    private void intAdapter() {
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1);
        binding.lvnProduct.setAdapter(adapter);
    }

    private void addEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add product to listview
                String name = binding.edtProductName.getText().toString();
                double price = Double.parseDouble(binding.edtProductPrice.getText().toString());
                Product p = new Product(name,price);

                adapter.add(p);

            }
        });

        binding.lvnProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selectedProduct = adapter.getItem(i);
                Toast.makeText(MainActivity2.this, selectedProduct.getProductName(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvnProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
               Product selectedProduct = adapter.getItem(i);
               adapter.remove(selectedProduct);
               adapter.notifyDataSetChanged();
               return true;
           }
        });
    }
}