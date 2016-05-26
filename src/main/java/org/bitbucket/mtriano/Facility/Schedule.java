package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Interface for a Schedule
 */
public interface Schedule {

    ArrayList<Integer> getSchedule() throws InvalidDataException;
    void scheduleProduction(Integer startDay, Integer quantity) throws InvalidDataException;
    void printSchedule() throws InvalidDataException;

}
