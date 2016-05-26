package org.bitbucket.mtriano.Order;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/** This class keeps track of the fulfillment of orders and handles the
 *  printing of solutions. *
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

    /*  Determines the first arrival day for an entire order
     *
     *  @return    The day of the first arrival of
     *              material from an order.
     */
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

    /*  Determines the first day of arrival for material for
     *  a single line on an order
     *
     *  @param  lineRecord     Takes the arraylist of FacilityRecords the
     *                           describes the filling solution for a line
     *  @return                The day of the first arrival of material
     *                           from this line
     */
    private Integer firstDelivery(ArrayList<FacilityRecord> lineRecord) throws InvalidDataException {
        Integer earliestDelivery = 30;
        for (FacilityRecord record : lineRecord) {
            if (record.getArrivalDay() < earliestDelivery) {
                earliestDelivery = record.getArrivalDay();
            }
        }
        return earliestDelivery;
    }

    /*  Determines the last arrival day for an entire order
     *
     *  @return    The day of the last arrival of
     *              material from an order.
     */
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

    /*  Determines the last day of arrival for material for
     *  a single line on an order
     *
     *  @param  lineRecord     Takes the arraylist of FacilityRecords the
     *                           describes the filling solution for a line
     *  @return                The day of the last arrival of material
     *                           from this line
     */
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
    /*  Public method that will print out this order fulfillment strategy
     */
    public void printOrderFulfillment() throws InvalidDataException {
        printOrderHeader();
        printSolution();
    }

    /*  Helper method that prints out the order header
     */
    private void printOrderHeader() throws InvalidDataException {
        System.out.println("\n\u2022 Order ID:      " + order.getOrderID());
        System.out.println("\u2022 Order Time:    Day " + order.getStartDay());
        System.out.println("\u2022 Destination:   " + order.getDestination());
        ArrayList<Line> orderLines = order.getOrderLines();
        for (Line orderLine : orderLines) {
            System.out.println("  \u25E6 " + orderLine.getLineID() + ",     Quantity: " + orderLine.getLineQty());
        }
        System.out.println(" ");
    }

    /*  Prints the header data for the fulfillment of the lines
     */
    private void printSolution() throws InvalidDataException {
        System.out.println("Processing Solution: ");
        System.out.printf("\u2022 Total Cost:      $%,01d %n", totalCost);
        System.out.println("\u2022 1st Delivery Day: " + firstDelivery());
        System.out.println("\u2022 Last Delivery Day: " + lastDelivery());
        System.out.println("\u2022 Order Items: ");
        printItems();
    }

    /*  Prints the information for the line items on this order
     */
    private void printItems() throws InvalidDataException {
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

    /*  Sums the number of items filled.  The is used to check if there
     *  should be a backorder or not.
     *
     *  @param  lineRecords     Takes the arraylist of FacilityRecords the
     *                           describes the filling solution for a line
     *  @return                 The number if items filled in lineRecords
     */
    private Integer lineCompletelyFilled(ArrayList<FacilityRecord> lineRecords)
            throws InvalidDataException {
        Integer qtyFilled = 0;
        for (FacilityRecord record : lineRecords) {
            qtyFilled += record.getFillQty();
        }
        return qtyFilled;
    }

    /*  Helper method that sums the costs of the lines.
     *
     *  @param lineCosts     A list of the total cost of each line
     *  @return              The sum of those costs
     */
    private Integer totalOrderCost(ArrayList<Integer> lineCosts) throws InvalidDataException {
        Integer totalCost = 0;
        for (Integer cost : lineCosts) {
            totalCost += cost;
        }
        return totalCost;
    }

    @Override
    /*  Returns the total cost for this order fulfillment.  Simple getter.
     *
     *  @return               totalCost
     */
    public Integer getTotalOrderCost() throws InvalidDataException {
        return totalCost;
    }
}
