package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Created by Matt on 5/17/2016.
 */
public class ScheduleImplFactory {

    public static Schedule createSchedule(Integer plannedDays, Integer rate) throws InvalidDataException {
        return new ScheduleImpl(plannedDays, rate);
    }
}
