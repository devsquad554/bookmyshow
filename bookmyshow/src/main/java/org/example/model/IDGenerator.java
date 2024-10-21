package org.example.model;

import java.util.UUID;

public class IDGenerator {
    public static String generateID(){
        return UUID.randomUUID().toString();
    }
}
