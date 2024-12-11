package com.example.view_p1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/*import androidx.activity.EdgeToEdge;*/
import androidx.appcompat.app.AppCompatActivity;
import com.example.view_p1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String content = "Ban chon: ";
                    if (binding.chkFlim.isChecked())
                        content += binding.chkFlim.getText().toString() + ", ";
                    if (binding.chkFTPplay.isChecked())
                        content += binding.chkFTPplay.getText().toString() + ", ";
                    if (binding.chkYoutube.isChecked())
                        content += binding.chkYoutube.getText().toString() + ", ";

                    binding.txtVote.setText(content.substring(0, content.length() - 2));
            }
        });
    }
}