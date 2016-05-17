package org.bitbucket.mtriano.Item;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Item.Item;

/**
 * Implements the Item interface
 */
public class ItemImpl implements Item {

    private String itemID;
    private double itemCost;

    public ItemImpl(String id, double cost) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("ItemID not valid (it's null).");
        }
        if (cost < 0) {
            throw new InvalidDataException("ItemCost not valid (it's negative).");
        }
        itemID = id;
        itemCost = cost;
    }

    public String getID() throws InvalidDataException {
        return itemID;
    }

    public double getCost() throws InvalidDataException {
        return itemCost;
    }
}
