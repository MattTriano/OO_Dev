package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.ItemCatalog;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.Inventory.Stock;
import org.bitbucket.mtriano.Order.Line;

import java.util.ArrayList;

/**
 * Implements the Facility interface
 */
public class FacilityImpl implements Facility {

    private String cityID;                          // city name/location
    private Integer cityRate;                           // daily production rate
    private Integer cityCost;                           // daily cost of operation
    private Inventory cityInventory;                // local inventory
    private ArrayList<LinkedCity> linkedCities;     // 1st degree linked cities
    private Schedule schedule;

    /*
     * Checks inputs for a Facility object
     */
    public FacilityImpl(String id, Integer rate, Integer cost, Inventory inventory,
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

    public Integer getRate() throws InvalidDataException {
        return cityRate;
    }

    public Integer getCost() throws InvalidDataException {
        return cityCost;
    }

    public Inventory getInventory() throws InvalidDataException {
        return cityInventory;
    }

    public ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException {
        return linkedCities;
    }

    public boolean hasItem(String itemID) throws InvalidDataException {
        if (!ItemCatalog.getInstance().isItem(itemID)) {
            throw new InvalidDataException("Invalid item ID passed to " +
                    "FacilityImpl/hasItem");
        }
        return cityInventory.hasItem(itemID);
    }

    public boolean itemInStock(String itemID) throws InvalidDataException {
        if (!ItemCatalog.getInstance().isItem(itemID)) {
            throw new InvalidDataException("Invalid item ID passed to " +
                    "FacilityImpl/hasItem");
        }
        return cityInventory.itemInStock(itemID);
    }


    public Schedule getSchedule() throws InvalidDataException {
        return schedule;
    }

    public void printSchedule() throws InvalidDataException {
        schedule.printSchedule();
    }

    public void scheduleProduction(Line line, Integer startDay, Integer quantity) throws InvalidDataException {
        schedule.scheduleProduction(startDay, quantity);
        Integer qtyShipped = cityInventory.shipQty(line.getLineID(), quantity);
        if (qtyShipped > quantity) {
            throw new InvalidDataException("Shipped too many items from " + cityID);
        }
    }

}
