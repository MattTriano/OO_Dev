package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.FacilityNetwork;
import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.ShortestPath.ShortestPath;

/**
 * Created by Matt on 5/19/2016.
 */
public class FacilityRecordImpl implements FacilityRecord {

    private Facility sourceFacility;
    private Integer qtyFilled;
    private Integer qtyUnfilled;
    private Integer startDay;
    private ShortestPath path;

    public FacilityRecordImpl(Facility supplyingFacility, Integer initDay, Line line,
                              Integer qty) throws InvalidDataException {
        if (!FacilityNetwork.getInstance().isValidFacility(supplyingFacility)) {
            throw new InvalidDataException("Invalid facility passed" +
                    " to FacilityRecordImpl");
        }
        if (initDay < 1) {
            throw new InvalidDataException("Invalid start day passed " +
                    "to FacilityRecordImpl");
        }
        if (line == null) {
            throw new InvalidDataException("Null line passed to FacilityRecordImpl");
        }
        if (qty < 0) {
            throw new InvalidDataException("Negative fulfillment quantity " +
                    "passed to FacilityRecordImpl");
        }

        sourceFacility = supplyingFacility;
        qtyFilled = qty;
        qtyUnfilled = qty;
        startDay = initDay;
    }

    public Facility getFacility() throws InvalidDataException {
        return sourceFacility;
    }

    public Integer getQtyFilled() throws InvalidDataException {
        return qtyFilled;
    }

    public Integer getStartDay() throws InvalidDataException {
        return startDay;
    }

    public Integer getEndDay() throws InvalidDataException {
        return null;
    }

    public Integer getArrivalDay() throws InvalidDataException {
        return null;
    }
}
