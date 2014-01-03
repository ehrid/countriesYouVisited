package com.countriesyouvisited.database.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.util.Pair;

/**
 * @author horodysk
 */
public class RegionObject {
    private int _id;

    private String _name;

    private int _country;

    private int _surface;

    private String _regionPoints;

    private String _countryName;

    private String _continentName;

    /***/
    public RegionObject(int id, String name, int country, int surface, String regionPoints, String countryName, String continentName) {
        _id = id;
        _name = name;
        _country = country;
        _surface = surface;
        _regionPoints = regionPoints;
        _countryName = countryName;
        _continentName = continentName;
    }

    /***/
    public int getId() {
        return _id;
    }

    /***/
    public int getCountryId() {
        return _country;
    }

    /***/
    public String getName() {
        return _name;
    }

    /***/
    public String getCountryName() {
        return _countryName;
    }

    /***/
    public String getContinentName() {
        return _continentName;
    }

    /***/
    public int getSurface() {
        return _surface;
    }

    /***/
    public List<Pair<Integer, Integer>> getRegionPoints() {
        List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();

        if (_regionPoints != null) {

            String decodedPoints = _regionPoints.replaceAll("\\D", " ");
            Scanner in = new Scanner(decodedPoints);

            while (in.hasNext()) {
                int x = in.nextInt();
                if (in.hasNext()) {
                    int y = in.nextInt();
                    pairs.add(new Pair<Integer, Integer>(x, y));
                }
            }
        }

        return pairs;
    }

}
