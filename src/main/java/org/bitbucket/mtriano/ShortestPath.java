package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/4/2016.
 */
public interface ShortestPath {

    ArrayList<Facility> findBestPath(Facility originFac, Facility destinationFac)
            throws InvalidDataException;
    ArrayList<String> getPathCities() throws InvalidDataException;
    Integer getPathDistance() throws InvalidDataException;
    void mapPairs(Facility init) throws InvalidDataException;
    void findPath(Facility start, Facility end, ArrayList<Facility> pathList)
            throws InvalidDataException ;
    ArrayList<Facility> getLowPath() throws InvalidDataException;

}
