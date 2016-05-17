package org.bitbucket.mtriano.LinkedCity;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for LinkedCity objects
 */
public class LinkedCityImplFactory {

    public static LinkedCity createLinkedCity(String cityID, int cityDistance) throws InvalidDataException {
        return new LinkedCityImpl(cityID, cityDistance);
    }
}
