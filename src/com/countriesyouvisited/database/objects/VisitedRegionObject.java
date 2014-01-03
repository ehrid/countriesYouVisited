package com.countriesyouvisited.database.objects;

import com.countriesyouvisited.database.DataBaseHandler;

/**
 * @author horodysk
 */
public class VisitedRegionObject {
    private int _id;

    private int _region;

    private int _month;

    private int _year;

    /***/
    public VisitedRegionObject(int id) {
        _id = id;
        _region = 0;
        _month = 0;
        _year = 0;
    }

    /***/
    public VisitedRegionObject(int region, int month, int year) {
        _id = 0;
        _region = region;
        _month = month;
        _year = year;
    }

    /***/
    public VisitedRegionObject(int id, int region, int month, int year) {
        _id = id;
        _region = region;
        _month = month;
        _year = year;
    }

    /***/
    public int getId() {
        return _id;
    }

    /***/
    public RegionObject getRegion(DataBaseHandler db) {
        return db.getRegion(_region);
    }

    /***/
    public void setRegion(int region) {
        _region = region;
    }

    /***/
    public int getRegionId() {
        return _region;
    }

    /***/
    public String getDate() {
        return _year + "/" + _month;
    }

    /***/
    public void setMonth(int month) {
        _month = month;
    }

    /***/
    public void setYear(int year) {
        _year = year;
    }

    /***/
    public int getMonth() {
        return _month;
    }

    /***/
    public int getYear() {
        return _year;
    }

}
