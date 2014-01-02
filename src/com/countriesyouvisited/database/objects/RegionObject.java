package com.countriesyouvisited.database.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
