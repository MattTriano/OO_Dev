package org.bitbucket.mtriano;

/**
 * Implements the LinkedCity interface
 */
public class LinkedCityImpl implements LinkedCity {

    private String cityID;
    private int cityDistance;

    public LinkedCityImpl(String id, int distance) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("CityID not valid (null value entered)");
        }
        if (distance < 0) {
            throw new InvalidDataException("Distance not valid (negative value entered)");
        }
        cityID = id;
        cityDistance = distance;
    }

    public String getCityID() throws InvalidDataException {
        return cityID;
    }

    public int getDistance() throws InvalidDataException {
        return cityDistance;
    }
}
