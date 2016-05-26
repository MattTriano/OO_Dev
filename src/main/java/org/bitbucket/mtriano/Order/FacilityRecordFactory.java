package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for a FacilityRecord object
 */
public class FacilityRecordFactory {
    public static FacilityRecord createFacilityRecord(Facility facility, Integer quantity,
                                                      Integer initDay, Integer endDay,
                                                      Integer travelTime)
            throws InvalidDataException {
        return new FacilityRecordImpl(facility, quantity, initDay, endDay, travelTime);
    }
}
