package org.bitbucket.mtriano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Matt on 5/4/2016.
 */
public class ShortestPathAllPairsImpl implements ShortestPath {

    private ArrayList<Facility> network;
    private HashMap<ArrayList<Facility>, Integer> pairs;
    private HashSet<Facility> seen;
    private Facility startFacility;
    private Facility endFacility;

    public ShortestPathAllPairsImpl(ArrayList<Facility> network, Facility origin,
                                    Facility destination) throws InvalidDataException {
        if (network == null) {
            throw new InvalidDataException("network not valid (null network entered)");
        }
        if (origin == null) {
            throw new InvalidDataException("origin not valid (null origin entered)");
        }
        if (destination == null) {
            throw new InvalidDataException("dest not valid (null destination entered)");
        }
        this.network = network;
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
            pairs.put(pair, neighbor.get)

        }
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
