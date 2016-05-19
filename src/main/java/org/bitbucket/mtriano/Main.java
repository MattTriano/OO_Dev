package org.bitbucket.mtriano;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.bitbucket.mtriano.Item.Item;
import org.bitbucket.mtriano.Order.Order;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            // Singletons
            ItemCatalog cat = ItemCatalog.getInstance();
            FacilityNetwork net = FacilityNetwork.getInstance();
            OrderHandler handler = OrderHandler.getInstance();

            // Lists
            ArrayList<Item> catalog = ItemCatalog.getInstance().getCatalog();
            ArrayList<Order> orderList = handler.getOrderList();

            // Tests
            net.facilityStatus("Detroit, MI");
            cat.printCatalog();
            net.shortestPathTest();
            net.getFacility("Chicago, IL").getSchedule().printSchedule();

        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }
}
