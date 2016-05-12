package org.bitbucket.mtriano;

/**
 * Factory for ShortestPath objects
 */
public class ShortestPathAllPairsImplFactory {

    public static ShortestPath CreateShortestPath(Facility start, Facility end)
            throws InvalidDataException {
        return new ShortestPathAllPairsImpl(start, end);
    }
}
