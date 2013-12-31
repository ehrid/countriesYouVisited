package com.countriesyouvisited.database;

import java.util.List;

import android.content.Context;

import com.countriesyouvisited.database.objects.ContinentObject;
import com.countriesyouvisited.database.objects.CountryObject;
import com.countriesyouvisited.database.objects.RegionObject;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class DataBaseHandler {
    SystemDataBaseHandler _systemData;

    UserDataBaseHandler _userData;

    /***/
    public DataBaseHandler(Context context) {
        _systemData = new SystemDataBaseHandler(context);
        _userData = new UserDataBaseHandler(context);
    }

    // CONTINENTS

    /***/
    public List<ContinentObject> getAllContinents() {
        return _systemData.getAllContinents();
    }

    /***/
    public List<String> getAllContinentsName() {
        return _systemData.getAllContinentsName();
    }

    /***/
    public ContinentObject getContinent(int id) {
        return _systemData.getContinent(id);
    }

    // COUNTRIES

    /**
     * Return all countries placed on selected continent
     */
    public List<CountryObject> getAllCountries(int id) {
        return _systemData.getAllCountries(id);
    }

    /***/
    public List<String> getAllCountriesName(String name) {
        return _systemData.getAllCountriesName(name);
    }

    /***/
    public CountryObject getCountry(int id) {
        return _systemData.getCountry(id);
    }

    // REGIONS

    /**
     * Return all regions placed on selected country
     */
    public List<RegionObject> getAllRegions(int id) {
        return _systemData.getAllRegions(id);
    }

    /***/
    public List<String> getAllRegionsName(String name) {
        return _systemData.getAllRegionsName(name);
    }

    /***/
    public RegionObject getRegion(int id) {
        return _systemData.getRegion(id);
    }

    /***/
    public RegionObject getRegion(String name) {
        return _systemData.getRegion(name);
    }

    // VISITED REGIONS

    /***/
    public void addVisitedRegion(VisitedRegionObject object) {
        _userData.addVisitedRegion(object);
    }

    /***/
    public List<VisitedRegionObject> getAllVisitedRegions() {
        return _userData.getAllVisitedRegions();
    }

    /***/
    public int getVisitedRegionCount() {
        return _userData.getVisitedRegionCount();
    }

    /***/
    public void deleteVisitedRegion(VisitedRegionObject object) {
        _userData.deleteVisitedRegion(object);
    }

}
