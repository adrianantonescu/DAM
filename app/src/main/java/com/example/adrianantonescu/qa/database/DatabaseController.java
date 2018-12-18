package com.example.adrianantonescu.qa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseController extends SQLiteOpenHelper implements DatabaseConstants {

    private static DatabaseController controller;

    public DatabaseController(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseController getInstance(Context context){
        if (controller == null){
            synchronized (DatabaseController.class){
                if (controller == null) {
                    controller = new DatabaseController(context);
                }
            }
        }
        return controller;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TEACHER_PROFILE);
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT_PROFILE);
        sqLiteDatabase.execSQL(CREATE_TABLE_COURSES);
        sqLiteDatabase.execSQL(CREATE_TABLE_DISTRIBUTION);
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_TEACHER_PROFILE);
        sqLiteDatabase.execSQL(DROP_TABLE_STUDENT_PROFILE);
        sqLiteDatabase.execSQL(DROP_TABLE_COURSES);
        sqLiteDatabase.execSQL(DROP_TABLE_DISTRIBUTION);
        sqLiteDatabase.execSQL(DROP_TABLE_STUDENT_SCORES);
        onCreate(sqLiteDatabase);
    }
}
