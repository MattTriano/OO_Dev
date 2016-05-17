package org.bitbucket.mtriano.ShortestPath;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for ShortestPath objects
 */
public class ShortestPathAllPairsImplFactory {

    public static ShortestPath CreateShortestPath(Facility start, Facility end)
            throws InvalidDataException {
        return new ShortestPathAllPairsImpl(start, end);
    }
}
