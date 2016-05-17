package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Item.Item;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ItemCatalog cat = ItemCatalog.getInstance();
            ArrayList<Item> catalog = ItemCatalog.getInstance().getCatalog();

            FacilityNetwork net = FacilityNetwork.getInstance();
//            ArrayList<Facility> network = FacilityNetwork.getInstance().getNetwork();


            net.facilityStatus("Detroit, MI");
            cat.printCatalog();
            net.shortestPathTest();


        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }
}
