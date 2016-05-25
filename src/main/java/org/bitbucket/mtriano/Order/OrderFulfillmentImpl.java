package org.bitbucket.mtriano.Order;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/21/2016.
 */
public class OrderFulfillmentImpl implements OrderFulfillment {

    private Order order;
    private ArrayList<ArrayList<FacilityRecord>> records;
    private ArrayList<Integer> costs;
    private Integer totalCost;

    //todo clean this up, figure out what you were doing with the arraylists of arraylists.
    public OrderFulfillmentImpl(Order filledOrder, ArrayList<ArrayList<FacilityRecord>> fillingRecords,
                                ArrayList<Integer> lineCosts) throws InvalidDataException {
        if (filledOrder == null) {
            throw new InvalidDataException("Null order passed to OrderFulfillmentImpl");
        }
        if (fillingRecords == null) {
            throw new InvalidDataException("Null fillingRecord passed to OrderFulfillmentImpl");
        }
        if (lineCosts == null) {
            throw new InvalidDataException("Null lineCosts passed to OrderFulfillmentImpl");
        }

        order = filledOrder;
        records = fillingRecords;
        costs = lineCosts;
        totalCost = totalOrderCost(costs);
    }

    private Integer firstDelivery() throws InvalidDataException {
        Integer earliestDelivery = 30;
        for (ArrayList<FacilityRecord> recordRecord : records) {
            for (FacilityRecord record : recordRecord) {
                if (record.getArrivalDay() < earliestDelivery) {
                    earliestDelivery = record.getArrivalDay();
                }
            }
        }
        return earliestDelivery;
    }

    private Integer firstDelivery(ArrayList<FacilityRecord> lineRecord) throws InvalidDataException {
        Integer earliestDelivery = 30;
        for (FacilityRecord record : lineRecord) {
            if (record.getArrivalDay() < earliestDelivery) {
                earliestDelivery = record.getArrivalDay();
            }
        }
        return earliestDelivery;
    }

    private Integer lastDelivery() throws InvalidDataException {
        Integer lastDelivery = 0;
        for (ArrayList<FacilityRecord> recordRecord : records) {
            for (FacilityRecord record : recordRecord) {
                if (record.getArrivalDay() > lastDelivery) {
                    lastDelivery = record.getArrivalDay();
                }
            }
        }
        return lastDelivery;
    }

    private Integer lastDelivery(ArrayList<FacilityRecord> lineRecord) throws InvalidDataException {
        Integer lastDelivery = 0;
        for (FacilityRecord record : lineRecord) {
            if (record.getArrivalDay() > lastDelivery) {
                lastDelivery = record.getArrivalDay();
            }
        }
        return lastDelivery;
    }

    @Override
    public void printOrderFulfillment() throws InvalidDataException {
        printOrderHeader();
        printSolution();
    }

    private void printOrderHeader() throws InvalidDataException {
        System.out.println("\u2022 Order ID:      " + order.getOrderID());
        System.out.println("\u2022 Order Time:    Day " + order.getStartDay());
        System.out.println("\u2022 Destination:   " + order.getDestination());
        ArrayList<Line> orderLines = order.getOrderLines();
        for (Line orderLine : orderLines) {
            System.out.println("  \u25E6 " + orderLine.getLineID() + ",     Quantity: " + orderLine.getLineQty());
        }
        System.out.println(" ");
    }

    private void printSolution() throws InvalidDataException {
        System.out.println("Processing Solution: ");
        System.out.printf("\u2022 Total Cost:      $%,01d %n", totalCost);
        System.out.println("\u2022 1st Delivery Day: " + firstDelivery());
        System.out.println("\u2022 Last Delivery Day: " + lastDelivery());
        System.out.println("\u2022 Order Items: ");
        printItems();

    }

    private void printItems() throws InvalidDataException {
//        System.out.println("  Item ID  Quantity  Cost        # Sources Used  First Day  Last Day");
        System.out.printf("  %-8s %-9s %-11s %-15s %-10s %-9s %n", "Item ID", "Quantity",
                "Cost", "# Sources Used", "First Day", "Last Day");
        ArrayList<Line> orderLines = order.getOrderLines();
        for (int i = 0; i < orderLines.size(); i++) {
            String id = orderLines.get(i).getLineID();
            Integer qty = orderLines.get(i).getLineQty();
            Integer qtyFilled = lineCompletelyFilled(records.get(i));
            Integer backorderQty = 0;
            Integer sources = records.get(i).size();
            Integer firstDay = firstDelivery(records.get(i));
            Integer lastDay = lastDelivery(records.get(i));
            if (qty > qtyFilled) {
                backorderQty = qty - qtyFilled;
                System.out.printf("  %-8s %-9d $%-,10d %-15d %-10d %-9d B/O Qty: %-6d %n", id,
                        qty, costs.get(i), sources, firstDay, lastDay, backorderQty);
            } else {
                System.out.printf("  %-8s %-9d $%-,10d %-15d %-10d %-5d %n", id,
                        qty, costs.get(i), sources, firstDay, lastDay);
            }
        }

    }

    private Integer lineCompletelyFilled(ArrayList<FacilityRecord> lineRecords) throws InvalidDataException {
        Integer qtyFilled = 0;
        for (FacilityRecord record : lineRecords) {
            qtyFilled += record.getFillQty();
        }
        return qtyFilled;
    }

    private Integer totalOrderCost(ArrayList<Integer> lineCosts) throws InvalidDataException {
        Integer totalCost = 0;
        for (Integer cost : lineCosts) {
            totalCost += cost;
        }
        return totalCost;
    }

    @Override
    public Integer getTotalOrderCost() throws InvalidDataException {
        return totalCost;
    }
}
