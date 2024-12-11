package com.example.sqlite_ex;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Product;
import com.example.sqlite_ex.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public static final String DB_NAME = "product_db.db";
    public static final String DB_FOLDER = "databases";
    public static final String TBL_NAME = "Product";
    public static final String COL_ID = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public static SQLiteDatabase db = null;
    ArrayAdapter<Product>   adapter;
    ArrayList<Product> products;

    Product selectedProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareDB();

        OpenDB();
//        loadDataFromDB();

        registerForContextMenu(binding.lvProduct);
        addEvents();

    }

    private void addEvents() {
        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = adapter.getItem(i);

                return false;
            }
        });
    }

    private void OpenDB() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }

    private void loadDataFromDB() {
        products = new ArrayList<>();

//        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME,null);
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " Where " + COL_ID + ">?", new String[]{"3"}); CÁCH 1

        //Load data using Query CÁCH 2
//        Cursor cursor  = db.query( TBL_NAME, null, COL_PRICE + ">=?", new String[]{"15000"}, null,null,null);
        //Lấy hết tên
        Cursor cursor = db.query(TBL_NAME, null,null,null,null,null,null);


        Product p;
        while (cursor.moveToNext()){
            p = new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2));
            products.add(p);
        }
        cursor.close();

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,products);
        binding.lvProduct.setAdapter(adapter);

    }

    private void prepareDB() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            if(CopyDBFromAsset()){
                Toast.makeText(MainActivity.this,
                        "Copy database successful!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,
                        "Copy database fail!", Toast.LENGTH_LONG).show();
            }
        }
    }

//    private boolean CoppyDataBase(){
//        return true;
//    }

    private boolean CopyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + "/" + DB_FOLDER + "/" + DB_NAME;
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + "/" + DB_FOLDER);
            if(!f.exists()){
                f.mkdir();
            }

            OutputStream outputStream = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                outputStream = Files.newOutputStream(Paths.get(dbPath));
            }

            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush(); outputStream.close(); inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    //-----------------------------------------------------MENU----------------------------------------------------------//


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd){
            //Open Activity - Add
            Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnUpdate)
        {
            //Update
//            Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ActivityEdit.class);
            //Kem du lieu

            if(selectedProduct != null)
            {
                intent.putExtra("data", selectedProduct);
                onResume();
                loadDataFromDB();
                startActivity(intent);
            }

        }
        if(item.getItemId() == R.id.mnDelete){
            //Delete
            if(selectedProduct != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xac nhan xoa san pham!");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Ban co chac muon xoa san pham: "+ selectedProduct.getProductName() + "?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        int numdOFRows = db.delete(TBL_NAME, COL_ID + "=?", new String[]{String.valueOf(selectedProduct.getProductCode())});
                        if(numdOFRows > 0 ){
                            Toast.makeText(MainActivity.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
                            loadDataFromDB();
                        } else {
                            Toast.makeText(MainActivity.this, "FALL!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                Dialog dialog = builder.create();
                dialog.show();

            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDB();
    }
}