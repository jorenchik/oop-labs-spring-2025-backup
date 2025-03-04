package jtm.activity14;

// TODO #1
// Import necessary classes from javax.xml.* and, if necessary, org.w3c.dom.*

public class XMLCars {

    /* TODO
     * Declare Document (and Element) variables to remember generated structure of XML
     * addCar(Car car, String notes) method should add new elements to the same XML Document
     */

    /**
     * TODO create method which validates XML string against XML schema (also passed as string)
     *   public static boolean validateXMLSchema(String schemaSource, String xmlSource)
     * Use https://docs.oracle.com/javase/7/docs/api/javax/xml/validation/Validator.html
     *
     * @param schemaSource — String containing XSD schema definition from car.xsd file
     * @param xmlSource    — String containing XML for car
     * @return — true, if xmlSource is valid
     */

    /**
     * TODO create method which adds new car elements into XML structure:
     *     addCar(Car car, String notes)
     * Note, that:
     *   1. if method is called 1st time, one root element "car" should be
     *      created, but if method is called again, just new "car" element is added into
     *      "cars" tree.
     *   2. Car id should be padded with leading zeroes if id is smaller than 1111.
     *      E.g. if int id=33, then  attribute of XML should be id="0033".
     *   3. At the end of car element XML comment should be added with value of passed notes
     *      (This is not checked by validator using XSD schema,
     *      but is checked when generated XML is produced as string.)
     * Hint:
     *   Look at https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html and
     *           https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/package-summary.html
     *
     * @param car   — One of passed Car object
     * @param notes — Notes as XML comments
     */

    /**
     * TODO: create method that returns String containing XML that matches car.xsd requirements.
     *  public String getXML()
     * HINT look at:
     * https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html
     * Note, that XML should be "prettied" with line breaks and
     * indentations of 2 spaces for internal elements
     * HINT: look at:
     * https://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Transformer.html
     *
     * @return — Formatted and indented String of Document prepared by addCar() method
     */

    /**
     * TODO Create method which prepares list of cars from passed XML string
     *   public List<Car> getCars(String xmlSource)
     *
     * @param xmlSource — passed XML string
     * @return — created list of cars
     */

}
