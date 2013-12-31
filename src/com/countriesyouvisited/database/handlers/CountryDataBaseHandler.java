package com.countriesyouvisited.database.handlers;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.countriesyouvisited.database.objects.CountryObject;

/**
 * @author horodysk
 */
public class CountryDataBaseHandler {
    /***/
    public static final String TABLE_NAME = "Countries";

    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";

    private static final String KEY_PARENT = "continentId";

    /***/
    public static String getCreateSQL() {
        return "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PARENT + " INTEGER)";
    }

    /***/
    public static List<CountryObject> getAll(int id, SQLiteDatabase db) {
        List<CountryObject> continentList = new ArrayList<CountryObject>();

        String[] keys = new String[] { KEY_ID, KEY_NAME, KEY_PARENT };
        String where = KEY_PARENT + "=?";
        String[] whatValue = new String[] { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, keys, where, whatValue, null, null, null, null);

        return getAll(continentList, cursor);
    }

    /***/
    public static List<CountryObject> getAll(String name, SQLiteDatabase db) {
        List<CountryObject> continentList = new ArrayList<CountryObject>();

        String[] keys = new String[] { KEY_ID, KEY_NAME, KEY_PARENT };
        String where = KEY_NAME + "=?";
        String[] whatValue = new String[] { name };
        Cursor cursor = db.query(TABLE_NAME, keys, where, whatValue, null, null, null, null);

        return getAll(continentList, cursor);
    }

    private static List<CountryObject> getAll(List<CountryObject> continentList, Cursor cursor) {
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int idValue = Integer.parseInt(cursor.getString(0));
                String nameValue = cursor.getString(1);
                int parentValue = Integer.parseInt(cursor.getString(2));
                CountryObject item = new CountryObject(idValue, nameValue, parentValue);
                continentList.add(item);
            }
            while (cursor.moveToNext());
        }

        return continentList;
    }

    /***/
    public static CountryObject get(int id, SQLiteDatabase db) {
        String[] values = new String[] { KEY_ID, KEY_NAME, KEY_PARENT };
        String where = KEY_ID + "=?";
        String[] whatValue = new String[] { String.valueOf(id) };
        Cursor cursor = db.query(TABLE_NAME, values, where, whatValue, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                String nameValue = cursor.getString(1);
                int parentValue = Integer.parseInt(cursor.getString(2));
                return new CountryObject(id, nameValue, parentValue);
            }
        }

        return null;
    }
}
