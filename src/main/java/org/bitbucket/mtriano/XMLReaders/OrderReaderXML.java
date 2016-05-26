package org.bitbucket.mtriano.XMLReaders;

import org.bitbucket.mtriano.InvalidDataException;
import org.bitbucket.mtriano.Order.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Matt on 5/18/2016.
 */
public class OrderReaderXML implements OrderLoader {

    ArrayList<Order> orderQueue = new ArrayList<>();

    public void loadOrdersFromFile(String filePath) {
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
            NodeList orderEntries = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < orderEntries.getLength(); i++) {
                if (orderEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = orderEntries.item(i).getNodeName();
                if (!entryName.equals("Order")) {
                    throw new InvalidDataException("Unexpected node found: " + entryName);
                }

                Element iElem = (Element) orderEntries.item(i);
                NamedNodeMap aMap = orderEntries.item(i).getAttributes();
                String orderID = aMap.getNamedItem("OrderID").getNodeValue();
                int startDay = Integer.parseInt(iElem.getElementsByTagName("StartTime")
                        .item(0).getTextContent());
                String destination = iElem.getElementsByTagName("Destination")
                        .item(0).getTextContent();

                // now I need to harvest the orderLines from the order
                ArrayList<Line> orderLines = new ArrayList<>();
                NodeList lineList = iElem.getElementsByTagName("OrderItem");
                for (int j = 0; j < lineList.getLength(); j++) {
                    if (lineList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = lineList.item(j).getNodeName();
                    if (!entryName.equals("OrderItem")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    Element jElem = (Element) lineList.item(j);
                    String itemID = jElem.getElementsByTagName("ItemID").item(0)
                            .getTextContent();
                    Integer itemQty = Integer.parseInt(jElem.getElementsByTagName("ItemQty")
                            .item(0).getTextContent());
                    orderLines.add(LineImplFactory.createLine(itemID, itemQty));
                }

                Order order = OrderImplFactory.createOrder(startDay, orderID,
                        destination,orderLines);
                orderQueue.add(order);
            }
        } catch (ParserConfigurationException | SAXException | IOException |
                DOMException | InvalidDataException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ArrayList<Order> getOrderList() {
        return orderQueue;
    }


}
