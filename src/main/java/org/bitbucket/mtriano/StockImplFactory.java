package org.bitbucket.mtriano;

/**
 * Factory for Stock objects
 */
public class StockImplFactory {

    public static Stock createStock(String itemID, int itemQuantity)
            throws InvalidDataException {
        return new StockImpl(itemID, itemQuantity);
    }
}
