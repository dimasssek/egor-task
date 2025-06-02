package ru.egor;

import ru.egor.property.PropertyContainer;

public class Main {
    public static void main(String[] args) {
        var propertyContainer = new PropertyContainer("app.properties");
        propertyContainer.loadProperties();
    }
}