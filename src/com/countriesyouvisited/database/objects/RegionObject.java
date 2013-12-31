package com.countriesyouvisited.database.objects;

import java.util.List;

import android.util.Pair;

import com.countriesyouvisited.database.DataBaseHandler;

/**
 * @author horodysk
 */
public class RegionObject {
    private int _id;

    private String _name;

    private int _country;

    private int _surface;

    private String _regionPoints;

    /***/
    public RegionObject(int id, String name, int country, int surface, String regionPoints) {
        _id = id;
        _name = name;
        _country = country;
        _surface = surface;
        _regionPoints = regionPoints;
    }

    /***/
    public int getId() {
        return _id;
    }

    /***/
    public String getName() {
        return _name;
    }

    /***/
    public CountryObject getCountry(DataBaseHandler db) {
        return db.getCountry(_country);
    }

    /***/
    public int getSurface() {
        return _surface;
    }

    /***/
    public List<Pair<Integer, Integer>> getRegionPoints() {
        // TODO add translation of string points to list of points
        return null;
    }

}
