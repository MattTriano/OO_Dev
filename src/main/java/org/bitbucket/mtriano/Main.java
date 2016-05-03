package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ItemCatalog cat = ItemCatalog.getInstance();
        ArrayList<Item> catalog = ItemCatalog.getInstance().getCatalog();

//        for (Item item : catalog) {
//            System.out.println(item.get(0).toString());
//        }

        System.out.println(catalog.toString());
        System.out.println("Catalogs:");


    }
}
