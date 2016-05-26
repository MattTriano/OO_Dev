package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Interface for OrderFulfillment objects
 */
public interface OrderFulfillment {

    void printOrderFulfillment() throws InvalidDataException;
    Integer getTotalOrderCost() throws InvalidDataException;
}
