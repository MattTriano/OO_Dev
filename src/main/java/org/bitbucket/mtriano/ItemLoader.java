package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 4/28/2016.
 */
public interface ItemLoader {

    void loadItemFromFile(String filePath);
    ArrayList<Item> getItems();

}


