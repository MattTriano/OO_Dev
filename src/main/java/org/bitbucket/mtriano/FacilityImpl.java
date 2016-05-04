package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/3/2016.
 */
public class FacilityImpl implements Facility {

    private String cityID;                          // city name/location
    private int rate;                               // daily production rate
    private int cost;                               // daily cost of operation
    private ArrayList<Stock> inventory;             // local inventory
    private ArrayList<LinkedCity> linkedCities;     // 1st degree linked cities

    

    public String getCityID() throws InvalidDataException {
        return cityID;
    }

    public int getRate() throws InvalidDataException {
        return rate;
    }

    public int getCost() throws InvalidDataException {
        return cost;
    }

    public ArrayList<Stock> getInventory() throws InvalidDataException {
        return inventory;
    }

    public ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException {
        return linkedCities;
    }

}
