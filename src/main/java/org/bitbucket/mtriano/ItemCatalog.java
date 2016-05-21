package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Item.Item;
import org.bitbucket.mtriano.Item.ItemLoader;

import java.util.ArrayList;

/**
 * Singleton for the ItemCatalog
 */
public final class ItemCatalog {

    private static volatile ItemCatalog instance;
    private static ArrayList<Item> catalog;
    private static ItemLoader loader = new ItemLoaderXML();

    /*
     * Access point for the singleton instance of the catalog
     *
     * @return  the singleton instance
     */
    public static ItemCatalog getInstance() {
        if (instance == null) {
            synchronized (ItemCatalog.class) {
                if (instance == null) {
                    instance = new ItemCatalog();
                }
            }
        }
        return instance;
    }

    /*
     * loads the catalog from an XML file
     */
    private ItemCatalog() {
        loader.loadItemFromFile("src/main/java/org/bitbucket/mtriano/Data/ItemCatalog.xml");
        catalog = loader.getItems();
    }

    public Item getItem(String itemID) throws InvalidDataException {
        if (!isItem(itemID)) {
            throw new InvalidDataException("Invalid itemID passed to getItem");
        }
        for (Item item : catalog) {
            if (item.getID().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getCatalog() {
        return getInstance().catalog;
    }

    /*
     * Potentially useful later, it was stated that we'd need this, but I
     * didn't use it yet.  Perhaps later I'll work it into error handling
     *
     * @param  itemID     ID of an item
     * @return            boolean
     */
    public boolean isItem(String itemID) throws InvalidDataException {
        if (itemID == null) {
            throw new InvalidDataException("ItemID not valid (it's null).");
        }
        catalog = getCatalog();
        for (Item item : catalog) {
            if (item.getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Prints the formatted catalog output from the project specification
     */
    public void printCatalog() throws InvalidDataException {
        System.out.println("Item Catalog:");
        ArrayList<Item> catalog = getCatalog();
        for (int i = 0; i < catalog.size(); i++) {
            Item item = catalog.get(i);
            String itemID = item.getID();
            int cost = (int) item.getCost();
            if ( (i+1)%4 == 0 ) {
                System.out.printf("%-8s : $%-,10d%n", itemID, cost);
            } else {
                System.out.printf("%-8s : $%,-10d", itemID, cost);
            }
        }
    }

}
