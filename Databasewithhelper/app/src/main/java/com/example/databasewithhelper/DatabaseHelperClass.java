package com.example.databasewithhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Employee.db";
    private static final String TABLE_NAME = "Employee_Table";
    private static final String q1 = "CREATE TABLE "+ TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , SURNAME TEXT , MARKS INTEGER)";
    private static final String q2 = "DROP TABLE IF EXISTS "+ TABLE_NAME ;

    public DatabaseHelperClass(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(q1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(q2);
        onCreate(db);
    }

    public boolean insertData(String name,String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("SURNAME",surname);
        contentValues.put("MARKS",marks);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }

    public boolean updateData(String id,String name,String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("NAME",name);
        contentValues.put("SURNAME",surname);
        contentValues.put("MARKS",marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[]{id});
        return true;
    }

}
