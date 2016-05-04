package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 4/27/2016.
 */
public interface Facility {

    String getCityID();
    int getRate();
    int getCost();
    ArrayList<Stock> getInventory();
    ArrayList<LinkedCity> getLinkedCities();
}
