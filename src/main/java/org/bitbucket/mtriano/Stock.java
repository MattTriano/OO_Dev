package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/3/2016.
 */
public interface Stock {

    String getID() throws InvalidDataException;
    int getQuantity() throws InvalidDataException;

}
