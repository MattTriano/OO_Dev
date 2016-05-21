package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.Order.*;
import org.bitbucket.mtriano.ShortestPath.ShortestPath;
import org.bitbucket.mtriano.ShortestPath.ShortestPathAllPairsImplFactory;

import java.util.ArrayList;

/**
 * Created by Matt on 5/18/2016.
 */
public final class OrderHandler {

    private static volatile OrderHandler instance;
    private static OrderLoader loader = new OrderReaderXML();
    private static ArrayList<Order> orderList;
    private static ArrayList<ArrayList<FacilityRecord>> masterRecords = new ArrayList<>();
    FacilityNetwork net = FacilityNetwork.getInstance();
    ItemCatalog catalog = ItemCatalog.getInstance();

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
        ArrayList<Integer> lineCosts = new ArrayList<>();
        ArrayList<ArrayList<FacilityRecord>> orderSolution = new ArrayList<>();
        for (Line line : orderLines) {
            ArrayList<FacilityRecord> records = processLineFRGenerator(order, line);
            ArrayList<FacilityRecord> sortedRecords = recordSorter(records);
            Integer remainingQty = line.getLineQty();
            ArrayList<FacilityRecord> lineSolution = new ArrayList<>();
            Integer itemCost = (int) catalog.getItem(line.getLineID()).getCost();
            for (FacilityRecord sortedRec : sortedRecords) {
                if (remainingQty <= 0){
                    break;
                } else if (remainingQty > sortedRec.getFillQty()) {
                    Integer tempQty = sortedRec.getFillQty();
                    sortedRec.getFacility().scheduleProduction(line,
                            order.getStartDay(), sortedRec.getFillQty());
                    remainingQty -= tempQty;
                    System.out.println("Just prepared " + tempQty + " of " + line.getLineID());
                } else {
                    Facility facility = sortedRec.getFacility();
                    facility.scheduleProduction(line, order.getStartDay(), remainingQty);
                    Integer processingTime = (int) Math.ceil((remainingQty/facility.getRate()));
                    Integer startDay = sortedRec.getStartDay();
                    sortedRec = FacilityRecordFactory.createFacilityRecord(facility, remainingQty,
                            startDay, startDay + processingTime, sortedRec.getTravelTime());
                    remainingQty = 0;
                }
                lineSolution.add(sortedRec);
            }
            orderSolution.add(lineSolution);
            lineCosts.add(lineCost(itemCost, lineSolution));
            System.out.println("");
        }

        OrderFulfillment fill = OrderFulfillmentFactory.createOrderFulfillment(order, orderSolution, lineCosts);
        fill.printOrderFulfillment();
    }

    public Integer lineCost(Integer itemCost, ArrayList<FacilityRecord> solutionRecords)
            throws InvalidDataException {
        int totalItemCost = 0;
        int totalProcessingCost = 0;
        int totalTravelCost = 0;
        for (FacilityRecord record : solutionRecords) {
            totalItemCost += itemCost * record.getFillQty();
            totalProcessingCost += record.getFacility().getCost() * record.getProcessingTime();
            totalTravelCost += 500 * record.getTravelTime();
        }
        return totalItemCost + totalProcessingCost + totalTravelCost;
    }

    /*  Sorts FacilityRecords by arrivalDay (lowest to highest)
     *  
     */
    public ArrayList<FacilityRecord> recordSorter(ArrayList<FacilityRecord> unsorted)
            throws InvalidDataException {
        ArrayList<FacilityRecord> sortedList = new ArrayList<>();
        int numRecords = unsorted.size();
        for (int i = 0; i < numRecords; i++) {
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



    /*  generates the FacilityRecords that satisfy a line on an order
     *
     */
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
                records.add(FacilityRecordFactory.createFacilityRecord(facility, facilityItemQty,
                        start, start + processingTime, travelTime));
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
