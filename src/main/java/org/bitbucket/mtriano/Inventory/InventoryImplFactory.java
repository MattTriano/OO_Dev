package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/17/2016.
 */
public class InventoryImplFactory {

    public static Inventory createInventory(ArrayList<Stock> inventory)
            throws InvalidDataException {
        return new InventoryImpl(inventory);
    }
}
