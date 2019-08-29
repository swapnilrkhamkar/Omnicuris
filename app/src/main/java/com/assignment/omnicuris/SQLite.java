package com.assignment.omnicuris;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "answersManager";
    private static final String TABLE_ANSWERS = "answers";
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";

    public SQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUESTION + " TEXT,"
                + KEY_ANSWER + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        onCreate(sqLiteDatabase);
    }

    public void addAnswer(String q, String a) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, q);
        values.put(KEY_ANSWER, a);

        db.insert(TABLE_ANSWERS, null, values);
        db.close();
    }

    public List<String> getAllAnswers() {
        List<String> stringArrayList = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_ANSWERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String a = cursor.getString(2);
                stringArrayList.add(a);
            } while (cursor.moveToNext());
        }

        // return contact list
        return stringArrayList;
    }
}
