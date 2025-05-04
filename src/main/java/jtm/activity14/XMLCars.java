package jtm.activity14;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLCars {

    private Document doc;
    private Element root;  // <cars>

    public static boolean validateXMLSchema(String schemaSource, String xmlSource) {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(
                new javax.xml.transform.stream.StreamSource(new StringReader(schemaSource))
            );
            Validator validator = schema.newValidator();
            validator.validate(
                new javax.xml.transform.stream.StreamSource(new StringReader(xmlSource))
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addCar(Car car, String notes) {
        try {
            if (doc == null) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.newDocument();
                // ensure standalone declaration appears
                doc.setXmlStandalone(false);
                // root element
                root = doc.createElement("cars");
                doc.appendChild(root);
            }

            // pad id to 4 digits
            String idStr = String.format("%04d", car.getId());

            Element carElem = doc.createElement("car");
            carElem.setAttribute("id",    idStr);
            carElem.setAttribute("notes", notes);

            // <model>
            Element m = doc.createElement("model");
            m.setTextContent(car.getModel());
            carElem.appendChild(m);

            // <color>
            Element c = doc.createElement("color");
            c.setTextContent(car.getColor());
            carElem.appendChild(c);

            // <year>
            Element y = doc.createElement("year");
            y.setTextContent(Integer.toString(car.getYear()));
            carElem.appendChild(y);

            // <price>
            Element p = doc.createElement("price");
            p.setTextContent(Float.toString(car.getPrice()));
            carElem.appendChild(p);

            // XML comment with notes
            Comment comment = doc.createComment(notes);
            carElem.appendChild(comment);

            root.appendChild(carElem);

        } catch (ParserConfigurationException ex) {
            throw new RuntimeException("Failed to initialize XML Document", ex);
        }
    }

    public String getXML() {
        if (doc == null) {
            return "";
        }
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.STANDALONE, "no");

            StringWriter sw = new StringWriter();
            t.transform(new DOMSource(doc), new StreamResult(sw));
            String xml = sw.toString();
            // ensure declaration is present
            if (!xml.startsWith("<?xml")) {
                xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + xml;
            }
            return xml;
        } catch (Exception e) {
            throw new RuntimeException("Error serializing XML", e);
        }
    }

    public List<Car> getCars(String xmlSource) {
        List<Car> list = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document inDoc = db.parse(new InputSource(new StringReader(xmlSource)));
            inDoc.getDocumentElement().normalize();

            NodeList nodes = inDoc.getElementsByTagName("car");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                int    id    = Integer.parseInt(e.getAttribute("id"));
                String model = e.getElementsByTagName("model").item(0).getTextContent();
                String color = e.getElementsByTagName("color").item(0).getTextContent();
                int    year  = Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent());
                float  price = Float.parseFloat(e.getElementsByTagName("price").item(0).getTextContent());

                list.add(new Car(id, model, year, color, price));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse XML into cars", ex);
        }
        return list;
    }
}
