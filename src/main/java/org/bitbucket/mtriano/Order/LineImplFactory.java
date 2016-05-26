package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for Line objects
 */
public class LineImplFactory {

    public static Line createLine(String lineID, Integer lineQty)
            throws InvalidDataException{
        return new LineImpl(lineID, lineQty);
    }
}
