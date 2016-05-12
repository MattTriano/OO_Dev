package org.bitbucket.mtriano;

import javax.naming.LinkLoopException;
import java.util.ArrayList;

/**
 * Implements a Pair object
 */
public class PairImpl implements Pair {

    private ArrayList<Facility> pair = new ArrayList<>();
    private Facility facilityA;
    private Facility facilityB;
    private int distance;

    public PairImpl(Facility facA, Facility facB) throws InvalidDataException {
        ArrayList<String> linkedIDs = new ArrayList<>();
        ArrayList<LinkedCity> links = facB.getLinkedCities();
        for (LinkedCity link : links) {
            linkedIDs.add(link.getCityID());
        }
        if (facA == null || facB == null) {
            throw new InvalidDataException("Null Facility passed to this pair");
        }
        if (!linkedIDs.contains(facA.getCityID())) {
            throw new InvalidDataException(facA.getCityID() +" is not directly linked to " + facB.getCityID());
        }
        facilityA = facA;
        facilityB = facB;
        distance = linkDistance();
        pair.add(facA);
        pair.add(facB);
    }

    private int linkDistance() throws InvalidDataException {
        int distance = -1;
        String facilityBID = facilityB.getCityID();
        for (LinkedCity linkedCity : facilityA.getLinkedCities()) {
            if (linkedCity.getCityID().equals(facilityBID)) {
                return linkedCity.getDistance();
            }
        }
        return distance;
    }

    public ArrayList<Facility> getPair() {
        return pair;
    }

    public Facility getFacilityA() {
        return facilityA;
    }

    public Facility getFacilityB() {
        return facilityB;
    }

    public int getDistance() {
        return distance;
    }
}
