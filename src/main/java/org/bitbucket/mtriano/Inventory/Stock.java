package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Interface for Stock objects
 * Describes the inventory held at a facility
 */
public interface Stock {

    String getID() throws InvalidDataException;
    int getQuantity() throws InvalidDataException;

}
