package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Interface for Order objects
 */
public interface Order {

    Integer getStartDay() throws InvalidDataException;
    String getOrderID() throws InvalidDataException;
    String getDestination() throws InvalidDataException;
    Line getLine(String lineID) throws InvalidDataException;
    ArrayList<Line> getOrderLines() throws InvalidDataException;
}
