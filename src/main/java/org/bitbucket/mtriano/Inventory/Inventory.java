package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Interface for an Inventory Object
 */
public interface Inventory {

    ArrayList<Stock> getInventory() throws InvalidDataException;
    Stock getStock(String id) throws InvalidDataException;
    Integer getStockQty(String id) throws InvalidDataException;
    boolean hasItem(String id) throws InvalidDataException;
    boolean itemInStock(String id) throws InvalidDataException;
    Integer shipQty(String itemID,Integer qty) throws InvalidDataException;
}
