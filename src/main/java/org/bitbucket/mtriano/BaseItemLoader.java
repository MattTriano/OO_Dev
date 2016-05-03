package org.bitbucket.mtriano;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Matt on 5/2/2016.
 */
abstract public class BaseItemLoader implements ItemLoader {
    @Override
    public void loadItemFromFile(String filePath) throws InvalidDataException {
        NodeList nodeList = this.parseToNodeList(filePath);
        onNodesLoaded(nodeList);
    }

    protected NodeList parseToNodeList(String filePath) {
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(filePath);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + filePath + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            return doc.getDocumentElement().getChildNodes();
        } catch (ParserConfigurationException| SAXException| IOException| DOMException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract void onNodesLoaded(NodeList nodeList) throws InvalidDataException;

    @Override
    public ArrayList<Item> getItems() {
        return null;
    }
}
