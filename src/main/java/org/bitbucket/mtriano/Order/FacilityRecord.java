package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Created by Matt on 5/19/2016.
 */
public interface FacilityRecord {

    Facility getFacility() throws InvalidDataException;
    Integer getQtyFilled() throws InvalidDataException;
    Integer getStartDay() throws InvalidDataException;
    Integer getEndDay() throws InvalidDataException;
    Integer getArrivalDay() throws InvalidDataException;
}
