package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.Inventory.Stock;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.ShortestPath.ShortestPath;
import org.bitbucket.mtriano.ShortestPath.ShortestPathAllPairsImplFactory;

import java.util.ArrayList;

/**
 * Creates the network and is host to the methods for accessing and inspecting
 * the network
 */
public final class FacilityNetwork {

    private static volatile FacilityNetwork instance;
    private static ArrayList<Facility> network;
    private static FacilityLoader loader = new FacilityNetworkXML();

    /* Access point for the singleton instance of the network
     *
     * @return  the singleton instance
     */
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

    /* Loads the network
     */
    private FacilityNetwork() {
        loader.loadNetworkFromFile("src/main/java/org/bitbucket/mtriano/Data/" +
                "FacilityNetwork.xml");
        network = loader.getFacilities();
    }

    /* Controls access to the network
     *
     * @return  the network instance
     */
    public ArrayList<Facility> getNetwork() {
        return getInstance().network;
    }

    public boolean isValidFacility(Facility facility) throws InvalidDataException {
        return network.contains(facility);
    }

    /*  Wrapper method to allow a String of the cityID to print
     *  the formatted facility status output from the project specification
     *
     *  @param  cityID     The cityID of the Facility of interest
     */
    public void facilityStatus(String cityID) throws InvalidDataException {
        facilityStatus(getFacility(cityID));
    }

    public void facilityStatus() throws InvalidDataException {
        ArrayList<String> cityIDs = facilityList();
        for (String cityID : cityIDs) {
            facilityStatus(cityID);
        }
    }

    /* Prints the formatted facility status output from the project specification
     *
     * @param  facility     The Facility object we want the status of
     */
    public void facilityStatus(Facility facility) throws InvalidDataException {
        String cityID = facility.getCityID();
        int rate = facility.getRate();
        int cost = facility.getCost();
        ArrayList<LinkedCity> linkedCities = facility.getLinkedCities();
        Inventory inventory = facility.getInventory();

        printCityID(cityID);
        System.out.println("Rate per Day: " + rate);
        System.out.println("Cost per Day: " + cost + ".0");
        System.out.println(" ");
        printLinkedCities(linkedCities);
        printInventory(inventory);
        facility.printSchedule();
    }

    /* Prints the cityID with an equal length of underscores
     *
     * @param  cityID     The cityID to be printed
     */
    private void printCityID(String cityID) throws InvalidDataException {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(cityID);
        for (int i = 0; i < cityID.length(); i++){
            System.out.print("-");
        }
        System.out.print("\n");
    }

    /* A helper method that replicates the single decimal point format
     *     in the problem specification
     *
     * @param   value          The value to be rounded
     * @param   decimalPlaces  The number of decimal places to round to
     * @return                 The rounded value
     */
    private static double round(double value, int decimalPlaces) {
        int scalingFactor = (int) Math.pow(10, decimalPlaces);
        return (double) Math.round(value * scalingFactor) / scalingFactor;
    }

    /* Prints a list of the cities linked to a facility and the time
     *     needed to transport material to those cities
     *
     * @param  linkedCities     Takes an ArrayList of the cities
     *                           linked to a Facility
     */
    private void printLinkedCities(ArrayList<LinkedCity> linkedCities)
            throws InvalidDataException {
        System.out.println("Direct Links:");
        for (LinkedCity city : linkedCities) {
            double days = Math.ceil((double)city.getDistance() / 400);
            System.out.print(city.getCityID() + " (" + days + "d); ");
        }
        System.out.println(" ");
    }

