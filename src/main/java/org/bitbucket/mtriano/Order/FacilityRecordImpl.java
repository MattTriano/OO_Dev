package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.Singletons.FacilityNetwork;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Implements FacilityRecord objects
 */
public class FacilityRecordImpl implements FacilityRecord {

    private Facility sourceFacility;
    private Integer fillQty;
    private Integer processingStart;
    private Integer processingEnd;
    private Integer travelTime;
    private Integer arrivalDay;

    public FacilityRecordImpl(Facility supplyingFacility, Integer quantity, Integer initDay,
                              Integer endDay, Integer transitTime)
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

        sourceFacility = supplyingFacility;
        fillQty = quantity; //supplyingFacility.getInventory().getStockQty(line.getLineID());
        processingStart = initDay;
        processingEnd = endDay;
        travelTime = transitTime;
        arrivalDay = processingEnd + transitTime;
    }

    public Facility getFacility() throws InvalidDataException {
        return sourceFacility;
    }

    public Integer getFillQty() throws InvalidDataException {
        return fillQty;
    }

    public Integer getStartDay() throws InvalidDataException {
        return processingStart;
    }

    public Integer getEndDay() throws InvalidDataException {
        return processingEnd;
    }

    public Integer getProcessingTime() throws InvalidDataException {
        return processingEnd - processingStart;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public Integer getArrivalDay() throws InvalidDataException {
        return arrivalDay;
    }
}
