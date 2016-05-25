package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.Order.Line;

import java.util.ArrayList;

/**
 * Interface for a Facility
 */
public interface Facility {

    String getCityID() throws InvalidDataException;
    Integer getRate() throws InvalidDataException;
    Integer getCost() throws InvalidDataException;
    Inventory getInventory() throws InvalidDataException;
    ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException;
    boolean hasItem(String itemID) throws InvalidDataException;
    boolean itemInStock(String itemID) throws InvalidDataException;
    Schedule getSchedule() throws InvalidDataException;
    void printSchedule() throws InvalidDataException;
    void scheduleProduction(Line line, Integer startDay, Integer quantity)
            throws InvalidDataException;
}
