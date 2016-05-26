package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Factory for OrderFulfillment objects
 */
public class OrderFulfillmentFactory {
    public static OrderFulfillment createOrderFulfillment(Order filledOrder,
                                         ArrayList<ArrayList<FacilityRecord>> fillingRecords,
                                         ArrayList<Integer> lineCosts)
            throws InvalidDataException {
        return new OrderFulfillmentImpl(filledOrder, fillingRecords, lineCosts);

    }
}
