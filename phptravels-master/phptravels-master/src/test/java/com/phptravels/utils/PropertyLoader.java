package com.phptravels.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class PropertyLoader {
    public static Configuration LoadProperties() throws ConfigurationException {
        Configurations configs = new Configurations();
        return configs.properties("C:\\Users\\laptop\\IdeaProjects\\phptravels\\src\\test\\resources\\test.properties");
    }

    public static void main(String[] args) throws ConfigurationException {
        Configuration config = LoadProperties();



    }
}
