package org.bitbucket.mtriano;

/**
 * Factory for Item objects
 */
public class ItemImplFactory {

    public static Item createItem(String itemID, double itemCost) throws InvalidDataException {
        return new ItemImpl(itemID, itemCost);
    }
}
