package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/17/2016.
 */
public class ScheduleImpl {

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
        for (int i = 0; i < plannedDays; i++) {
            schedule.add(rate);
        }
        rate = localRate;
        days = plannedDays;
    }

    public void scheduleProduction(Integer quantity) throws InvalidDataException {
        if (quantity < 0) {
            throw new InvalidDataException("Invalid quantity passed to scheduleProduction");
        }
        for (int i = 0; i < schedule.size(); i++) {
            Integer capacityToday = schedule.get(i);
            if (quantity == 0) {
                break;
            } else if (capacityToday == 0) {
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

    public ArrayList<Integer> getAvailability() throws InvalidDataException {
        return schedule;
    }

    public void printSchedule() {
        for (int i = 1; i <= days; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print("\nAvailable:  ");
        for (int i = 1; i <= days; i++) {
            System.out.printf("%3d", schedule.get(i-1));
        }
    }
}
