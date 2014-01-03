package com.countriesyouvisited.database;

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
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    /***/
    public static final int DATABASE_VERSION = 2;

    /***/
    public static final String DATABASE_NAME = "CountriesYouVisited";

    private SystemDataBaseHandler _systemData = new SystemDataBaseHandler();

    private UserDataBaseHandler _userData = new UserDataBaseHandler();

    private Context _context;

    /***/
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
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
        _systemData.createSystemDatabase(db, _context);
        _userData.createUserDatabase(db);
    }

    // CONTINENTS

    /***/
    public List<ContinentObject> getAllContinents() {
        return _systemData.getAllContinents(getWritableDatabase());
    }

    /***/
    public List<String> getAllContinentsName() {
        return _systemData.getAllContinentsName(getWritableDatabase());
    }

    /***/
    public ContinentObject getContinent(int id) {
        return _systemData.getContinent(id, getWritableDatabase());
    }

    /***/
    public ContinentObject getContinent(String name) {
        return _systemData.getContinent(name, getWritableDatabase());
    }

    // COUNTRIES

    /**
     * Return all countries placed on selected continent
     */
    public List<CountryObject> getAllCountries(int id) {
        return _systemData.getAllCountries(id, getWritableDatabase());
    }

    /***/
    public List<String> getAllCountriesName(String name) {
        return _systemData.getAllCountriesName(name, getWritableDatabase());
    }

    /***/
    public CountryObject getCountry(int id) {
        return _systemData.getCountry(id, getWritableDatabase());
    }

    // REGIONS

    /**
     * Return all regions placed on selected country
     */
    public List<RegionObject> getAllRegions(int id) {
        return _systemData.getAllRegions(id, getWritableDatabase());
    }

    /***/
    public List<String> getAllRegionsName(String name) {
        return _systemData.getAllRegionsName(name, getWritableDatabase());
    }

    /***/
    public RegionObject getRegion(int id) {
        return _systemData.getRegion(id, getWritableDatabase());
    }

    /***/
    public RegionObject getRegion(String name) {
        return _systemData.getRegion(name, getWritableDatabase());
    }

    // VISITED REGIONS

    /***/
    public void addVisitedRegion(VisitedRegionObject object) {
        _userData.addVisitedRegion(object, getWritableDatabase());
    }

    /***/
    public List<VisitedRegionObject> getAllVisitedRegions() {
        return _userData.getAllVisitedRegions(getWritableDatabase());
    }

    /***/
    public int getVisitedRegionCount() {
        return _userData.getVisitedRegionCount(getWritableDatabase());
    }

    /***/
    public void deleteVisitedRegion(VisitedRegionObject object) {
        _userData.deleteVisitedRegion(object, getWritableDatabase());
    }

}
