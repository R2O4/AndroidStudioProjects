package com.example.activity_intent_ex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Models.Product;
import com.example.activity_intent_ex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.i("MainActivity: ", "onCreate()");

        addEvents();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), results
                -> {
                        if(results.getResultCode() == RESULT_OK && results.getData() != null){
                        double res = results.getData().getDoubleExtra("results", 0);
                        binding.txtContent.setText(String.valueOf(res));
            }
        });
    }

    private void addEvents() {
        binding.btnOpenAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        binding.btnOpenShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        binding.btnOpenAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(MainActivity.this, MainActivity3.class);
//Cach 1:
                //Dinh kem du lieu
//                intent.putExtra("Utils.NUMB", 10);
//                intent.putExtra("grades", 9.5f);
//                intent.putExtra("checked", true);
//                intent.putExtra("say", "Hello");

                //Cach 2:

                Bundle bundle = new Bundle();
                bundle.putInt(Utils.NUMB, 85);
                bundle.putFloat(Utils.GRADES, 8.2f);
                bundle.putBoolean("checked", true);
                bundle.putString("say","Welcome");


                Product p = new Product(1, "Gloves", 22000);
                bundle.putSerializable("myProduct",p);


                intent.putExtra("myData", bundle);
                startActivity(intent);
            }
        });

        binding.btnOpenAct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);

                intent.putExtra("numb",binding.edtNumber.getText().toString());

                activityResultLauncher.launch(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity: ", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity: ", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity: ", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity: ", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity: ", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity: ", "onDestroy");
    }
}