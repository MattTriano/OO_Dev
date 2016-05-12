package org.bitbucket.mtriano;

/**
 * Implements the Stock interface
 */
public class StockImpl implements Stock {

    private String itemID;
    private int itemQuantity;

    public StockImpl(String id, int qty) throws InvalidDataException {
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

    public int getQuantity() throws InvalidDataException {
        return itemQuantity;
    }
}
