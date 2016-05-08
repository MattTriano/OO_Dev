package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/7/2016.
 */
public class PairImplFactory {

    public static Pair createPairImpl(Facility facilityA, Facility facilityB) throws InvalidDataException {
        return new PairImpl(facilityA, facilityB);
    }
}
