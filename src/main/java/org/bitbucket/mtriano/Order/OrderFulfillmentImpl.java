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
    }

    private void printSolution() throws InvalidDataException {
        System.out.println("Processing Solution: ");
        System.out.printf("\u2022 Total Cost:      $%,01d %n", totalCost);

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
