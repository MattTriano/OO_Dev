package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/3/2016.
 */
public class LinkedCityImplFactory {

    public static LinkedCity createLinkedCity(String cityID, int cityDistance) throws InvalidDataException {
        return new LinkedCityImpl(cityID, cityDistance);
    }
}
