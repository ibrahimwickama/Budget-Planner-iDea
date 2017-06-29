package com.wickerlabs.exp_tr.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WickerLabs on 6/17/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    private static final String COLUMN_UNAME= "username";
    public static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE= "phone";
    private static final String COLUMN_BUDGET= "budget";

    private static final String COLUMN_TRANSPORT= "transport";
    private static final String COLUMN_BILLS= "bills";
    private static final String COLUMN_SHOPPING= "shopping";
    private static final String COLUMN_FOOD= "food";
    public static final String COLUMN_CREDITS = "credits";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_UNAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PHONE + " TEXT,"
            + COLUMN_BUDGET + " INTEGER,"
            + COLUMN_TRANSPORT + " INTEGER,"
            + COLUMN_BILLS + " INTEGER,"
            + COLUMN_SHOPPING + " INTEGER,"
            + COLUMN_FOOD + " INTEGER,"
            + COLUMN_CREDITS + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    public void addUser(String username,String email,String phone,String budget) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_UNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_BUDGET, budget);

        values.put(COLUMN_TRANSPORT, 0);
        values.put(COLUMN_BILLS, 0);
        values.put(COLUMN_SHOPPING, 0);
        values.put(COLUMN_FOOD, 0);
        values.put(COLUMN_CREDITS, 0);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "select * from "+USER_TABLE;
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    public Cursor getAllExpData(){
        SQLiteDatabase db= this.getReadableDatabase();
        String query= "select * from " + USER_TABLE;
        Cursor cursor= db.rawQuery(query, null);
        return cursor;
    }

    public void addExpense(String selectedExp, int expCash){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();

        if(selectedExp == "Transport"){
            values.put(COLUMN_TRANSPORT, expCash);
            //String query= "update " + USER_TABLE + " set " + COLUMN_TRANSPORT + " = " + expCash + " where " + COLUMN_ID + " = 1";
            String query= "update " + USER_TABLE + " set " + COLUMN_TRANSPORT + " = " + COLUMN_TRANSPORT + " + " + expCash + " where " + COLUMN_ID + " = 1";
            db.execSQL(query);

        }else if(selectedExp == "Bills"){
            values.put(COLUMN_BILLS, expCash);
            //String query= "update " + USER_TABLE + " set " + COLUMN_BILLS + " = " + expCash + " where " + COLUMN_ID + " = 1";
            String query= "update " + USER_TABLE + " set " + COLUMN_BILLS + " = " + COLUMN_BILLS + " + " + expCash + " where " + COLUMN_ID + " = 1";
            db.execSQL(query);

        }else if(selectedExp == "Shopping"){
            values.put(COLUMN_SHOPPING, expCash);
            //String query= "update " + USER_TABLE + " set " + COLUMN_SHOPPING + " = " + expCash + " where " + COLUMN_ID + " = 1";
            String query= "update " + USER_TABLE + " set " + COLUMN_SHOPPING + " = " + COLUMN_SHOPPING + " + " + expCash + " where " + COLUMN_ID + " = 1";
            db.execSQL(query);

        }else if(selectedExp == "Food"){
            values.put(COLUMN_FOOD, expCash);
            //String query= "update " + USER_TABLE + " set " + COLUMN_FOOD + " = " + expCash + " where " + COLUMN_ID + " = 1";
            String query= "update " + USER_TABLE + " set " + COLUMN_FOOD + " = " + COLUMN_FOOD + " + " + expCash + " where " + COLUMN_ID + " = 1";
            db.execSQL(query);

        }else if(selectedExp == "Credits"){
            values.put(COLUMN_CREDITS, expCash);
            //String query= "update " + USER_TABLE + " set " + COLUMN_CREDITS + " = " + expCash + " where " + COLUMN_ID + " = 1";
            String query= "update " + USER_TABLE + " set " + COLUMN_CREDITS + " = " + COLUMN_CREDITS + " + " + expCash + " where " + COLUMN_ID + " = 1";
            db.execSQL(query);

        }

        db.close();
    }

    public void updateBudget(int newBudgetMoney){
        SQLiteDatabase db= this.getReadableDatabase();
        String query= "update " + USER_TABLE + " set " + COLUMN_BUDGET + " = " + newBudgetMoney + " where " + COLUMN_ID + " = 1";
        db.execSQL(query);
    }

    public void deleteAll(){
        SQLiteDatabase db= this.getWritableDatabase();
        String query= "delete from " + USER_TABLE + " where " + COLUMN_ID + " = 1";
        //String query= "drop table " + USER_TABLE;
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

}

