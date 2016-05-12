package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Interface for a Pair of Facilities
 */
public interface Pair {

    int getDistance() throws InvalidDataException;
    Facility getFacilityA();
    Facility getFacilityB();
    ArrayList<Facility> getPair();

}
