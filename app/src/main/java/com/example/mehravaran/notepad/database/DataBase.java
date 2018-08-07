package com.example.mehravaran.notepad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mehravaran.notepad.ItemData.ItemDataNotes;

import java.util.ArrayList;
import java.util.Date;

public class DataBase extends SQLiteOpenHelper {
    public static final String DBNAME = "DBNAME";
    public static final String TABLENAME = "TABLENAME";
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    public static final String TEXT = "TEXT";
    public static final String DATE = "DATE";
    public static final boolean FAVORITE = false;


    public static final String Createtable = "CREATE TABLE " + TABLENAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT,"
            + TEXT + " TEXT,"
            + DATE + " TEXT,"
            + FAVORITE + " BOOLEAN);";

    public DataBase(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertData(ItemDataNotes data) {
        SQLiteDatabase Database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, data.getNotetitle());
        cv.put(TEXT, data.getNotedetails());
        cv.put(DATE, data.getNotelastmodifieddate());

        Database.insert(TABLENAME, null, cv);
        Database.close();
    }

    public ArrayList<ItemDataNotes> Showdata() {
        ArrayList<ItemDataNotes> datashow = new ArrayList<>();
        String select = "SELECT * FROM " + TABLENAME;
        SQLiteDatabase Database = this.getWritableDatabase();
        Cursor cursor = Database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ItemDataNotes itemData = new ItemDataNotes();
                itemData.setId(Integer.parseInt(cursor.getString(0)));
                itemData.setNotetitle(cursor.getString(1));
                itemData.setNotedetails(cursor.getString(2));
                itemData.setNotelastmodifieddate(cursor.getString(3));

                datashow.add(itemData);
            }
            while (cursor.moveToNext());
        }
        return datashow;
    }

    public void Delete(int id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLENAME, "id=" + id, null);
    }

    public void Update (ItemDataNotes data,int id)
    {
        SQLiteDatabase Database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, data.getNotetitle());
        cv.put(TEXT, data.getNotedetails());
        cv.put(DATE, data.getNotelastmodifieddate());

        Database.update(TABLENAME,cv,"id=" + id,null);
        Database.close();
    }

}
