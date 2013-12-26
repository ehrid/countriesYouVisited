package com.countriesyouvisited.database;

/**
 * @author horodysk
 */
public class CountryObject {
    private int _id;

    private String _name;

    private String _date;

    /***/
    public CountryObject(int id, String name, String date) {
        _id = id;
        _name = name;
        _date = date;
    }

    /***/
    public String getName() {
        return _name;
    }

    /***/
    public void setName(String name) {
        _name = name;
    }

    /***/
    public String getDate() {
        return _date;
    }

    /***/
    public void setDate(String date) {
        _date = date;
    }

    /***/
    public int getId() {
        return _id;
    }
}
