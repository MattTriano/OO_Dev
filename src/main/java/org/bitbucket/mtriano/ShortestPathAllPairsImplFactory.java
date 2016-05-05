package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/5/2016.
 */
public class ShortestPathAllPairsImplFactory {

    public static ShortestPath CreateShortestPath(Facility start, Facility end)
            throws InvalidDataException {
        return new ShortestPathAllPairsImpl(start, end);
    }
}
