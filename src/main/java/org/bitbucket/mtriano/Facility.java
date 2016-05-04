package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 4/27/2016.
 */
public interface Facility {

    String getCityID() throws InvalidDataException;
    int getRate() throws InvalidDataException;
    int getCost() throws InvalidDataException;
    ArrayList<Stock> getInventory() throws InvalidDataException;
    ArrayList<LinkedCity> getLinkedCities() throws InvalidDataException;
}
