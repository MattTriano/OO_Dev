package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.Order.FacilityRecord;
import org.bitbucket.mtriano.Order.FacilityRecordFactory;
import org.bitbucket.mtriano.Order.Line;
import org.bitbucket.mtriano.Order.Order;
import org.bitbucket.mtriano.ShortestPath.ShortestPath;
import org.bitbucket.mtriano.ShortestPath.ShortestPathAllPairsImpl;
import org.bitbucket.mtriano.ShortestPath.ShortestPathAllPairsImplFactory;

import java.util.ArrayList;

/**
 * Created by Matt on 5/18/2016.
 */
public final class OrderHandler {

    private static volatile OrderHandler instance;
    private static OrderLoader loader = new OrderReaderXML();
    private static ArrayList<Order> orderList;
    private static ArrayList<FacilityRecord> masterRecords = new ArrayList<>();
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

    public void processOrders() throws InvalidDataException {
        ArrayList<Order> orderList = getOrderList();
        for (Order order : orderList) {
            processOrder(order);
        }

    }

    //todo finish
    public void processOrder(Order order) throws InvalidDataException{
        ArrayList<Line> orderLines = order.getOrderLines();
        for (Line line : orderLines) {
            ArrayList<FacilityRecord> records = processLineFRGenerator(order, line);
            ArrayList<FacilityRecord> sortedRecords = recordSorter(records);
            Integer remainingQty = line.getLineQty();
            for (FacilityRecord sortedRec : sortedRecords) {
                if (remainingQty <= 0){
                    break;
                } else if (remainingQty > sortedRec.getQtyAvailable()) {
                    Integer tempQty = sortedRec.getQtyAvailable();
                    sortedRec.getFacility().scheduleProduction(line,
                            order.getStartDay(), sortedRec.getQtyAvailable());
                    remainingQty -= tempQty;
                    System.out.println("Just prepared " + tempQty + " of " + line.getLineID());
                } else {
                    sortedRec.getFacility().scheduleProduction(line,
                            order.getStartDay(), remainingQty);
                    remainingQty = 0;
                }
                masterRecords.add(sortedRec);
            }
        }
    }

    public ArrayList<FacilityRecord> recordSorter(ArrayList<FacilityRecord> unsorted)
            throws InvalidDataException {
        ArrayList<FacilityRecord> sortedList = new ArrayList<>();
        for (int i = 0; i < unsorted.size(); i++) {
            FacilityRecord minRec = minRecord(unsorted);
            sortedList.add(minRec);
            unsorted.remove(minRec);
        }
        return sortedList;
    }

    public FacilityRecord minRecord(ArrayList<FacilityRecord> unsorted)
            throws InvalidDataException {
        Integer minArrivalDay = Integer.MAX_VALUE;
        FacilityRecord minRec = unsorted.get(0);
        for (FacilityRecord rec : unsorted) {
            if (rec.getArrivalDay() < minArrivalDay) {
                minRec = rec;
                minArrivalDay = rec.getArrivalDay();
            }
        }
        return minRec;
    }

    public ArrayList<FacilityRecord> processLineFRGenerator(Order order, Line line)
            throws InvalidDataException {
        ArrayList<Facility> sources = itemSource(order.getDestination(),
                line.getLineID());
        Facility destination = net.getFacility(order.getDestination());
        Integer itemQty = line.getLineQty();
        String itemID = line.getLineID();
        ArrayList<FacilityRecord> records = new ArrayList<>();
        if (!sources.isEmpty()) {
            for (Facility facility : sources) {
                ShortestPath path = ShortestPathAllPairsImplFactory
                        .CreateShortestPath(facility, destination);
                Integer travelTime = (int) Math.ceil(path.getTravelTime(path.getLowPath(), 50));
                Integer facilityItemQty = facility.getInventory().getStockQty(itemID);
                Integer processingTime = (int) Math.ceil((facilityItemQty/facility.getRate()));
                Integer start = order.getStartDay();
                records.add(FacilityRecordFactory.createFacilityRecord(facility,
                        start, start + processingTime, travelTime, line));
            }
        }
        return records;
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
            if (facility.hasItem(itemID) && facility.itemInStock(itemID)) {
                sources.add(facility);
            }
        }
        Facility dest = net.getFacility(destination);
        if (sources.contains(dest)) {
            sources.remove(dest);
        }
        return sources;
    }

    private static double round(double value, int decimalPlaces) {
        int scalingFactor = (int) Math.pow(10, decimalPlaces);
        return (double) Math.round(value * scalingFactor) / scalingFactor;
    }
}
