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

    public boolean hasItem(String itemID) throws InvalidDataException {
        for (Stock stock : inventory) {
            if (stock.getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    public boolean itemInStock(String itemID) throws InvalidDataException {
        for (Stock stock : inventory) {
            if (stock.getID().equals(itemID) && (stock.getQuantity() > 0)) {
                return true;
            }
        }
        return false;
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

    public Integer shipQty(String itemID, Integer qty) throws InvalidDataException {
        if (qty < 0) {
            throw new InvalidDataException("Negative qty passed to shipQty in InventoryImpl");
        }
        Integer quantityShipped = getStockQty(itemID);
        if (qty > getStockQty(itemID)) {
            getStock(itemID).shipStock(itemID, getStockQty(itemID));
        } else {
            quantityShipped = qty;
            getStock(itemID).shipStock(itemID, qty);
        }
        return quantityShipped;
    }
}
