package com.countriesyouvisited.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.countriesyouvisited.database.handlers.ContinentDataBaseHandler;
import com.countriesyouvisited.database.handlers.CountryDataBaseHandler;
import com.countriesyouvisited.database.handlers.RegionDataBaseHandler;
import com.countriesyouvisited.database.objects.ContinentObject;
import com.countriesyouvisited.database.objects.CountryObject;
import com.countriesyouvisited.database.objects.RegionObject;

/**
 * @author horodysk
 */
public class SystemDataBaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "CountriesYouVisitedSystemData";

    /***/
    public SystemDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContinentDataBaseHandler.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CountryDataBaseHandler.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RegionDataBaseHandler.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContinentDataBaseHandler.getCreateSQL());
        db.execSQL(CountryDataBaseHandler.getCreateSQL());
        db.execSQL(RegionDataBaseHandler.getCreateSQL());
    }

    // CONTINENTS

    /***/
    public List<ContinentObject> getAllContinents() {
        return ContinentDataBaseHandler.getAll(getWritableDatabase());
    }

    /***/
    public List<String> getAllContinentsName() {
        List<String> names = new ArrayList<String>();
        List<ContinentObject> objects = ContinentDataBaseHandler.getAll(getWritableDatabase());

        for (ContinentObject ob : objects) {
            names.add(ob.getName());
        }
        return names;
    }

    /***/
    public ContinentObject getContinent(int id) {
        return ContinentDataBaseHandler.get(id, getWritableDatabase());
    }

    // COUNTRIES

    /**
     * Return all countries placed on selected continent
     */
    public List<CountryObject> getAllCountries(int id) {
        return CountryDataBaseHandler.getAll(id, getWritableDatabase());
    }

    /***/
    public List<String> getAllCountriesName(String name) {
        List<String> names = new ArrayList<String>();
        List<CountryObject> objects = CountryDataBaseHandler.getAll(name, getWritableDatabase());

        for (CountryObject ob : objects) {
            names.add(ob.getName());
        }
        return names;
    }

    /***/
    public CountryObject getCountry(int id) {
        return CountryDataBaseHandler.get(id, getWritableDatabase());
    }

    // REGIONS

    /**
     * Return all regions placed on selected country
     */
    public List<RegionObject> getAllRegions(int id) {
        return RegionDataBaseHandler.getAll(id, getWritableDatabase());
    }

    /***/
    public List<String> getAllRegionsName(String name) {
        List<String> names = new ArrayList<String>();
        List<RegionObject> objects = RegionDataBaseHandler.getAll(name, getWritableDatabase());

        for (RegionObject ob : objects) {
            names.add(ob.getName());
        }
        return names;
    }

    /***/
    public RegionObject getRegion(int id) {
        return RegionDataBaseHandler.get(id, getWritableDatabase());
    }

    /***/
    public RegionObject getRegion(String name) {
        return RegionDataBaseHandler.get(name, getWritableDatabase());
    }
}