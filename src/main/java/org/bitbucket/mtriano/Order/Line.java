package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Created by Matt on 5/18/2016.
 */
public interface Line {

    String getLineID() throws InvalidDataException;
    Integer getLineQty() throws InvalidDataException;
}
