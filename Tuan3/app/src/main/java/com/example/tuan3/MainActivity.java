package com.example.tuan3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tuan3.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvent();

        addEvent2();

        addEvent3();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnClick2) {
                Toast.makeText(MainActivity.this, "Valorant1", Toast.LENGTH_SHORT).show();
            }
            if (view.equals(binding.btnClick3)) {
                Toast.makeText(MainActivity.this, "Valorant2", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void sayHello(View view) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    public void addEvent() {
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Anonymous Listener", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnClick2.setOnClickListener(onClickListener);
        binding.btnClick3.setOnClickListener(onClickListener);
        //Activity as Listener
        binding.btnClick5.setOnClickListener(this);
        binding.btnClick6.setOnClickListener(this);

        //Explicit class as listener
        binding.btnClick8.setOnClickListener(new MyEvent());
        binding.btnClick9.setOnClickListener(new MyEvent());
    }

    //Activity on Listener

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClick5) {
            Toast.makeText(MainActivity.this, "Valorant05", Toast.LENGTH_SHORT).show();
        }
        if (view.equals(binding.btnClick6))
            Toast.makeText(MainActivity.this, "Valorant06", Toast.LENGTH_SHORT).show();
    }

    public class MyEvent implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.equals(binding.btnClick8)) {
                Toast.makeText(MainActivity.this, "Valorant8", Toast.LENGTH_SHORT).show();
            }
            if (view.equals(binding.btnClick9)) {
                Toast.makeText(MainActivity.this, "Valorant9", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addEvent3() {
        binding.btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addEvent2() {
        binding.btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xac nhan thoat");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("Ban co muon thoat app hay khong");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                Dialog dialog = builder.create();
//                 dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                binding.btnCustom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(MainActivity.this);
//                        dialog.setCanceledOnTouchOutside(false);
                        dialog.setContentView(R.layout.custom_dialog);
                        ImageView ok = dialog.findViewById(R.id.invOK);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });

                        ImageView cancel = dialog.findViewById(R.id.invCancel);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();

                    }
                });


            }
        });

        binding.btnBottomSheetDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_bottom_shee);

                LinearLayout camera = dialog.findViewById(R.id.layout_Camera);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Open Camera", Toast.LENGTH_SHORT).show();
                    }
                });


                LinearLayout image = dialog.findViewById(R.id.layout_Image);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Open Image", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow();
                dialog.show();
            }
        });

    }
}

