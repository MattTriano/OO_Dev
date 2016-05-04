package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Matt on 4/28/2016.
 */
public final class ItemCatalog {

    private static volatile ItemCatalog instance;
    private static ArrayList<Item> catalog;
    private static ItemLoader loader = new ItemLoaderXML();

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

    private ItemCatalog() {
        loader.loadItemFromFile("src/main/java/org/bitbucket/mtriano/ItemCatalog.xml");
        catalog = loader.getItems();
        System.out.println("loaded");
    }

    public ArrayList<Item> getCatalog() {
        return getInstance().catalog;
    }

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
}
