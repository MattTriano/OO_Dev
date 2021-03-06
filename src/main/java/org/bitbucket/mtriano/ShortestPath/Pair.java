package org.bitbucket.mtriano.ShortestPath;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

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
