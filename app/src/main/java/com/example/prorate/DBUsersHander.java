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
public class DBUsersHander extends SQLiteOpenHelper {


    private static final String DB_NAME = "users2";


    private static final int DB_VERSION = 1;


    private static final String TABLE_NAME = "useri";


    private static final String ID_COL = "id";


    private static final String NUME_COL = "nume";


    private static final String PRENUME_COL = "prenume";


    private static final String USERNAME_COL = "username";


    private static final String PASSWORD_COL = "password";

    private static final String FACULTATE_COL = "facultate";

    private static final String AN_COL = "an";

    private static final String SPECIALIZARE_COL = "specializare";
    private static final String STATUS_COL = "status";

    private static final String MATERII_COL = "materii";

    public DBUsersHander (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRENUME_COL + " TEXT,"
                + NUME_COL + " TEXT,"
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL+ " TEXT,"
                + FACULTATE_COL+ " TEXT,"
                + AN_COL+" TEXT,"
                + SPECIALIZARE_COL+" TEXT,"
                + STATUS_COL + " TEXT,"
                + MATERII_COL  + " TEXT)";


        db.execSQL(query);
    }

    public void addNewUser(String nume, String prenume, String username, String parola,String facultate,int an,String specializare, int status,ArrayList<String> materiiArray) {

        SQLiteDatabase db = this.getWritableDatabase();

        Gson gson = new Gson();
        String materii=gson.toJson(materiiArray);
        ContentValues values = new ContentValues();

        values.put(NUME_COL, nume);
        values.put(PRENUME_COL, prenume);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, parola);
        values.put(FACULTATE_COL, facultate);
        values.put(AN_COL, an);
        values.put(SPECIALIZARE_COL,specializare);
        values.put(STATUS_COL, status);
        values.put(MATERII_COL, materii);
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Users> readUsers() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Users> usersArrayList = new ArrayList<>();
        ArrayList<String> materiiArray=new ArrayList<String>();

        //convert string to arrayList
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        //

        //

        // moving our cursor to first position.
        if (cursorUsers.moveToFirst()) {
            do {
                materiiArray=gson.fromJson(cursorUsers.getString(9), type);
                usersArrayList.add(new Users(cursorUsers.getString(3),
                        cursorUsers.getString(4),
                        cursorUsers.getString(2),
                        cursorUsers.getString(1),
                        cursorUsers.getInt(6),
                        cursorUsers.getInt(8),
                        cursorUsers.getInt(0),
                        cursorUsers.getString(5),
                        cursorUsers.getString(7),
                        materiiArray));
            } while (cursorUsers.moveToNext());

        }
        cursorUsers.close();
        return usersArrayList;
    }

    public void updateUser(Users user) {

        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        String materii=gson.toJson(user.getMaterii());
        ContentValues values = new ContentValues();

        values.put(NUME_COL, user.getLastName());
        values.put(PRENUME_COL, user.getFirstName());
        values.put(USERNAME_COL, user.getUsername());
        values.put(PASSWORD_COL, user.getPassword());
        values.put(FACULTATE_COL, user.getFacultate());
        values.put(AN_COL, user.getAn());
        values.put(SPECIALIZARE_COL,user.getSpecializare());
        values.put(STATUS_COL, user.getStatus());
        values.put(MATERII_COL, materii);

        db.update(TABLE_NAME, values, "username=?", new String[]{user.getUsername()});
        db.close();
    }

    public void deleteUser(String useName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "username=?", new String[]{useName});
        db.close();
    }

    public Users getUserDB(String username){
        Users user = new Users("Dummy", "Dummy", "Dummy", "Dummy",0,0,0,"Dummy","Dummy",new ArrayList<>());
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);


        ArrayList<String> materiiArray=new ArrayList<String>();

        //convert string to arrayList
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        //

        //

        // moving our cursor to first position.
        if (cursorUsers.moveToFirst()) {
            do {
                if(username.equals(cursorUsers.getString(3))) {
                    materiiArray = gson.fromJson(cursorUsers.getString(9), type);
                    user=new Users(cursorUsers.getString(3),
                            cursorUsers.getString(4),
                            cursorUsers.getString(2),
                            cursorUsers.getString(1),
                            cursorUsers.getInt(6),
                            cursorUsers.getInt(8),
                            cursorUsers.getInt(0),
                            cursorUsers.getString(5),
                            cursorUsers.getString(7),
                            materiiArray);
                }
            } while (cursorUsers.moveToNext());

        }
        cursorUsers.close();


        return user;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
