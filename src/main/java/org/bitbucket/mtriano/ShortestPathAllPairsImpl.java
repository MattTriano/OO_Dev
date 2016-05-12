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
    private ArrayList<Facility> lowPath;
    private Integer lowPathLength;   // initializing to 'infinity'
    private Facility startFacility;
    private Facility endFacility;

    /*
     * Implements the ShortestPath interface with the AllPairs algorithm
     *
     * @param  origin          The origin Facility
     * @param  destination     The destination Facility
     */
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

    /*
     * This method loads all possible connections between adjacent cities in the network
     *
     * @param  init     the initial city in a search
     */
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

    /*
     * Gets the distance between 2 directly linked facilities
     *
     * @param  facilityA     the first link in the pair
     * @param  facilityB     the second link in the pair
     * @return               the distance between the facilities
     */
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

    /*
     * the findPath method described on the AllPairs example provided by Prof Hield
     *
     * @param  start        start Facility
     * @param  end          end Facility
     * @param  pathList     list of facilities already on the path from start
     */
    public void findPath(Facility start, Facility end, ArrayList<Facility> pathList)
            throws InvalidDataException {
        if (start.equals(end)) {
            int pathLength = pathDistance(pathList);
            if (pathLength < lowPathLength) {
                lowPath = pathList;
                lowPathLength = pathLength;
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

    /*
     * returns the total length of a path
     *
     * @param  pathList     list of Facilities on a path
     * @return              the sum of the distances between Facilities on the path
     */
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

    /*
     * the findBestPath method described on the AllPairs example provided by Prof Hield
     *
     * @param  start        start Facility
     * @param  end          end Facility
     */
    public ArrayList<Facility> findBestPath(Facility start, Facility end) throws InvalidDataException {
        mapPairs(start);
        seen = new HashSet<>();
        lowPath = new ArrayList<>();
        lowPathLength = Integer.MAX_VALUE;
        ArrayList<Facility> pathList = new ArrayList<>();
        pathList.add(start);
        findPath(start, end, pathList);
        return lowPath;
    }

    /*
     * Getter for lowPath for this ShortestPath object
     */
    public ArrayList<Facility> getLowPath() throws InvalidDataException {
        return lowPath;
    }

    /*
     * gets the path length for the lowest path
     *
     * @param  path     makes sure a path exists to insure a lowPath has been set
     * @return          length of the shortest path
     */
    public Integer getPathDistance(ArrayList<Facility> path)
            throws InvalidDataException {
        if (path == null) {
            throw new InvalidDataException("lowPath is null! It has no distance!");
        }
        return pathDistance(lowPath);
    }

    /*
     * Calculates the travel time for a given path
     *
     * @param  path     The path to be traveled
     * @param  speed    The speed at which the transporter travels
     * @return          number of days needed to travel that path
     */
    public Double getTravelTime(ArrayList<Facility> path, int speed) throws InvalidDataException {
        if (speed < 0) {
            throw new InvalidDataException("Negative speed entered! Illegal!");
        }
        return (double) getPathDistance(path)/ (8* speed);
    }
}
