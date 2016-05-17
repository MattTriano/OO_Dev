package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Facility.Facility;

/**
 * Factory for Pair objects
 */
public class PairImplFactory {

    public static Pair createPairImpl(Facility facilityA, Facility facilityB) throws InvalidDataException {
        return new PairImpl(facilityA, facilityB);
    }
}
