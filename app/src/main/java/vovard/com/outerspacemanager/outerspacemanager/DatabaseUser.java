package vovard.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUser extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MyDB.db";
    public static final String CURRENT_USER_TABLE_NAME = "Current_user";
    public static final String KEY_ID = "id";
    public static final String KEY_GAS = "gas";
    public static final String KEY_GAS_MODIFIER = "gasModifier";
    public static final String KEY_MINERALS = "minerals";
    public static final String KEY_MINERALS_MODIFIER =  "mineralsModifier";
    public static final String KEY_POINTS = "points";
    public static final String KEY_USERNAME = "username";

    private static final String CURRENT_USER_TABLE_CREATE = "CREATE TABLE " + CURRENT_USER_TABLE_NAME + " ("
            + KEY_ID + " TEXT, " +
            KEY_GAS + " TEXT, " +
            KEY_GAS_MODIFIER + " TEXT, " +
            KEY_MINERALS + " TEXT, " +
            KEY_MINERALS_MODIFIER + " TEXT, " +
            KEY_POINTS + " TEXT, " +
            KEY_USERNAME + " TEXT);";


    public DatabaseUser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CURRENT_USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CURRENT_USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CURRENT_USER_TABLE_NAME);
        onCreate(db);
    }
}
