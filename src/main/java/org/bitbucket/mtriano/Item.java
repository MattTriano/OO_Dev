package org.bitbucket.mtriano;

/**
 * Item Interface
 */
public interface Item {

    String getID() throws InvalidDataException;
    double getCost() throws InvalidDataException;

}
