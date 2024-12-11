package com.example.view_p1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_p1.databinding.ActivityMain2Binding;


public class MainActivity2 extends Activity {

    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void   addEvents(){
       binding.btnVote2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String s = "";
               if(binding.radRatTot.isChecked())
                    s += binding.radRatTot.getText().toString();
               else if(binding.radTot.isChecked())
                   s += binding.radTot.getText().toString();
               else if(binding.radTuongdoitot.isChecked())
                   s += binding.radTuongdoitot.getText().toString();
                else if(binding.radTuyetVoi.isChecked())
                    s += binding.radTuyetVoi.getText().toString();

                binding.txtVote2.setText("Ban chon: " + s);
           }
       });
    }

}
