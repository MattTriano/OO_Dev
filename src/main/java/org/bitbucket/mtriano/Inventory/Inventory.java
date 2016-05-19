package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/17/2016.
 */
public interface Inventory {

    ArrayList<Stock> getInventory() throws InvalidDataException;
    Stock getStock(String id) throws InvalidDataException;
    Integer getStockQty(String id) throws InvalidDataException;
    boolean hasItem(String id) throws InvalidDataException;
}
