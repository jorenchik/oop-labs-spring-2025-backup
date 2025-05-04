package jtm.activity15;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jtm.activity14.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility for serializing and deserializing Car objects to/from JSON.
 */
public class JsonCars {
    private static final Logger logger = LogManager.getLogger(JsonCars.class);

    /**
     * Parses the given JSON string and returns a list of Car objects.
     * Expected JSON format:
     * {"cars":[{"id":4535,"model":"Bentley","year":2010,"color":"Magenta","price":3534.0},...]}
     *
     * @param jsonString JSON text containing a "cars" array
     * @return list of Car instances (empty on error)
     */
    public List<Car> getCars(String jsonString) {
        List<Car> cars = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(jsonString);
            ArrayNode arr = (ArrayNode) root.path("cars");
            for (JsonNode node : arr) {
                int id = node.path("id").asInt();
                String model = node.path("model").asText();
                int year = node.path("year").asInt();
                String color = node.path("color").asText();
                float price = (float) node.path("price").asDouble();
                cars.add(new Car(id, model, year, color, price));
            }
        } catch (IOException e) {
            logger.error("Failed to parse JSON into Car list", e);
        }
        return cars;
    }

    /**
     * Serializes the given list of Car objects into a JSON string.
     * Output format:
     * {"cars":[{"id":4535,"model":"Bentley","year":2010,"color":"Magenta","price":3534.0},...]}
     *
     * @param cars list of Car objects to serialize
     * @return JSON text (empty string on error)
     */
    public String getJson(List<Car> cars) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ArrayNode arr = root.putArray("cars");
        for (Car car : cars) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", car.getId());
            node.put("model", car.getModel());
            node.put("year", car.getYear());
            node.put("color", car.getColor());
            node.put("price", car.getPrice());
            arr.add(node);
        }
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize Car list to JSON", e);
            return "";
        }
    }
}
