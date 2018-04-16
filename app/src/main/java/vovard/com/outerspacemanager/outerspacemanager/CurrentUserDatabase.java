package vovard.com.outerspacemanager.outerspacemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import vovard.com.outerspacemanager.outerspacemanager.Entity.UserClass;

public class CurrentUserDatabase {
    // Database fields
    private SQLiteDatabase database;
    private DatabaseUser dbHelper;
    private String[] allColumns = {
            DatabaseUser.KEY_ID,
            DatabaseUser.KEY_GAS,
            DatabaseUser.KEY_GAS_MODIFIER,
            DatabaseUser.KEY_MINERALS,
            DatabaseUser.KEY_MINERALS_MODIFIER,
            DatabaseUser.KEY_POINTS,
            DatabaseUser.KEY_USERNAME};
    public CurrentUserDatabase(Context context) {
        dbHelper = new DatabaseUser(context, DatabaseUser.DATABASE_NAME, null, DatabaseUser.DATABASE_VERSION);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public Cursor getUsername(String user){
        Cursor cursor = database.query(DatabaseUser.CURRENT_USER_TABLE_NAME, allColumns,DatabaseUser.KEY_USERNAME +"=\""+user+"\"",null,null,null,null);
        return cursor;
    }

    public UserClass getUser(String user){

        Cursor cursor = database.query(DatabaseUser.CURRENT_USER_TABLE_NAME, allColumns,DatabaseUser.KEY_USERNAME +"=\""+user+"\"",null,null,null,null);
        cursor.moveToFirst();
        String result = cursor.getString(0);

        UserClass data = new UserClass(cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
        return data;
    }

    public void insertUser(String gas, String gas_modifier, String minerals, String mineral_modifier, String points, String username){
        ContentValues values = new ContentValues();
        UUID newID = UUID.randomUUID();
        values.put(DatabaseUser.KEY_GAS, gas);
        values.put(DatabaseUser.KEY_GAS_MODIFIER, gas_modifier);
        values.put(DatabaseUser.KEY_MINERALS, minerals);
        values.put(DatabaseUser.KEY_MINERALS_MODIFIER, mineral_modifier);
        values.put(DatabaseUser.KEY_POINTS,points);
        values.put(DatabaseUser.KEY_USERNAME,username);
        values.put(DatabaseUser.KEY_ID,newID.toString());
        database.insert(DatabaseUser.CURRENT_USER_TABLE_NAME,null,values);
    }
    public void updateUser(String gas, String gas_modifier, String minerals, String mineral_modifier, String points, String username){
        ContentValues values = new ContentValues();

        values.put(DatabaseUser.KEY_GAS, gas);
        values.put(DatabaseUser.KEY_GAS_MODIFIER, gas_modifier);
        values.put(DatabaseUser.KEY_MINERALS, minerals);
        values.put(DatabaseUser.KEY_MINERALS_MODIFIER, mineral_modifier);
        values.put(DatabaseUser.KEY_POINTS,points);
        database.update(DatabaseUser.CURRENT_USER_TABLE_NAME,values,DatabaseUser.KEY_USERNAME + " = \""+username+"\"",null);
    }

}
