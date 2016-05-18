package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/16/2016.
 */
public interface Schedule {

    ArrayList<Integer> getAvailability() throws InvalidDataException;
    void scheduleProduction(Integer startDay, Integer quantity) throws InvalidDataException;
    void printSchedule() throws InvalidDataException;

}
