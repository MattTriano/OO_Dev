package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.Inventory.Stock;

import java.util.ArrayList;

/**
 * Factory for a Facility object
 */
public class FacilityImplFactory {

    public static Facility createFacility(String id, int rate, int cost, Inventory inventory,
                                          ArrayList<LinkedCity> links, Schedule schedule)
                                            throws InvalidDataException {
        return new FacilityImpl(id, rate, cost, inventory, links, schedule);
    }
}
