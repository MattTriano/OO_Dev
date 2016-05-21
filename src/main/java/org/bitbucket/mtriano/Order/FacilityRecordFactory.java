package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Created by Matt on 5/19/2016.
 */
public class FacilityRecordFactory {
    public static FacilityRecord createFacilityRecord(Facility facility, Integer quantity,
                                                      Integer initDay, Integer endDay,
                                                      Integer travelTime)
            throws InvalidDataException {
        return new FacilityRecordImpl(facility, quantity, initDay, endDay, travelTime);
    }
}
