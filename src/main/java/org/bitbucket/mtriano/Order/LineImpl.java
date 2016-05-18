package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Created by Matt on 5/18/2016.
 */
public class LineImpl implements Line {

    private String lineID;
    private Integer lineQty;

    public LineImpl(String id, Integer qty) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("Null ID passed to LineImpl");
        }
        if (qty < 0) {
            throw new InvalidDataException("Invalid quantity passed to LineImpl");
        }
        lineID = id;
        lineQty = qty;
    }

    public String getLineID() throws InvalidDataException {
        return lineID;
    }

    public Integer getLineQty() throws InvalidDataException {
        return lineQty;
    }
}
