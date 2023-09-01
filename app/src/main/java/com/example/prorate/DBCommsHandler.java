

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
public class DBCommsHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "comms";


    private static final int DB_VERSION = 1;


    private static final String TABLE_NAME = "comentarii";


    private static final String ID_COL = "id";

    private static final String CREATOR_COL = "creator";

    private static final String MATERIE_COL = "materie";

    private static final String NOTA_COL = "nota";

    private static final String DESCRIERE_COL = "descriere";

    public DBCommsHandler (Context context) {
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

    public void addNewComm(Comms comm) {

        SQLiteDatabase db = this.getWritableDatabase();
        //Instert:
        //ArrayList<String> participantsArray=new ArrayList<String>();
        //...Add value to inputArray

        ContentValues values = new ContentValues();

        values.put(CREATOR_COL, comm.getCreator());
        values.put(MATERIE_COL, comm.getMaterie());
        values.put(NOTA_COL, comm.getNota());
        values.put(DESCRIERE_COL, comm.getDescriere());
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Comms> readComms() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorComms = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Comms> commsArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorComms.moveToFirst()) {
            do {

                commsArrayList.add(new Comms(cursorComms.getString(1),
                        cursorComms.getString(2),
                        cursorComms.getFloat(3),
                        cursorComms.getString(4)));
            } while (cursorComms.moveToNext());

        }
        cursorComms.close();
        return commsArrayList;
    }

    public void updateComms(Comms comm,String descriere) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CREATOR_COL, comm.getCreator());
        values.put(MATERIE_COL, comm.getMaterie());
        values.put(NOTA_COL,comm.getNota());
        values.put(DESCRIERE_COL, comm.getDescriere());


        db.update(TABLE_NAME, values, "descriere=?", new String[]{descriere});
        db.close();
    }

    public void deleteComm(String comm) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "descriere=?", new String[]{comm});
        db.close();
    }
    public boolean existComm(String owner, String materie) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorComms = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean ok=false;
        //ArrayList<Comms> commsArrayList = new ArrayList<>();
        Comms comm=new Comms();
        // moving our cursor to first position.
        if (cursorComms.moveToFirst()) {
            do {
                if(cursorComms.getString(1).equals(owner)&&cursorComms.getString(2).equals(materie)){
                    ok=true;
                    break;
                }
            } while (cursorComms.moveToNext());

        }
        cursorComms.close();
        return ok;
    }
    public Float averageRating(String materie){
        Float averageRating=new Float(0);
        int count=0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorComms = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //ArrayList<Comms> commsArrayList = new ArrayList<>();
        Comms comm=new Comms();
        // moving our cursor to first position.
        if (cursorComms.moveToFirst()) {
            do {
                if(cursorComms.getString(2).equals(materie)){
                    averageRating=averageRating+cursorComms.getFloat(3);
                    count++;

                }
            } while (cursorComms.moveToNext());

        }
        cursorComms.close();
        averageRating=averageRating/count;
        return averageRating;
    }

    public Comms getComm(String owner, String materie) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorComms = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //ArrayList<Comms> commsArrayList = new ArrayList<>();
        Comms comm=new Comms();
        // moving our cursor to first position.
        if (cursorComms.moveToFirst()) {
            do {
                if(cursorComms.getString(1).equals(owner)&&cursorComms.getString(2).equals(materie)){
                comm=new Comms(cursorComms.getString(1),
                        cursorComms.getString(2),
                        cursorComms.getFloat(3),
                        cursorComms.getString(4));
                break;
                }
            } while (cursorComms.moveToNext());

        }
        cursorComms.close();
        return comm;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
