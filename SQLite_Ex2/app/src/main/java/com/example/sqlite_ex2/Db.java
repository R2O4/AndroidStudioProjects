package com.example.sqlite_ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    public static final String DB_NAME = "product.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Product";
    public static final String COL_CODE = "ProductCode";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public Db(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ( " + COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(50), " + COL_PRICE + "REAL)" ;
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(sqLiteDatabase);
    }

    //SELECT ...
    public Cursor queryData(String sql){
        SQLiteDatabase db = getReadableDatabase();
         return  db.rawQuery(sql, null);
    }

    //INSERT,UPDATE,DELETE,...
    public void exeSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public int getNumOfRows(){
        Cursor cursor   = queryData("SELECT * FROM " + TBL_NAME);
        int rows = cursor.getCount();
        cursor.close();
        return rows;
    }

    public void generateSampleData(){
        if(getNumOfRows() == 0)
        {
            try{
                exeSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Heineken', 1900)");
                exeSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Tiger', 1900)");
                exeSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Saporo', 2200)");
                exeSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Blanc 1644', 2500)");
            }catch (Exception e){
                Log.e("Error: ", e.getMessage().toString());
            }
        }
    }

}
