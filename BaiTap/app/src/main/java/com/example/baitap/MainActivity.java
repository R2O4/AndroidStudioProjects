package com.example.baitap;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayListDrinks;

    ArrayList<String> arrayListFoods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());

        loadData();

        addEvents();
    }

    private void loadData() {
        String[] drinks = {"Coca-mala", "Sting", "Pepsi", "C2", "Coffee", "Milk", "Red-Bull", "SaiGon Beer", "Sapporo", "Beer 333", "Blanc 1644", "Tiger", "Mirinda","Mirinda1","Mirinda2"};

        arrayListDrinks = new ArrayList<>();
        arrayListDrinks.addAll(Arrays.asList(drinks));

        arrayListFoods = new ArrayList<>();
        arrayListFoods.addAll(Arrays.asList(getResources().getStringArray(R.array.foods)));


//        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListDrinks);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListFoods);

        binding.lvDrinks.setAdapter(adapter);
    }

    public void addEvents() {
        binding.lvDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapter.getItem(i);
                Toast.makeText(MainActivity.this, selectedItem, Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvDrinks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.remove(adapter.getItem(i));
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}