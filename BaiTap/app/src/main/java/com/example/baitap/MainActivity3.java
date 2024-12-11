package com.example.baitap;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.BeerAdapter;
import com.example.baitap.databinding.ActivityMain3Binding;
import com.example.baitap.databinding.ActivityMainBinding;
import com.example.models.Beer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;

    BeerAdapter adapter;
    ArrayList<Beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loaddata();

        addEvents();



    }

    private void addEvents() {
        binding.lvBeers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Beer selectedBeer = beers.get(i);

                Dialog dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.thong_bao);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                ImageView imgBeer = dialog.findViewById(R.id.imgAnh);
                TextView txtName = dialog.findViewById(R.id.txtName);
                TextView txtPrice = dialog.findViewById(R.id.txtPrice);

                imgBeer.setImageResource(selectedBeer.getBeerThumb());
                txtName.setText(selectedBeer.getBeerName());
                txtPrice.setText(selectedBeer.getBeerPrice()+"");

                dialog.show();
            }
        });
    }

    private void loaddata() {
        adapter = new BeerAdapter(MainActivity3.this,R.layout.item_list,initData());
        binding.lvBeers.setAdapter(adapter);

    }

    private List<Beer> initData() {
        beers  = new ArrayList<>();
        beers.add(new Beer(1,"Beer 333",18000, R.drawable.beer333));
        beers.add(new Beer(2,"Beer HaNoi",18000,R.drawable.hanoi));
        beers.add(new Beer(3,"Beer Heineiken",18000,R.drawable.heineken));
        beers.add(new Beer(4,"Beer Larue",18000,R.drawable.larue));
        beers.add(new Beer(5,"Beer SaiGon",18000,R.drawable.saigon));
        beers.add(new Beer(6,"Beer Sapporo",18000,R.drawable.sapporo));
        beers.add(new Beer(7,"Beer Tiger",18000,R.drawable.tiger));
        return  beers;
    }
}