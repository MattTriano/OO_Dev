package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Interface for ShortestPath objects
 */
public interface ShortestPath {

    ArrayList<Facility> findBestPath(Facility originFac, Facility destinationFac)
            throws InvalidDataException;
    Integer getPathDistance(ArrayList<Facility> path)
            throws InvalidDataException;
    void mapPairs(Facility init) throws InvalidDataException;
    void findPath(Facility start, Facility end, ArrayList<Facility> pathList)
            throws InvalidDataException ;
    ArrayList<Facility> getLowPath() throws InvalidDataException;
    Double getTravelTime(ArrayList<Facility> path, int speed)
            throws InvalidDataException;

}
