package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/4/2016.
 */
public interface ShortestPath {

    ArrayList<Facility> shortestPath(Facility originFac, Facility destinationFac)
            throws InvalidDataException;
    ArrayList<String> getPathCities() throws InvalidDataException;
    Integer getPathDistance() throws InvalidDataException;

}
