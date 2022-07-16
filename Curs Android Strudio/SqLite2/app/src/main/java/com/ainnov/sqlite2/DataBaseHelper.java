package com.ainnov.sqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID = "ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_AGE = "USER_AGE";
    public static final String ACTIVE_USER = "ACTIVE_USER";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String createTable = "CREATE TABLE " + USER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT, " + USER_AGE + " INT, " + ACTIVE_USER + " BOOL)";
      db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean adaugaUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME,userModel.getNume());
        cv.put(USER_AGE,userModel.getVarsta());
        cv.put(ACTIVE_USER,userModel.isActive());

        long insert = db.insert(USER_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

   public List<UserModel> getUsers(){
        List<UserModel> listaUseri = new ArrayList<>();
        String query = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                int userId = cursor.getInt(0);
                String userNume = cursor.getString(1);
                int userAge = cursor.getInt(2);
                boolean active = cursor.getInt(3) == 1 ? true :false;
                UserModel userModel = new UserModel(userId,userNume,userAge,active);
                listaUseri.add(userModel);

            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return listaUseri;
   }

   public boolean deleteUser(UserModel userModel){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "DELETE FROM " + USER_TABLE + " WHERE " + ID + " = " +userModel.getId();
       Cursor cursor = db.rawQuery(query, null);
       return  cursor.moveToFirst();
   }

}
