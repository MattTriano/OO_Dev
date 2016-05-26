package org.bitbucket.mtriano.XMLReaders;

import org.bitbucket.mtriano.Facility.Facility;
import org.bitbucket.mtriano.Facility.FacilityImplFactory;
import org.bitbucket.mtriano.Facility.Schedule;
import org.bitbucket.mtriano.Facility.ScheduleImplFactory;
import org.bitbucket.mtriano.FacilityLoader;
import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Inventory.Inventory;
import org.bitbucket.mtriano.Inventory.InventoryImplFactory;
import org.bitbucket.mtriano.Inventory.Stock;
import org.bitbucket.mtriano.Inventory.StockImplFactory;
import org.bitbucket.mtriano.LinkedCity.LinkedCity;
import org.bitbucket.mtriano.LinkedCity.LinkedCityImplFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Matt on 4/26/2016.
 * The XMLReader that Prof. Hield provided was used as a jumping off point
 */
public class FacilityNetworkXML implements FacilityLoader {

    ArrayList<Facility> facilityNetwork = new ArrayList<>();

    public void loadNetworkFromFile(String filePath) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(filePath);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + filePath + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < facilityEntries.getLength(); i++) {
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    throw new InvalidDataException("Unexpected node found: " + entryName);
                }

                Element elem = (Element) facilityEntries.item(i);
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String city = aMap.getNamedItem("City").getNodeValue();
                int rate = Integer.parseInt(elem.getElementsByTagName("Rate")
                        .item(0).getTextContent());
                int cost = Integer.parseInt(elem.getElementsByTagName("Cost")
                        .item(0).getTextContent().replace("$", ""));

                // Now I want to make a list of the stock in inventory in this city
                ArrayList<Stock> inventoryStock = new ArrayList<>();
                NodeList stockList = elem.getElementsByTagName("Item");
                for (int j = 0; j < stockList.getLength(); j++) {
                    if (stockList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = stockList.item(j).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    Element elemj = (Element) stockList.item(j);
                    String itemID = elemj.getElementsByTagName("ItemID").item(0)
                            .getTextContent();
                    int itemQty = Integer.parseInt(elemj.getElementsByTagName("Quantity")
                            .item(0).getTextContent());
                    inventoryStock.add(StockImplFactory.createStock(itemID, itemQty));
                }

                // Now I want to make a list of all of the cities that are
                // only 1 link (edge, in graph-speak) away from this city.
                //Element elem = (Element) facilityEntries.item(i);
                ArrayList<LinkedCity> linkedCities = new ArrayList<>();
                NodeList linkedCityList = elem.getElementsByTagName("Link");
                for (int k = 0; k < linkedCityList.getLength(); k++) {
                    if (linkedCityList.item(k).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkedCityList.item(k).getNodeName();
                    if (!entryName.equals("Link")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    Element elemK = (Element) linkedCityList.item(k);
                    String cityID = elemK.getElementsByTagName("City").item(0)
                            .getTextContent();
                    int cityDistance = Integer.parseInt(elemK.getElementsByTagName
                            ("Distance").item(0).getTextContent());
                    linkedCities.add(LinkedCityImplFactory.createLinkedCity(cityID,
                            cityDistance));
                }

                Inventory inventory = InventoryImplFactory.createInventory(inventoryStock);
                Schedule schedule = ScheduleImplFactory.createSchedule(20, rate);
                Facility local = FacilityImplFactory.createFacility(city, rate,
                        cost, inventory, linkedCities, schedule);
                facilityNetwork.add(local);
            }
        } catch (ParserConfigurationException | SAXException | IOException |
                DOMException | InvalidDataException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ArrayList<Facility> getFacilities() {
        return facilityNetwork;
    }
}