package com.countriesyouvisited.database.objects;

/**
 * @author horodysk
 */
public class ContinentObject {
    private int _id;

    private String _name;

    /***/
    public ContinentObject(int id, String name) {
        _id = id;
        _name = name;
    }

    /***/
    public int getId() {
        return _id;
    }

    /***/
    public String getName() {
        return _name;
    }
}
