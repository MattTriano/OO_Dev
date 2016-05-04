package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/3/2016.
 */
public class FacilityImplFactory {

    public static Facility createFacility(String id, int rate, int cost,
                                          ArrayList<Stock> inventory,
                                          ArrayList<LinkedCity> links) throws InvalidDataException {
        return new FacilityImpl(id, rate, cost, inventory, links);
    }
}
