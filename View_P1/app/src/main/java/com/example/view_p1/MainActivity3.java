//package com.example.view_p1;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//
//import com.example.view_p1.databinding.ActivityMain2Binding;
//import com.example.view_p1.databinding.ActivityMain3Binding;
//public class MainActivity3 extends Activity {
//
//    ActivityMain3Binding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
//        binding = ActivityMain3Binding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        addEvents();
//    }
//
//    private  void addEvents(){
//        binding.btnVote2.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view)
//           {
//               if(binding.imvPhoto.getTag() == null){
//                   bidnding.imvPhoto.setImageResource(R.drawablw.icon_ios);
//                   bindding.imvPhoto.setTag("ios");
//               } else if(binding.imvPhoto.getTag() == "ios") {
//               bindding.imvPhoto.setImageResource(R.drawable.icon_android;
//               binding.imvPhoto.setTag("Android");
//           }
//        }
//        });
//               binding.btnClose.setOnClickListener(new View.OnClickListener()){
//                   @Override
//                           public void OnClick(View view){
//                           closeContextMenu();
//            }
//        }
//    }
//
//}
