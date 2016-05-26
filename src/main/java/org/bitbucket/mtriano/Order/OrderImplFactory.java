package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Factory for Order objects
 */
public class OrderImplFactory {
    public static Order createOrder(Integer startDay, String orderID,
                                    String orderDestination,
                                    ArrayList<Line> orderLines)
            throws InvalidDataException {
        return new OrderImpl(startDay, orderID, orderDestination, orderLines);
    }
}
