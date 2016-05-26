package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Factory for an Inventory object
 */
public class InventoryImplFactory {

    public static Inventory createInventory(ArrayList<Stock> inventory)
            throws InvalidDataException {
        return new InventoryImpl(inventory);
    }
}
