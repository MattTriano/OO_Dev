package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/17/2016.
 */
public class InventoryImpl implements Inventory {

    private ArrayList<Stock> inventory;

    public InventoryImpl(ArrayList<Stock> localInventory) throws InvalidDataException {
        if (localInventory == null) {
            throw new InvalidDataException("null localInventory passed to InventoryImpl");
        }
        inventory = localInventory;
    }

    public ArrayList<Stock> getInventory() throws InvalidDataException {
        return inventory;
    }

    public Stock getStock(String id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("null id passed to getStock");
        }
        for (Stock stock : inventory) {
            if (stock.getID().equals(id)) {
                return stock;
            }
        }
        return null;
    }

    public Integer getStockQty(String id) throws InvalidDataException {
        if (id == null) {
            throw new InvalidDataException("null id passed to getStockQty");
        }
        for (Stock stock : inventory) {
            if (stock.getID().equals(id)) {
                return stock.getQuantity();
            }
        }
        return null;
    }
}