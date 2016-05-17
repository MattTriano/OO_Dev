package org.bitbucket.mtriano.Item;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Item Interface
 */
public interface Item {

    String getID() throws InvalidDataException;
    double getCost() throws InvalidDataException;

}
