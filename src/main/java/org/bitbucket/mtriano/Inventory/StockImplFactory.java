package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

/**
 * Factory for Stock objects
 */
public class StockImplFactory {

    public static Stock createStock(String itemID, Integer itemQuantity)
            throws InvalidDataException {
        return new StockImpl(itemID, itemQuantity);
    }
}
