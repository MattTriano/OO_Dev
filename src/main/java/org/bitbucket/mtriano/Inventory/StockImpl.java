package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Implements the Stock interface
 */
public class StockImpl implements Stock {

    private String itemID;
    private int itemQuantity;

    public StockImpl(String id, Integer qty) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("ItemID not valid (null value entered)");
        }
        if (qty < 0) {
            throw new InvalidDataException("ItemQuantity not valid (negative value entered)");
        }
        itemID = id;
        itemQuantity = qty;
    }

    public String getID() throws InvalidDataException {
        return itemID;
    }

    public Integer getQuantity() throws InvalidDataException {
        return itemQuantity;
    }
}
