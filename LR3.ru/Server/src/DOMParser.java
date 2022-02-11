import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import phone.*;

public class DOMParser {
    private DOMParser() {

    }

    public static PhoneList parseGroupNode(String xmlFilePath)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(xmlFilePath));

        PhoneList phones = new PhoneList();

        NodeList nodeList = document.getDocumentElement().getElementsByTagName("phone");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);

            if (node.getNodeName().equals("phone")) {
                phones.addPhone(parsePhoneNode(node));
            }
        }

        return phones;
    }

    private static Phone parsePhoneNode(Node node) {
        NamedNodeMap attributes = node.getAttributes();

        Phone phone = new Phone(Integer.parseInt(attributes.getNamedItem("modelindex").getNodeValue()),
                attributes.getNamedItem("name").getNodeValue(),
                attributes.getNamedItem("manufacturer").getNodeValue(),
                attributes.getNamedItem("operatingsystem").getNodeValue(),
                attributes.getNamedItem("releaseyear").getNodeValue());

        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node childNode = childNodes.item(i);

            if (childNode.getNodeName().equals("review")) {
                Review review = parseReviewNode(childNode);
                phone.addSubject(review);
            } else if (childNode.getNodeName().equals("average")) {
                phone.setAverage(parseAverageNode(childNode));
            }
        }

        return phone;
    }

    private static Review parseReviewNode(Node node) {
        NamedNodeMap attributes = node.getAttributes();

        return new Review(attributes.getNamedItem("website").getNodeValue(),
                Integer.parseInt(attributes.getNamedItem("score").getNodeValue()));
    }

    private static int parseAverageNode(Node node) {
        return Integer.parseInt(node.getFirstChild().getNodeValue());
    }
}
