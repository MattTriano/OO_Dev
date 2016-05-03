package org.bitbucket.mtriano;

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
 * Much was taken from the XMLReader that Prof. Hield provided
 */
public class FacilityNetworkXMLReader {

    public static void main(String[] args) {

        try {
            String fileName = "src/org/bitbucket/mtriano/FacilityNetwork.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facilityEntries.getLength(); i++) {
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) { continue; }

                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String facilityCity = aMap.getNamedItem("City").getNodeValue();
                Element elem = (Element) facilityEntries.item(i);

                String facilityRate = elem.getElementsByTagName("Rate").item(0).getTextContent();
                String facilityCost = elem.getElementsByTagName("Cost").item(0).getTextContent();
//                System.out.println("facilityRate: " + facilityRate);
//                System.out.println("facilityCost: " + facilityCost);
//                System.out.println("facilityCity contents: " + facilityCity.toString());

                ArrayList<Item> stockedItems = new ArrayList<>();
                NodeList itemList = elem.getElementsByTagName("Item");
                for (int j = 0; j < itemList.getLength(); j++) {
                    if (itemList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = itemList.item(j).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    elem = (Element) itemList.item(j);
                    String itemID = elem.getElementsByTagName("ItemID").item(0).getTextContent();
                    int itemQty = Integer.parseInt(elem.getElementsByTagName("Quantity").item(0).getTextContent());
                    Item inventoryItem = ItemImplFactory.createItem(itemID, itemQty);
                    stockedItems.add(inventoryItem);
                }

//                for (int j = 0; j < itemList.getLength(); j++) {
//                    System.out.println("ItemID:  " + stockedItems.get(j).getID());
//                    System.out.println("ItemQty: " + stockedItems.get(j).getQty());
//                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | InvalidDataException | DOMException e) {
            e.printStackTrace();
        }
    }
}
