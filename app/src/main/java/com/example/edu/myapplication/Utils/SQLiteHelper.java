package com.example.edu.myapplication.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{

    private Context mContext;
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "password text,"
            +"phonenum text)";
    public static final String CREATE_COLLECTION_NEWS = "create table Collection_News ("
            + "id integer primary key autoincrement,"
            + "news_title text,"
            + "news_date text,"
            + "news_author text,"
            + "news_picurl text,"
            + "news_url text)";


    String CREAT_TABLE="create table t_user(id INTEGER primary key  autoincrement,username varchar(50),password varchar(50))";
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_COLLECTION_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
