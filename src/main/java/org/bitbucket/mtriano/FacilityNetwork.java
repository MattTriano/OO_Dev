package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/3/2016.
 */
public final class FacilityNetwork {

    private static volatile FacilityNetwork instance;
    private static ArrayList<Facility> network;
    private static FacilityLoader loader = new FacilityNetworkXML();

    public static FacilityNetwork getInstance() {
        if (instance == null) {
            synchronized (FacilityNetwork.class) {
                if (instance == null) {
                    instance = new FacilityNetwork();
                }
            }
        }
        return instance;
    }

    private FacilityNetwork() {
        loader.loadNetworkFromFile("src/main/java/org/bitbucket/mtriano/" +
                "FacilityNetwork.xml");
        network = loader.getFacilities();
    }

    public ArrayList<Facility> getNetwork() {
        return getInstance().network;
    }

    /* This  */
    public Facility getFacility(String cityID) throws InvalidDataException {
        for (Facility facility : network) {
            if (facility.getCityID().equals(cityID)) {
                return facility;
            }
        }
        return null;
    }

    public ArrayList<Facility> getShortestPath(String start, String end)
            throws InvalidDataException {
        Facility startFacility = getFacility(start);
        Facility endFacility   = getFacility(end);
        ShortestPath path = ShortestPathAllPairsImplFactory
                .CreateShortestPath(startFacility, endFacility);
        ArrayList<Facility> lowPath = path.findBestPath(startFacility, endFacility);
        return lowPath;
    }

    public ArrayList<Facility> getShortestPath(Facility start, Facility end)
            throws InvalidDataException {
        ShortestPath path = ShortestPathAllPairsImplFactory.CreateShortestPath(start, end);
        ArrayList<Facility> lowPath = path.findBestPath(start, end);
        return lowPath;
    }

    // i want to test mapPairs
//    public ArrayList<Facility> testShortestPath() throws InvalidDataException {
//        Facility start = getFacility("Detroit, MI");
//        Facility end = getFacility("San Francisco, CA");
//        ShortestPath path = ShortestPathAllPairsImplFactory.CreateShortestPath(start, end);
//        ArrayList<Facility> lowPath = path.findBestPath(start, end);
//        return lowPath;
//    }

    /* This is a helper method intended to convert the LinkedCity
       ArrayLists from Facility objects into ArrayLists of Facilities.
    */
    public ArrayList<Facility> getLinkedFacilities(ArrayList<LinkedCity> links)
            throws InvalidDataException{
        ArrayList<Facility> linkedFacilities = new ArrayList<>();
        for (LinkedCity city : links) {
            Facility facility = getFacility(city.getCityID());
            if (facility != null) {
                linkedFacilities.add(facility);
            }
        }
        return linkedFacilities;
    }
}

