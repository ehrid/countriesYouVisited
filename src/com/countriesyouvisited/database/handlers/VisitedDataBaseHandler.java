package com.countriesyouvisited.database.handlers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class VisitedDataBaseHandler {
    /***/
    public static final String TABLE_NAME = "Visited";

    private static final String KEY_ID = "id";

    private static final String KEY_RELATED = "regionId";

    private static final String KEY_YEAR = "year";

    private static final String KEY_MONTH = "month";

    /***/
    public static String getCreateSQL() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_RELATED + " INTEGER," + KEY_YEAR +
            " INTEGER," + KEY_MONTH + " INTEGER)";
    }

    /**
     * Adding new page
     */
    public static void addVisitedCountry(VisitedRegionObject object, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_RELATED, object.getRegionId());
        values.put(KEY_YEAR, object.getYear());
        values.put(KEY_MONTH, object.getMonth());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    /***/
    public static List<VisitedRegionObject> getAll(SQLiteDatabase db) {
        List<VisitedRegionObject> continentList = new ArrayList<VisitedRegionObject>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int idValue = Integer.parseInt(cursor.getString(0));
                int relatedValue = Integer.parseInt(cursor.getString(1));
                int monthValue = Integer.parseInt(cursor.getString(2));
                int yearValue = Integer.parseInt(cursor.getString(3));
                VisitedRegionObject item = new VisitedRegionObject(idValue, relatedValue, monthValue, yearValue);
                continentList.add(item);
            }
            while (cursor.moveToNext());
        }

        return continentList;
    }

    /***/
    public static int getCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    /***/
    public static void delete(int id, SQLiteDatabase db) {
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
}
