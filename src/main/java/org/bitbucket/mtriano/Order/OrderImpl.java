package org.bitbucket.mtriano.Order;

import org.bitbucket.mtriano.Singletons.FacilityNetwork;
import org.bitbucket.mtriano.InvalidDataException;

import java.util.ArrayList;

/**
 * Implements the Order interface
 */
public class OrderImpl implements Order {

    private Integer startDay;
    private String orderID;
    private String orderDestination;
    private ArrayList<Line> orderLines;
    private FacilityNetwork net = FacilityNetwork.getInstance();
    private ArrayList<String> facilities = net.facilityList();

    public OrderImpl(Integer day, String id, String destination,
                     ArrayList<Line> lines) throws InvalidDataException {
        if (day < 0) {
            throw new InvalidDataException("Invalid start day passed to OrderImpl");
        }
        if (id == null) {
            throw new InvalidDataException("Null orderID passed to OrderImpl");
        }
        if (destination == null || !facilities.contains(destination)) {
            throw new InvalidDataException("Invalid destination passed to ");
        }
        if (lines == null) {
            throw new InvalidDataException("Null orderLines ArrayList " +
                    "passed to OrderImpl");
        }

        startDay = day;
        orderID = id;
        orderDestination = destination;
        orderLines = lines;
    }

    public Integer getStartDay() throws InvalidDataException {
        return startDay;
    }

    public String getOrderID() throws InvalidDataException {
        return orderID;
    }

    public String getDestination() throws InvalidDataException {
        return orderDestination;
    }

    public Line getLine(String lineID) throws InvalidDataException {
        for (Line line : orderLines) {
            if (line.getLineID().equals(lineID)) {
                return line;
            }
        }
        return null;
    }

    public ArrayList<Line> getOrderLines() throws InvalidDataException {
        return orderLines;
    }
}
