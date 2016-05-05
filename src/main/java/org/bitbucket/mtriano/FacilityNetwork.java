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
        System.out.println("loaded");
    }

    public ArrayList<Facility> getNetwork() {
        return getInstance().network;
    }

    public Facility getFacility(String cityID) throws InvalidDataException {
        for (Facility facility : network) {
            if (facility.getCityID().equals(cityID)) {
                return facility;
            }
        }
        return null;
    }

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

