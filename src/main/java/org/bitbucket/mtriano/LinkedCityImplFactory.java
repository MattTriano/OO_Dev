package org.bitbucket.mtriano;

/**
 * Factory for LinkedCity objects
 */
public class LinkedCityImplFactory {

    public static LinkedCity createLinkedCity(String cityID, int cityDistance) throws InvalidDataException {
        return new LinkedCityImpl(cityID, cityDistance);
    }
}
