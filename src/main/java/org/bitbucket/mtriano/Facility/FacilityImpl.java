package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.Inventory.Stock;

import java.util.ArrayList;

/**
 * Implements the Facility interface
 */
public class FacilityImpl implements Facility {

    private String cityID;                          // city name/location
    private int cityRate;                           // daily production rate
    private int cityCost;                           // daily cost of operation
    private Inventory cityInventory;                // local inventory
    private ArrayList<LinkedCity> linkedCities;     // 1st degree linked cities
    private Schedule schedule;

    /*
     * Checks inputs for a Facility object
     */
    public FacilityImpl(String id, int rate, int cost, Inventory inventory,
                        ArrayList<LinkedCity> links, Schedule productionSchedule)
            throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("id not valid (null value entered)");
        }
        if (rate < 0) {
            throw new InvalidDataException("rate not valid (negative value entered)");
        }
        if (cost < 0) {
            throw new InvalidDataException("cost not value (negative value entered)");
        }
        if (inventory == null) {
            throw new InvalidDataException("inventory not valid (null value entered)");
        }
        if (links == null) {
            throw new InvalidDataException("links not valid (null value entered)");
        }
        if (productionSchedule == null) {
            throw new InvalidDataException("null schedule passed to FacilityImpl");
        }
        cityID = id;
        cityRate = rate;
        cityCost = cost;
        cityInventory = inventory;
        linkedCities = links;
        schedule = productionSchedule;
    }

    public String getCityID() throws InvalidDataException {
        return cityID;
    }

    public int getRate() throws InvalidDataException {
        return cityRate;
    }

    public int getCost() throws InvalidDataException {
        return cityCost;
    }

    public Inventory getInventory() throws InvalidDataException {
        return cityInventory;
    }

    public ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException {
        return linkedCities;
    }

    public Schedule getSchedule() throws InvalidDataException {
        return schedule;
    }

    public void printSchedule() throws InvalidDataException {
        schedule.printSchedule();
    }

}
