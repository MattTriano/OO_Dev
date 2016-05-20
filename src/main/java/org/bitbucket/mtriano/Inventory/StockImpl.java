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

    /* This method reduces the stock of an item at a facility
     *
     * @param  id       The itemID to confirm we're filling the right thing
     * @param  qty      The quantity of items requested
     * @return          returns the quantity of items that are shipped from stock
     */
    public Integer shipStock(String id, Integer qty) throws InvalidDataException {
        if (!id.equals(itemID)) {
            throw new InvalidDataException("Trying to draw the wrong item from stock");
        }
        int quantity = itemQuantity;
        if (qty > itemQuantity) {
            itemQuantity = 0;
            return quantity;
        } else {
            itemQuantity = itemQuantity - qty;
            return qty;
        }
    }
}
