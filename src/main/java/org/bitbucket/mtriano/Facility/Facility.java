package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;

import java.util.ArrayList;

/**
 * Interface for a Facility
 */
public interface Facility {

    String getCityID() throws InvalidDataException;
    int getRate() throws InvalidDataException;
    int getCost() throws InvalidDataException;
    Inventory getInventory() throws InvalidDataException;
    ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException;
    boolean hasItem(String itemID) throws InvalidDataException;
    boolean itemInStock(String itemID) throws InvalidDataException;
    Schedule getSchedule() throws InvalidDataException;
}
