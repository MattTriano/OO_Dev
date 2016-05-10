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

    public void facilityStatus(String cityID) throws InvalidDataException {
        facilityStatus(getFacility(cityID));
    }

    public void facilityStatus(Facility facility) throws InvalidDataException {
        String cityID = facility.getCityID();
        int rate = facility.getRate();
        int cost = facility.getCost();
        ArrayList<LinkedCity> linkedCities = facility.getLinkedCities();
        ArrayList<Stock> inventory = facility.getInventory();

        printCityID(cityID);
        System.out.println("Rate per Day: " + rate);
        System.out.println("Cost per Day: " + cost + ".0");
        System.out.println(" ");
        printLinkedCities(linkedCities);
        printInventory(inventory);
        printSchedule(rate);
    }

    private void printCityID(String cityID) throws InvalidDataException {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(cityID);
        for (int i = 0; i < cityID.length(); i++){
            System.out.print("-");
        }
        System.out.print("\n");
    }

    private static double round(double value, int decimalPlaces) {
        int scalingFactor = (int) Math.pow(10, decimalPlaces);
        return (double) Math.round(value * scalingFactor) / scalingFactor;
    }

    private void printLinkedCities(ArrayList<LinkedCity> linkedCities)
            throws InvalidDataException {
        System.out.println("Direct Links:");
        for (LinkedCity city : linkedCities) {
            double days = city.getDistance() / 400;
            System.out.print(city.getCityID() + " (" + round(days, 1) + "d); ");
        }
        System.out.println(" ");
    }

    private void printSchedule(int rate) throws InvalidDataException {
        System.out.println("Depleted (Used-Up) Inventory: None \n");
        System.out.println("Schedule:");
        System.out.print("Day:        ");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print("\nAvailable:  ");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%3d", 10);
        }
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------------------------");
    }

    private void printInventory(ArrayList<Stock> inventory)
            throws InvalidDataException {
        System.out.println("Active Inventory:");
        System.out.println("    Item ID     Quantity");
        for (Stock item : inventory) {
            System.out.printf("    %-11s %-9d %n", item.getID(), item.getQuantity());
        }
        System.out.println(" ");
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
        ArrayList<Facility> lowPath = getShortestPath(startFacility, endFacility);
//        ShortestPath path = ShortestPathAllPairsImplFactory
//                .CreateShortestPath(startFacility, endFacility);
//        ArrayList<Facility> lowPath = path.findBestPath(startFacility, endFacility);
        return lowPath;
    }

    public ArrayList<Facility> getShortestPath(Facility start, Facility end)
            throws InvalidDataException {
        ShortestPath path = ShortestPathAllPairsImplFactory.
                CreateShortestPath(start, end);
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

