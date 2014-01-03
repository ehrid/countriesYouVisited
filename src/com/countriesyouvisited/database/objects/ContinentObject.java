package com.countriesyouvisited.database.objects;

/**
 * @author horodysk
 */
public class ContinentObject {
    private int _id;

    private String _name;

    private String _file;

    /***/
    public ContinentObject(int id, String name, String file) {
        _id = id;
        _name = name;
        _file = file;
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
    public String getFile() {
        return _file;
    }
}
