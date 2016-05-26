package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for a Schedule object
 */
public class ScheduleImplFactory {

    public static Schedule createSchedule(Integer plannedDays, Integer rate) throws InvalidDataException {
        return new ScheduleImpl(plannedDays, rate);
    }
}
