package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Created by Matt on 5/17/2016.
 */
public interface Order {

    Integer getStartDay() throws InvalidDataException;
    String getOrderID() throws InvalidDataException;
    String getDestination() throws InvalidDataException;
    Line getLine(String lineID) throws InvalidDataException;
    ArrayList<Line> getOrderLines() throws InvalidDataException;
}
