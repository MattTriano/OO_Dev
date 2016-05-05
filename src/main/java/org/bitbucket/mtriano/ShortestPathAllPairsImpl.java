package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Matt on 5/4/2016.
 */
public class ShortestPathAllPairsImpl implements ShortestPath {

    private ArrayList<Facility> network;
    private HashMap<ArrayList<Facility>, Integer> pairs = new HashMap<>();
    private HashSet<Facility> seen = new HashSet<>();
    private Facility startFacility;
    private Facility endFacility;

    public ShortestPathAllPairsImpl(Facility origin, Facility destination)
            throws InvalidDataException {

        if (origin == null) {
            throw new InvalidDataException("origin not valid (null origin entered)");
        }
        if (destination == null) {
            throw new InvalidDataException("dest not valid (null destination entered)");
        }
        this.network = FacilityNetwork.getInstance().getNetwork();
        this.startFacility = origin;
        this.endFacility = destination;
    }

// todo figure out the distance from init to neighbor
    public void mapPairs(Facility init) throws InvalidDataException {
        seen.add(init);
        for (Facility neighbor : FacilityNetwork.getInstance().
                getLinkedFacilities(init.getLinkedCities())) {
            ArrayList<Facility> pair = new ArrayList<>();
            pair.add(init);
            pair.add(neighbor);
            pairs.put(pair, linkDistance(init, neighbor));
            if (!seen.contains(neighbor)) {
                mapPairs(neighbor);
            }
        }
        System.out.println("Stuff");
    }

    // Gets the distance between 2 facilities
    // todo: check to make sure the facilities are 1 degree apart
    private int linkDistance(Facility facilityA, Facility facilityB) throws InvalidDataException {
        int distance = -1;
        String facilityBID = facilityB.getCityID();
        for (LinkedCity linkedCity : facilityA.getLinkedCities()) {
            if (linkedCity.getCityID().equals(facilityBID)) {
                distance = linkedCity.getDistance();
            }
        }
        return distance;
    }

    public ArrayList<Facility> shortestPath(Facility originFac,
                                            Facility destinationFac) {
        return null;

    }

    public ArrayList<String> getPathCities() {
        return null;
    }

    public Integer getPathDistance() {
        return null;
    }










}
