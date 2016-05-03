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
 * Created by Matt on 4/28/2016.
 */
public class ItemXMLLoader extends BaseItemLoader {

    ArrayList<Item> itemCatalog = new ArrayList<>();
    String defaultFilepath = "src/main/java/org/bitbucket/mtriano/ItemCatalog.xml";

    public void loadItemFromFile(String filePath) {
        try {
            //TODO: Fix this hack, pass in from caller
            filePath = this.defaultFilepath;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(filePath);
//            String cwd = System.getProperty("user.dir");
//            System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
//            System.out.println(xml.toString());
            if (!xml.exists()) {
                System.err.println("**** XML File '" + filePath + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            NodeList itemEntries = doc.getDocumentElement().getChildNodes();
        } catch (ParserConfigurationException| SAXException| IOException| InvalidDataException | DOMException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNodesLoaded(NodeList nodeList) throws InvalidDataException {
        try {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = nodeList.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                Element elem = (Element) nodeList.item(i);

                String itemID = elem.getElementsByTagName("ItemID").item(0).getTextContent();
                int itemCost = Integer.parseInt(elem.getElementsByTagName("Price").item(0).getTextContent().replace("$", ""));
                itemCatalog.add(ItemImplFactory.createItem(itemID, itemCost));
            }
        } catch (InvalidDataException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getItems() {
        return itemCatalog;
    }

}
