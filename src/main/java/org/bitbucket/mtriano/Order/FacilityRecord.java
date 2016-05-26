package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Interface for a FacilityRecord
 */
public interface FacilityRecord {

    Facility getFacility() throws InvalidDataException;         // the source facility
    Integer getFillQty() throws InvalidDataException;           // Fillable quantity.
    Integer getStartDay() throws InvalidDataException;          // line processing start day
    Integer getEndDay() throws InvalidDataException;            // line processing end day
    Integer getArrivalDay() throws InvalidDataException;        // line delivery day
    Integer getTravelTime() throws InvalidDataException;
    Integer getProcessingTime() throws InvalidDataException;
}
