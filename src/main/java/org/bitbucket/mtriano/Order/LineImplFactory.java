package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Created by Matt on 5/18/2016.
 */
public class LineImplFactory {

    public static Line createLine(String lineID, Integer lineQty)
            throws InvalidDataException{
        return new LineImpl(lineID, lineQty);
    }
}
