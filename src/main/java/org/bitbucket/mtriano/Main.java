package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            ItemCatalog cat = ItemCatalog.getInstance();
            ArrayList<Item> catalog = ItemCatalog.getInstance().getCatalog();

            FacilityNetwork net = FacilityNetwork.getInstance();
            ArrayList<Facility> network = FacilityNetwork.getInstance().getNetwork();

            
            ArrayList<Facility> lowPath = net.getShortestPath("Boston, MA", "Phoenix, AZ");

            net.facilityStatus("Detroit, MI");
            cat.printCatalog();


        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }
}
