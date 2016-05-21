package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/21/2016.
 */
public interface OrderFulfillment {

//    String getOrderID() throws InvalidDataException;
//    Integer getOrderStartDay() throws InvalidDataException;
//    String getDestination() throws InvalidDataException;
//    ArrayList<>

    void printOrderFulfillment() throws InvalidDataException;
    Integer getTotalOrderCost() throws InvalidDataException;
}
