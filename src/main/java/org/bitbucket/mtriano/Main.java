package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            ItemCatalog cat = ItemCatalog.getInstance();
            ArrayList<Item> catalog = ItemCatalog.getInstance().getCatalog();

            for (Item item : catalog) {
                System.out.println("Item " + item.getID() + " costs $" + item.getCost());
            }

            System.out.println(catalog.toString());
            System.out.println("Catalogs:");

        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }
}
