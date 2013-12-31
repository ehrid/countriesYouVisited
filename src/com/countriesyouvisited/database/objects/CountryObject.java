package com.countriesyouvisited.database.objects;

import com.countriesyouvisited.database.DataBaseHandler;

/**
 * @author horodysk
 */
public class CountryObject {
    private int _id;

    private String _name;

    private int _continent;

    /***/
    public CountryObject(int id, String name, int continent) {
        _id = id;
        _name = name;
        _continent = continent;
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
    public ContinentObject getContinent(DataBaseHandler db) {
        return db.getContinent(_continent);
    }
}
