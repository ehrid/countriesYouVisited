package com.countriesyouvisited.database.objects;


/**
 * @author horodysk
 */
public class CountryObject {
    private int _id;

    private String _name;

    private int _continent;

    private String _continentName;

    /***/
    public CountryObject(int id, String name, int continent, String continentName) {
        _id = id;
        _name = name;
        _continent = continent;
        _continentName = continentName;
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
    public int getContinentId() {
        return _continent;
    }

    /***/
    public String getContinentName() {
        return _continentName;
    }
}
