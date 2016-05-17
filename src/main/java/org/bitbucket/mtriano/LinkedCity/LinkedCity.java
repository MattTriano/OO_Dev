package org.bitbucket.mtriano.LinkedCity;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Interface for LinkedCity objects
 * These are the cities linked to a facility
 */
public interface LinkedCity {

    String getCityID() throws InvalidDataException;
    int getDistance() throws InvalidDataException;

}
