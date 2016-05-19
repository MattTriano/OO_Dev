package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.Order.Order;

import java.util.ArrayList;

/**
 * Created by Matt on 5/18/2016.
 */
public final class OrderHandler {

    private static volatile OrderHandler instance;
    private static OrderLoader loader = new OrderReaderXML();
    private static ArrayList<Order> orderList;
    FacilityNetwork net = FacilityNetwork.getInstance();

    public static OrderHandler getInstance() {
        if (instance == null) {
            synchronized (OrderHandler.class) {
                if (instance == null) {
                    instance = new OrderHandler();
                }
            }
        }
        return instance;
    }

    private OrderHandler() {
        loader.loadOrdersFromFile("src/main/java/org/bitbucket/mtriano/Data/" +
                "Orders.xml");
        orderList = loader.getOrderList();
    }

    public ArrayList<Order> getOrderList() {
        return getInstance().orderList;
    }

    public void processOrder(){
        int i = 0;
    }

    /* Makes a list of all facilities (except for the destination facility)
     * that has an item in inventory
     *
     * @param   destination     The facility the will receive the items
     * @param   itemID          The id of the desired item
     * @return                  A list of sources for that item
     */
    public ArrayList<Facility> itemSource(String destination, String itemID)
            throws InvalidDataException {
        ArrayList<Facility> network = net.getNetwork();
        ArrayList<Facility> sources = new ArrayList<>();
        for (Facility facility : network) {
            if (facility.hasItem(itemID)) {
                sources.add(facility);
            }
        }
        Facility dest = net.getFacility(destination);
        if (sources.contains(dest)) {
            sources.remove(dest);
        }
        return sources;
    }

}
