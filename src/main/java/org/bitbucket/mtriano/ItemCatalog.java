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
    private Map<String, String> mDict;
    private ArrayList<Item> catalog;
    private static Object sLock = new Object();
    private volatile static boolean sIsLoaded = false;

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
    }

    protected void loadItems() {
        mDict = new HashMap<String, String>();
        ItemLoader x = new ItemXMLLoader();
        x.loadItemFromFile("src/main/java/org/bitbucket/mtriano/ItemCatalog.xml");
        getInstance().catalog = x.getItems();
        System.out.println("words");
    }

//    private static Map<String, String> getItemDict() {
//        synchronized (sLock) {
//            if(!sIsLoaded) {
//                getInstance().loadItems();
//                sIsLoaded = true;
//            }
//            return getInstance().mDict;
//        }
//    }

    public ArrayList<Item> getCatalog() {
        return getInstance().catalog;
    }

//    boolean isItem(String itemID) {
//
//
//    }



}
