package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

/**
 * Created by Matt on 5/21/2016.
 */
public class OrderFulfillmentFactory {
    public static OrderFulfillment createOrderFulfillment(Order filledOrder,
                                         ArrayList<ArrayList<FacilityRecord>> fillingRecords,
                                         ArrayList<Integer> lineCosts)
            throws InvalidDataException {
        return new OrderFulfillmentImpl(filledOrder, fillingRecords, lineCosts);

    }
}
