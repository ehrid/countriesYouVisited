package com.countriesyouvisited.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author horodysk
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "VisitedCountryDataBase";

    // Contacts table name
    private static final String TABLE_NAME = "Visited";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";

    private static final String KEY_YEAR = "year";

    private static final String KEY_MONTH = "month";

    /***/
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getDiaryPagesCreateTableSQL());
    }

    private String getDiaryPagesCreateTableSQL() {
        return "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_YEAR + " TEXT," + KEY_MONTH +
            " TEXT" + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * Adding new page
     */
    public void addVisitedCountry(CountryObject country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, country.getName());
        values.put(KEY_YEAR, country.getYear());
        values.put(KEY_MONTH, country.getMonth());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Getting single page
     */
    public CountryObject getVisitedCountry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        CountryObject page = new CountryObject(id, "", 0, 0);

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME, KEY_YEAR }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null,
            null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                page.setName(cursor.getString(1));
                page.setYear(Integer.parseInt(cursor.getString(2)));
                page.setMonth(Integer.parseInt(cursor.getString(2)));
            }
            else {
                addVisitedCountry(page);
            }
        }

        return page;
    }

    /**
     * Getting all pages
     */
    public List<CountryObject> getAllVisitedCountries() {
        List<CountryObject> diary = new ArrayList<CountryObject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CountryObject page = new CountryObject(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor
                    .getString(2)), Integer.parseInt(cursor.getString(3)));
                diary.add(page);
            }
            while (cursor.moveToNext());
        }

        return diary;
    }

    /**
     * Getting pages count
     */
    public int getVisitedCountryCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    /**
     * Updating single country
     */
    public int updateVisitedCountry(CountryObject country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, country.getName());
        values.put(KEY_YEAR, country.getYear());
        values.put(KEY_MONTH, country.getMonth());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(country.getId()) });
    }

    /**
     * Deleting single country
     */
    public void deleteVisitedCountry(CountryObject country) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(country.getId()) });
        db.close();
    }
}
