package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Interface for a Line (as in order line) object
 */
public interface Line {

    String getLineID() throws InvalidDataException;
    Integer getLineQty() throws InvalidDataException;
}
