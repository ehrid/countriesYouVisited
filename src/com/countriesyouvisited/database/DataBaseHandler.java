package com.countriesyouvisited.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.countriesyouvisited.database.handlers.ContinentDataBaseHandler;
import com.countriesyouvisited.database.handlers.CountryDataBaseHandler;
import com.countriesyouvisited.database.handlers.RegionDataBaseHandler;
import com.countriesyouvisited.database.handlers.VisitedDataBaseHandler;
import com.countriesyouvisited.database.objects.ContinentObject;
import com.countriesyouvisited.database.objects.CountryObject;
import com.countriesyouvisited.database.objects.RegionObject;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CountriesYouVisitedDB";

    /***/
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ContinentDataBaseHandler.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CountryDataBaseHandler.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RegionDataBaseHandler.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VisitedDataBaseHandler.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContinentDataBaseHandler.getCreateSQL());
        db.execSQL(CountryDataBaseHandler.getCreateSQL());
        db.execSQL(RegionDataBaseHandler.getCreateSQL());
        db.execSQL(VisitedDataBaseHandler.getCreateSQL());
    }

    // CONTINENTS

    /***/
    public List<ContinentObject> getAllContinents() {
        return ContinentDataBaseHandler.getAll(this.getWritableDatabase());
    }

    /***/
    public List<String> getAllContinentsName() {
        List<String> names = new ArrayList<String>();
        List<ContinentObject> objects = ContinentDataBaseHandler.getAll(this.getWritableDatabase());

        for (ContinentObject ob : objects) {
            names.add(ob.getName());
        }
        return names;
    }

    /***/
    public ContinentObject getContinent(int id) {
        return ContinentDataBaseHandler.get(id, this.getWritableDatabase());
    }

    // COUNTRIES

    /**
     * Return all countries placed on selected continent
     */
    public List<CountryObject> getAllCountries(int id) {
        return CountryDataBaseHandler.getAll(id, this.getWritableDatabase());
    }

    /***/
    public List<String> getAllCountriesName(String name) {
        List<String> names = new ArrayList<String>();
        List<CountryObject> objects = CountryDataBaseHandler.getAll(name, this.getWritableDatabase());

        for (CountryObject ob : objects) {
            names.add(ob.getName());
        }
        return names;
    }

    /***/
    public CountryObject getCountry(int id) {
        return CountryDataBaseHandler.get(id, this.getWritableDatabase());
    }

    // REGIONS

    /**
     * Return all regions placed on selected country
     */
    public List<RegionObject> getAllRegions(int id) {
        return RegionDataBaseHandler.getAll(id, this.getWritableDatabase());
    }

    /***/
    public List<String> getAllRegionsName(String name) {
        List<String> names = new ArrayList<String>();
        List<RegionObject> objects = RegionDataBaseHandler.getAll(name, this.getWritableDatabase());

        for (RegionObject ob : objects) {
            names.add(ob.getName());
        }
        return names;
    }

    /***/
    public RegionObject getRegion(int id) {
        return RegionDataBaseHandler.get(id, this.getWritableDatabase());
    }

    /***/
    public RegionObject getRegion(String name) {
        return RegionDataBaseHandler.get(name, this.getWritableDatabase());
    }

    // VISITED REGIONS

    /***/
    public void addVisitedRegion(VisitedRegionObject object) {
        VisitedDataBaseHandler.addVisitedCountry(object, this.getWritableDatabase());
    }

    /***/
    public List<VisitedRegionObject> getAllVisitedRegions() {
        return VisitedDataBaseHandler.getAll(this.getWritableDatabase());
    }

    /***/
    public int getVisitedRegionCount() {
        return VisitedDataBaseHandler.getCount(this.getWritableDatabase());
    }

    /***/
    public void deleteVisitedRegion(VisitedRegionObject object) {
        VisitedDataBaseHandler.delete(object.getId(), this.getWritableDatabase());
    }
}
