package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/3/2016.
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
