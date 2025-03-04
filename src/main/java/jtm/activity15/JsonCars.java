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

public class JsonCars {

    /**
     * TODO Implement method, which returns list of cars from generated JSON string
     *   public List<Car> getCars(String jsonString)
     * HINTS:
     *  You will need to use:
     *   - https://fasterxml.github.io/jackson-databind/javadoc/2.9/com/fasterxml/jackson/databind/ObjectMapper.html
     *   - https://fasterxml.github.io/jackson-databind/javadoc/2.9/com/fasterxml/jackson/databind/JsonNode.html
     * You will need to initialize JSON array from "cars" key in JSON string
     * JSON string format is following:
     *   {"cars":[{"id":4535,"model":"Bentley","year":2010,"color":"Magenta","price":3534.0}]}
     */


    /**
     * TODO create method, which returns JSON String generated from list of cars
     *   public String getJson(List<Car> cars)
     * HINTS:
     *   You will need to use:
     *   - https://fasterxml.github.io/jackson-databind/javadoc/2.9/com/fasterxml/jackson/databind/ObjectMapper.html
     *   - https://docs.oracle.com/javase/8/docs/api/index.html?java/io/StringWriter.html
     * Remember to add "cars" key as a single container for array of car objects in it.
     */
}