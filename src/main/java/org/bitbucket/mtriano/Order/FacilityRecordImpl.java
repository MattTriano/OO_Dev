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
    private Integer qtyAvailable;
    private Integer processingStart;
    private Integer processingEnd;
    private Integer travelTime;
    private Integer arrivalDay;

    public FacilityRecordImpl(Facility supplyingFacility, Integer initDay,
                              Integer endDay, Integer transitTime, Line line)
            throws InvalidDataException {
        if (!FacilityNetwork.getInstance().isValidFacility(supplyingFacility)) {
            throw new InvalidDataException("Invalid facility passed" +
                    " to FacilityRecordImpl");
        }
        if (initDay < 1) {
            throw new InvalidDataException("Invalid start day passed " +
                    "to FacilityRecordImpl");
        }
        if (endDay < initDay) {
            throw new InvalidDataException("Impossible endDay passed " +
                    "to FacilityRecordImpl");
        }
        if (line == null) {
            throw new InvalidDataException("Null line passed to FacilityRecordImpl");
        }


        sourceFacility = supplyingFacility;
        qtyAvailable = supplyingFacility.getInventory().getStockQty(line.getLineID());
        processingStart = initDay;
        processingEnd = endDay;
        travelTime = transitTime;
        arrivalDay = processingEnd + transitTime;
    }

    public Facility getFacility() throws InvalidDataException {
        return sourceFacility;
    }

    public Integer getQtyAvailable() throws InvalidDataException {
        return qtyAvailable;
    }

    public Integer getStartDay() throws InvalidDataException {
        return processingStart;
    }

    public Integer getEndDay() throws InvalidDataException {
        return processingEnd;
    }

    public Integer getArrivalDay() throws InvalidDataException {
        return arrivalDay;
    }
}
