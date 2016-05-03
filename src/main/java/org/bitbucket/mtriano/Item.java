package org.bitbucket.mtriano;

/**
 * Created by Matt on 4/27/2016.
 */
public interface Item {

    String getID() throws InvalidDataException;
    double getCost() throws InvalidDataException;

}
