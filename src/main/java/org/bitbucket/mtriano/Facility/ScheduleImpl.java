package org.bitbucket.mtriano.Facility;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Implements the Schedule interface
 */
public class ScheduleImpl implements Schedule{

    private ArrayList<Integer> schedule;
    private Integer rate;
    private Integer days;

    public ScheduleImpl(Integer plannedDays, Integer localRate)
            throws InvalidDataException {
        if (localRate < 0) {
            throw new InvalidDataException("Invalid rate passed to ScheduleImpl");
        }
        if (plannedDays < 0) {
            throw new InvalidDataException("Invalid number of days passed to ScheduleImpl");
        }
        schedule = new ArrayList<>();
        rate = localRate;
        for (int i = 0; i < plannedDays; i++) {
            schedule.add(rate);
        }
        days = plannedDays;
    }

    /*  Schedules production and updates the schedule accordingly
     *
     *  @param  startDay     The first day that this request could begin processing
     *  @param  quantity     The quantity of work to be scheduled
     */
    public void scheduleProduction(Integer startDay, Integer quantity)
            throws InvalidDataException {
        if (quantity < 0) {
            throw new InvalidDataException("Invalid quantity passed to scheduleProduction");
        }
        if (startDay < 0 | startDay > days) {
            throw new InvalidDataException("Invalid startDay passed to scheduleProduction");
        }
        for (int i = startDay-1; i < schedule.size(); i++) {
            Integer capacityToday = schedule.get(i);
            if (quantity == 0) {
                break;
            } else if (capacityToday <= 0) {
                continue;
            } else if (quantity < capacityToday) {
                schedule.set(i, (capacityToday - quantity));
                quantity = 0;
            } else {
                schedule.set(i, 0);
                quantity = quantity - capacityToday;
            }
        }
    }

    public ArrayList<Integer> getSchedule() throws InvalidDataException {
        return schedule;
    }

    /*  Prints the current schedule
     */
    public void printSchedule() {
        System.out.print("Day:        ");
        for (int i = 1; i <= days; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print("\nAvailable:  ");
        for (int i = 1; i <= days; i++) {
            System.out.printf("%3d", schedule.get(i-1));
        }
        System.out.println(" ");
    }
}
