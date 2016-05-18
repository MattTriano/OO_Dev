package org.bitbucket.mtriano.ShortestPath;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for Pair objects
 */
public class PairImplFactory {

    public static Pair createPairImpl(Facility facilityA, Facility facilityB) throws InvalidDataException {
        return new PairImpl(facilityA, facilityB);
    }
}
