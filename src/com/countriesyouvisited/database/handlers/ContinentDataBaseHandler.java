package com.countriesyouvisited.database.handlers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.countriesyouvisited.database.objects.ContinentObject;

/**
 * @author horodysk
 */
public class ContinentDataBaseHandler {
    /***/
    public static final String TABLE_NAME = "Continents";

    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";

    /***/
    public static String getCreateSQL() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
    }

    /***/
    public static List<ContinentObject> getAll(SQLiteDatabase db) {
        List<ContinentObject> continentList = new ArrayList<ContinentObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int idValue = Integer.parseInt(cursor.getString(0));
                String nameValue = cursor.getString(1);
                ContinentObject item = new ContinentObject(idValue, nameValue);
                continentList.add(item);
            }
            while (cursor.moveToNext());
        }

        return continentList;
    }

    /***/
    public static ContinentObject get(int id, SQLiteDatabase db) {
        String[] values = new String[] { KEY_ID, KEY_NAME };
        String where = KEY_ID + "=?";
        String[] whatValue = new String[] { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, values, where, whatValue, null, null, null, null);

        return get(cursor);
    }

    /***/
    public static ContinentObject get(String name, SQLiteDatabase db) {
        String[] values = new String[] { KEY_ID, KEY_NAME };
        String where = KEY_NAME + "=?";
        String[] whatValue = new String[] { name };
        Cursor cursor = db.query(TABLE_NAME, values, where, whatValue, null, null, null, null);

        return get(cursor);
    }

    private static ContinentObject get(Cursor cursor) {
        if (cursor != null) {
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                int idValue = Integer.parseInt(cursor.getString(0));
                String nameValue = cursor.getString(1);
                return new ContinentObject(idValue, nameValue);
            }
        }

        return null;
    }
}
