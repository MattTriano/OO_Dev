package org.bitbucket.mtriano;

/**
 * Interface for Stock objects
 * Describes the inventory held at a facility
 */
public interface Stock {

    String getID() throws InvalidDataException;
    int getQuantity() throws InvalidDataException;

}
