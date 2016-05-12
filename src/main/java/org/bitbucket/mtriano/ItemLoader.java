package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Interface for an ItemLoader
 */
public interface ItemLoader {

    void loadItemFromFile(String filePath);
    ArrayList<Item> getItems();

}


