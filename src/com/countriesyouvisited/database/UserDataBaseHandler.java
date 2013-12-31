package com.countriesyouvisited.database;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.countriesyouvisited.database.handlers.VisitedDataBaseHandler;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class UserDataBaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CountriesYouVisitedUserData";

    /***/
    public UserDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VisitedDataBaseHandler.getCreateSQL());
    }

    // VISITED REGIONS

    /***/
    public void addVisitedRegion(VisitedRegionObject object) {
        VisitedDataBaseHandler.addVisitedCountry(object, getWritableDatabase());
    }

    /***/
    public List<VisitedRegionObject> getAllVisitedRegions() {
        return VisitedDataBaseHandler.getAll(getWritableDatabase());
    }

    /***/
    public int getVisitedRegionCount() {
        return VisitedDataBaseHandler.getCount(getWritableDatabase());
    }

    /***/
    public void deleteVisitedRegion(VisitedRegionObject object) {
        VisitedDataBaseHandler.delete(object.getId(), getWritableDatabase());
    }
}
