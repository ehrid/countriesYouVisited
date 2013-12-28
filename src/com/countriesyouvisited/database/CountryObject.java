package com.countriesyouvisited.database;

/**
 * @author horodysk
 */
public class CountryObject {
    private int _id;

    private String _name;

    private int _month;

    private int _year;

    /***/
    public CountryObject(String name, int month, int year) {
        _id = 0;
        _name = name;
        _month = month;
        _year = year;
    }

    /***/
    public CountryObject(int id, String name, int month, int year) {
        _id = id;
        _name = name;
        _month = month;
        _year = year;
    }

    /***/
    public int getMonth() {
        return _month;
    }

    /***/
    public void setMonth(int month) {
        _month = month;
    }

    /***/
    public int getYear() {
        return _year;
    }

    /***/
    public void setYear(int year) {
        _year = year;
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
    public int getId() {
        return _id;
    }
}
