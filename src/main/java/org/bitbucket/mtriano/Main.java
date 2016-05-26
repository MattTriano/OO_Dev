package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Item.Item;
import org.bitbucket.mtriano.Order.Order;
import org.bitbucket.mtriano.Singletons.FacilityNetwork;
import org.bitbucket.mtriano.Singletons.ItemCatalog;
import org.bitbucket.mtriano.Singletons.OrderHandler;

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
//            net.facilityStatus("Detroit, MI");
//            cat.printCatalog();

            // status precheck
            net.facilityStatus();
            //net.getFacility("Chicago, IL").getSchedule().printSchedule();
            int i = 1;
            for (Order order : orderList) {
                handler.processOrder(order, i);
                i++;
            }

            // status postcheck
            net.facilityStatus();


        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }
}
