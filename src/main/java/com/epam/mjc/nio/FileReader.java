package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> dataMap = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                addDataToMapFromLine(dataMap, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dataMap.isEmpty()) {
            return new Profile();
        } else {
            return getProfileFromMap(dataMap);
        }
    }

    private void addDataToMapFromLine(Map<String, String> map, String line) {
        String[] data = line.split(":");
        if(data.length == 2) {
            map.put(data[0].trim(), data[1].trim());
        } else {
            map.put(data[0].trim(), null);
        }
    }

    private Profile getProfileFromMap(Map<String, String> map){
        final String NAME_KEY = "Name";
        final String AGE_KEY = "Age";
        final String EMAIL_KEY = "Email";
        final String PHONE_KEY = "Phone";
        String name = map.get(NAME_KEY);
        String email = map.get(EMAIL_KEY);
        Integer age;
        try{
            age = Integer.parseInt(map.get(AGE_KEY));
        } catch (NumberFormatException e) {
            age = null;
        }
        Long phone;
        try{
            phone = Long.parseLong(map.get(PHONE_KEY));
        } catch (NumberFormatException e) {
            phone = null;
        }
        return new Profile(name, age, email, phone);
    }
}