    /* Prints the production schedule for a facility
     *
     * @param  rate     The number of items a facility can produce in a day
     */
    private void printSchedule(int rate) throws InvalidDataException {
        System.out.println("Depleted (Used-Up) Inventory: None \n");
        System.out.println("Schedule:");
        System.out.print("Day:        ");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print("\nAvailable:  ");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%3d", rate);
        }
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------------------------");
    }

    /* Prints the inventory at a facility
     *
     * @param  inventory     An ArrayList of the stock in inventory at a facility
     */
    private void printInventory(Inventory inventory)
            throws InvalidDataException {
        System.out.println("Active Inventory:");
        System.out.println("    Item ID     Quantity");
        ArrayList<Stock> stockOnHand = inventory.getInventory();
        for (Stock item : stockOnHand) {
            System.out.printf("    %-11s %-9d %n", item.getID(), item.getQuantity());
        }
        System.out.println(" ");
    }

    /* Wrapper method to use Strings of the IDs to print the shortest path
     *
     * @param  start  The ID of the first Facility in the path
     * @param  end    The ID of the last Facility in the path
     */
    private void printShortestPath(String start, String end) throws InvalidDataException {
        Facility startFac = getFacility(start);
        Facility endFac = getFacility(end);
        printShortestPath(startFac, endFac);
    }

    /* Prints the formatted output for a shortest path between two facilities
     *
     * @param  start  The ID of the first Facility in the path
     * @param  end    The ID of the last Facility in the path
     */
    private void printShortestPath(Facility start, Facility end) throws InvalidDataException {
        ShortestPath path = ShortestPathAllPairsImplFactory.
                CreateShortestPath(start, end);
        path.findBestPath(start, end);
        ArrayList<Facility> lowPath = path.getLowPath();
        Integer pathDistance = path.getPathDistance(lowPath);
        Double travelTime = round(path.getTravelTime(lowPath, 50),2) ;
        System.out.println(start.getCityID() + " to " + end.getCityID() + ":");
        System.out.print("    - ");
        for (Facility facility : lowPath) {
            System.out.print(facility.getCityID());
            if (!facility.getCityID().equals(end.getCityID())) {
                System.out.print("->");
            }
        }
        System.out.printf(" = %,01d mi%n", (int)pathDistance);
        System.out.printf("    - %,01d", (int)pathDistance);
        System.out.print(" mi / (8 hours per day * 50 mph) = "
                + travelTime + " days");
    }

    /* Tester method that produces the outputs required
     * by the problem specification
     */
    public void shortestPathTest() throws InvalidDataException {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("Santa Fe, NM");
        testList.add("Chicago, IL");
        testList.add("Atlanta, GA");
        testList.add("St. Louis, MO");
        testList.add("Seattle, WA");
        testList.add("Nashville, TN");
        testList.add("New York City, NY");
        testList.add("Phoenix, AZ");
        testList.add("Fargo, ND");
        testList.add("Austin, TX");
        testList.add("Denver, CO");
        testList.add("Miami, FL");
        testList.add("Austin, TX");
        testList.add("Norfolk, VA");
        testList.add("Miami, FL");
        testList.add("Seattle, WA");
        testList.add("Los Angeles, CA");
        testList.add("Chicago, IL");
        testList.add("Detroit, MI");
        testList.add("Nashville, TN");
        System.out.println("Shortest Path Tests:");
        System.out.println(" ");

        int j = 65;
        for (int i = 0; i < 20; i=i+2) {
            System.out.print((char)(j+32) + ") ");
            printShortestPath(testList.get(i), testList.get(i+1));
            System.out.println("\n ");
            j++;
        }
    }

    /* This looks up a facility by its cityID
     *
     * @param   cityID      The ID of the desired facility
     * @return              The Facility object with that ID
     */
    public Facility getFacility(String cityID) throws InvalidDataException {
        ArrayList<String> facilities = facilityList();
        if (!facilities.contains(cityID)) {
            throw new InvalidDataException("Invalid facility name entered");
        }
        for (Facility facility : network) {
            if (facility.getCityID().equals(cityID)) {
                return facility;
            }
        }
        return null;
    }

    /* This is a wrapper method that returns the shortest path given
     * strings of the start and end facilities
     *
     * @param  start  The ID of the first Facility in the path
     * @param  end    The ID of the last Facility in the path
     * @return        Returns an ordered ArrayList of the facilities
     *                  in the shortest path
     */
    public ArrayList<Facility> getShortestPath(String start, String end)
            throws InvalidDataException {
        Facility startFacility = getFacility(start);
        Facility endFacility   = getFacility(end);
        ArrayList<Facility> lowPath = getShortestPath(startFacility, endFacility);
        return lowPath;
    }

    /* returns the shortest path given the start and end facilities
     *
     * @param  start  The ID of the first Facility in the path
     * @param  end    The ID of the last Facility in the path
     * @return        Returns an ordered ArrayList of the facilities
     *                  in the shortest path
     */
    public ArrayList<Facility> getShortestPath(Facility start, Facility end)
            throws InvalidDataException {
        ShortestPath path = ShortestPathAllPairsImplFactory.
                CreateShortestPath(start, end);
        path.findBestPath(start, end);
        return path.getLowPath();
    }

    /* Produces a list of the IDs of all facilities loaded into the network
     * @return      An ArrayList of Strings of the IDs of facilities in the network
     */
    public ArrayList<String> facilityList() throws InvalidDataException {
        ArrayList<String> facList = new ArrayList<>();
        ArrayList<Facility> net = network;
        for (Facility facility : net) {
            facList.add(facility.getCityID());
        }
        return facList;
    }

    /* This is a helper method intended to convert the LinkedCity
     * ArrayLists from Facility objects into ArrayLists of Facilities.
     *
     * @param   links     An ArrayList of LinkedCity objects
     * @return            An ArrayList of facilities linked to the host facility
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

