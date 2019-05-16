package com.acmegames.game.rockpaperscissors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MvcTestUtil {
    public static final String CONTENT_TYPE = "application/json";

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String objectToString(final T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Can't convert object to JSON string");
        }
    }

    public static <T> boolean isValidJsonObject(final Class<T> clazz, final String value) {
        try {
            mapper.readValue(value, clazz);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
