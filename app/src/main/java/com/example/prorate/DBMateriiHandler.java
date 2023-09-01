

package com.example.prorate;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
public class DBMateriiHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "materii";


    private static final int DB_VERSION = 1;


    private static final String TABLE_NAME = "materii";


    private static final String ID_COL = "id";

    private static final String CREATOR_COL = "creator";

    private static final String MATERIE_COL = "materie";

    private static final String NOTA_COL = "nota";

    private static final String DESCRIERE_COL = "descriere";

    public DBMateriiHandler (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CREATOR_COL + " TEXT,"
                + MATERIE_COL + " TEXT,"
                + NOTA_COL + " TEXT,"
                + DESCRIERE_COL+ " TEXT)";


        db.execSQL(query);
    }

    public void addNewClass(Classes comm) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(CREATOR_COL, comm.getCreator());
        values.put(MATERIE_COL, comm.getMaterie());
        values.put(NOTA_COL, comm.getNota());
        values.put(DESCRIERE_COL, comm.getDescriere());
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Classes> readClasses() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorComms = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Classes> commsArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorComms.moveToFirst()) {
            do {

                commsArrayList.add(new Classes(cursorComms.getString(1),
                        cursorComms.getString(2),
                        cursorComms.getFloat(3),
                        cursorComms.getString(4)));
            } while (cursorComms.moveToNext());

        }
        cursorComms.close();
        return commsArrayList;
    }

    public void updateClasses(Classes comm) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CREATOR_COL, comm.getCreator());
        values.put(MATERIE_COL, comm.getMaterie());
        values.put(NOTA_COL,comm.getNota());
        values.put(DESCRIERE_COL, comm.getDescriere());


        db.update(TABLE_NAME, values, "descriere=?", new String[]{comm.getDescriere()});
        db.close();
    }
    public void updateRating(Float rating,String materie)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorComms = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ContentValues values = new ContentValues();

        if (cursorComms.moveToFirst()) {
            do {

                if(cursorComms.getString(2).equals(materie)){
                    //rating=rating+cursorComms.getFloat(3);
                    values.put(NOTA_COL,rating);
                    break;
                }
            } while (cursorComms.moveToNext());

        }
        cursorComms.close();


        db.update(TABLE_NAME, values, "materie=?", new String[]{materie});
        db.close();
    }
    public void deleteClass(String comm) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "descriere=?", new String[]{comm});
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
