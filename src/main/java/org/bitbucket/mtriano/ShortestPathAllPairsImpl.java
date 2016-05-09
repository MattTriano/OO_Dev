package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matt on 5/4/2016.
 */
public class ShortestPathAllPairsImpl implements ShortestPath {

    private ArrayList<Facility> network;
    private HashMap<Pair, Integer> pairs = new HashMap<>();
    private HashSet<Facility> seen = new HashSet<>();
    private ArrayList<Facility> lowPath = new ArrayList<>();
    private Integer lowPathLength = Integer.MAX_VALUE;   // initializing to 'infinity'
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
        startFacility = origin;
        endFacility = destination;
    }


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

    /* This method loads all possible connections between adjacent cities in the network
    * Param: Facility init, the initial city in an analysis*/
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

    // Gets the distance between 2 facilities
    private int linkDistance(Facility facilityA, Facility facilityB) throws InvalidDataException {
        int distance = -1;
        String facilityBID = facilityB.getCityID();
        for (LinkedCity linkedCity : facilityA.getLinkedCities()) {
            if (linkedCity.getCityID().equals(facilityBID)) {
                return linkedCity.getDistance();
            }
        }
        return distance;
    }

    // todo finish this up
    public void findPath(Facility start, Facility end, ArrayList<Facility> pathList)
            throws InvalidDataException {
        if (start.equals(end)) {
            int pathLength = pathDistance(pathList);
            if (pathLength < lowPathLength) {
                lowPath = pathList;
                lowPathLength = pathLength;
                System.out.println("Current shortest path is " + lowPathLength + " long.");
                return;
            }
        }
        HashSet<Pair> fromHere = new HashSet<>();
        Set<Pair> pairsKeySet = pairs.keySet();
        for (Pair pair : pairsKeySet) {
            if (pair.getFacilityA().equals(start)) {
                fromHere.add(pair);
            }
        }
        for (Pair pairFromHere : fromHere) {
            Facility facilityB = pairFromHere.getFacilityB();
            if (!pathList.contains(facilityB)) {
                ArrayList<Facility> newPath = (ArrayList<Facility>) pathList.clone();
                newPath.add(facilityB);
                findPath(facilityB, end, newPath);
            }
        }
    }

    private int pathDistance(ArrayList<Facility> pathList) throws InvalidDataException {
        if (pathList == null) {
            throw new InvalidDataException("Null pathList passed to pathDistance()");
        }
        int totalDistance = 0;
        for (int i = 0; i < pathList.size()-1; i++) {
            totalDistance += linkDistance(pathList.get(i), pathList.get(i+1));
        }
        return totalDistance;
    }

    public ArrayList<Facility> findBestPath(Facility start, Facility end) throws InvalidDataException {
        mapPairs(start);
        seen = new HashSet<>();
        ArrayList<Facility> pathList = new ArrayList<>();
        pathList.add(start);
        findPath(start, end, pathList);
        return lowPath;
    }

    public ArrayList<Facility> shortestPath(Facility originFac,
                                            Facility destinationFac) {
        return lowPath;

    }

    public ArrayList<Facility> getLowPath() throws InvalidDataException {
        return lowPath;
    }

    public ArrayList<String> getPathCities() {
        return null;
    }

    public Integer getPathDistance() {
        return null;
    }










}
