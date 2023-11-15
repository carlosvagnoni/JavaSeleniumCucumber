package com.automatedtests.demoblaze.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.io.IOException;

/**
 * Configuration class represents the configuration settings for a system.
 */
public class Configuration {
    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String navigator;

    /**
     * Loads a Configuration object from a JSON file.
     *
     * @param path The path to the JSON file.
     * @return A Configuration object populated from the JSON file.
     * @throws IOException If there's an issue reading the file or parsing the JSON.
     */
    public static Configuration loadFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Configuration.class);
    }
}
