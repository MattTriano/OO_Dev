package org.bitbucket.mtriano.Item;

import org.bitbucket.mtriano.Item.Item;

import java.util.ArrayList;

/**
 * Interface for an ItemLoader
 */
public interface ItemLoader {

    void loadItemFromFile(String filePath);
    ArrayList<Item> getItems();

}


