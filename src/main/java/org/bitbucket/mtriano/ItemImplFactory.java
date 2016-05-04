package org.bitbucket.mtriano;

/**
 * Created by Matt on 4/27/2016.
 */
public class ItemImplFactory {

    public static Item createItem(String itemID, double itemCost) throws InvalidDataException {
        return new ItemImpl(itemID, itemCost);
    }
}
