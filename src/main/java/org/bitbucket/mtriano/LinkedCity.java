package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/3/2016.
 */
public interface LinkedCity {

    String getCityID() throws InvalidDataException;
    int getDistance() throws InvalidDataException;

}
