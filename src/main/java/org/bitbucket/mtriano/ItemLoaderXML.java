package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Item.Item;
import org.bitbucket.mtriano.Item.ItemImplFactory;
import org.bitbucket.mtriano.Item.ItemLoader;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The XMLReader that Prof. Hield provided was used as a jumping off point
 */
public class ItemLoaderXML implements ItemLoader {

    ArrayList<Item> itemCatalog = new ArrayList<>();
    String defaultFilepath = "src/main/java/org/bitbucket/mtriano/Data/ItemCatalog.xml";

    public void loadItemFromFile(String filePath) {
        try {
            //String filePath = "src/main/java/org/bitbucket/mtriano/Data/ItemCatalog.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(filePath);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + filePath + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            NodeList itemEntries = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < itemEntries.getLength(); i++) {
                if (itemEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = itemEntries.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    throw new InvalidDataException("Unexpected node found: " + entryName);
                }

                Element elem = (Element) itemEntries.item(i);
                String itemID = elem.getElementsByTagName("ItemID").item(0).getTextContent();
                int itemCost = Integer.parseInt(elem.getElementsByTagName("Price")
                        .item(0).getTextContent().replace("$", ""));
                itemCatalog.add(ItemImplFactory.createItem(itemID, itemCost));
            }

        } catch (ParserConfigurationException | SAXException | IOException |
                DOMException | InvalidDataException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ArrayList<Item> getItems() {
        return itemCatalog;
    }
}

