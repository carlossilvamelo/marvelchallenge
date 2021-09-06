package com.marvelchallenge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class MockLoaderUtils {

    private static final String BASE_PATH = "mocks\\";

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        objMapper.registerModule(new JSR310Module());
        objMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

        return objMapper;
    }

    public static <T> T stringToObject(String jsonString, TypeReference<T> typeReference) {
        ObjectMapper objectMapper = getObjectMapper();
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static <T> T getMockTypeReference(String fileName, TypeReference<T> typeReference) {
        ObjectMapper objMapper = getObjectMapper();
        try {
            Reader reader = getReader(fileName);
            return (T) objMapper.readValue(reader, typeReference);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Reader getReader(String fileName) throws IOException {
        String fileCompletePath = String.format("%s%s", BASE_PATH, fileName);
        Resource resource = new ClassPathResource(fileCompletePath);
        InputStream inputStream = resource.getInputStream();
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }

    public static void printJson(Object obj) {
        ObjectMapper objectMapper = getObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(obj);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public static String objectToString(Object obj) {
        ObjectMapper objectMapper = getObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
