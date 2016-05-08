package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Matt on 5/4/2016.
 */
public class ShortestPathAllPairsImpl implements ShortestPath {

    private ArrayList<Facility> network;
    private HashMap<Pair, Integer> pairs = new HashMap<>();
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

    // This method loads all possible connections between adjacent cities in the network
    //todo delete this if the new thing works
//    public void mapPairs(Facility init) throws InvalidDataException {
//        seen.add(init);
//        ArrayList<Facility> linkedFacs = FacilityNetwork.getInstance().
//                getLinkedFacilities(init.getLinkedCities());
//        for (Facility neighbor : linkedFacs) {
//            ArrayList<Facility> pair = new ArrayList<>();
//            pair.add(init);
//            pair.add(neighbor);
//            pairs.put(pair, linkDistance(init, neighbor));
//            if (!seen.contains(neighbor)) {
//                mapPairs(neighbor);
//            }
//        }
//    }

    public void mapPairs(Facility init) throws InvalidDataException {
        seen.add(init);
        ArrayList<Facility> linkedFacs = FacilityNetwork.getInstance().
                getLinkedFacilities(init.getLinkedCities());
        for (Facility neighbor : linkedFacs) {
            Pair pair = PairImplFactory.createPairImpl(init, neighbor);
            pairs.put(pair, pair.getDistance());
            if (!seen.contains(neighbor)) {
                mapPairs(neighbor);
            }
        }
    }

//    // Gets the distance between 2 facilities
//    private int linkDistance(Facility facilityA, Facility facilityB) throws InvalidDataException {
//        int distance = -1;
//        String facilityBID = facilityB.getCityID();
//        for (LinkedCity linkedCity : facilityA.getLinkedCities()) {
//            if (linkedCity.getCityID().equals(facilityBID)) {
//                return linkedCity.getDistance();
//            }
//        }
//        return distance;
//    }

//    public ArrayList<Facility> findPath(Facility start, Facility end, ArrayList<Facility> pathList) {
//
//    }

//    public ArrayList<Facility> findBestPath(Facility start, Facility end) throws InvalidDataException {
//        mapPairs(start);
//        seen = new HashSet<>();
//        ArrayList<>
//
//    }

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
