package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.Stock;

import java.util.ArrayList;

/**
 * Interface for a Facility
 */
public interface Facility {

    String getCityID() throws InvalidDataException;
    int getRate() throws InvalidDataException;
    int getCost() throws InvalidDataException;
    ArrayList<Stock> getInventory() throws InvalidDataException;
    ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException;
}