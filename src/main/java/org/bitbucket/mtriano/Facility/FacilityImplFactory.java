package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.Stock;

import java.util.ArrayList;

/**
 * Factory for a Facility object
 */
public class FacilityImplFactory {

    public static Facility createFacility(String id, int rate, int cost,
                                          ArrayList<Stock> inventory,
                                          ArrayList<LinkedCity> links) throws InvalidDataException {
        return new FacilityImpl(id, rate, cost, inventory, links);
    }
}
