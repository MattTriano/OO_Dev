package org.bitbucket.mtriano.Inventory;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Implements an Inventory object
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

    /*  Checks whether the local inventory has an item
     *
     *  @param  itemID     The ID of the item
     *  @return            Boolean
     */
    public boolean hasItem(String itemID) throws InvalidDataException {
        for (Stock stock : inventory) {
            if (stock.getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    /*  This checks to see if an item is valid and is in stock
     *
     *  @param  itemID      The ID of the item
     *  @return             boolean
     */
    public boolean itemInStock(String itemID) throws InvalidDataException {
        for (Stock stock : inventory) {
            if (stock.getID().equals(itemID) && (stock.getQuantity() > 0)) {
                return true;
            }
        }
        return false;
    }

    /*  Returns the stock of an item with a given ID
     *
     *  @param  id        The id of the requested item
     *  @return           The corresponding Stock object
     */
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

    /*  Returns the quantity of an item in stock
     *
     *  @param  id        The id of the requested item
     *  return            The quantity in the corresponding Stock object
     */
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

    /*  Returns the amount actually shipped from inventory.
     *
     *  @param itemID        The id of the item to be shipped
     *  @param qty           The quantity that is desired
     *  @return              The actual quantity shipped and
     *                        removed from inventory
     */
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
