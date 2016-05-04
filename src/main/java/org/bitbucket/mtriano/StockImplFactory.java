package org.bitbucket.mtriano;

/**
 * Created by Matt on 5/3/2016.
 */
public class StockImplFactory {

    public static Stock createStock(String itemID, int itemQuantity)
            throws InvalidDataException {
        return new StockImpl(itemID, itemQuantity);
    }
}
