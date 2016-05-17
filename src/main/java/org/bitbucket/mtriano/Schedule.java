package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/16/2016.
 */
public interface Schedule {

    ArrayList<Integer> getAvailability() throws InvalidDataException;
    void scheduleProduction(Integer quantity) throws InvalidDataException;

}
