package com.countriesyouvisited.database;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.countriesyouvisited.database.handlers.VisitedDataBaseHandler;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class UserDataBaseHandler {

    /***/
    public void createUserDatabase(SQLiteDatabase db) {
        db.execSQL(VisitedDataBaseHandler.getCreateSQL());
    }

    // VISITED REGIONS

    /***/
    public void addVisitedRegion(VisitedRegionObject object, SQLiteDatabase db) {
        VisitedDataBaseHandler.addVisitedCountry(object, db);
    }

    /***/
    public List<VisitedRegionObject> getAllVisitedRegions(SQLiteDatabase db) {
        return VisitedDataBaseHandler.getAll(db);
    }

    /***/
    public int getVisitedRegionCount(SQLiteDatabase db) {
        return VisitedDataBaseHandler.getCount(db);
    }

    /***/
    public void deleteVisitedRegion(VisitedRegionObject object, SQLiteDatabase db) {
        VisitedDataBaseHandler.delete(object.getId(), db);
    }
}
