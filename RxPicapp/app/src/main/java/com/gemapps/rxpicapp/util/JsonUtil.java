package com.gemapps.rxpicapp.util;

import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by edu on 4/13/17.
 */

public class JsonUtil {

    public static final Gson PICTURE_DESERIALIZER = new GsonBuilder()
            .registerTypeAdapter(PictureDeserializer.ResultValue.class, new PictureDeserializer())
            .create();

    public static String loadJsonFromResources(ClassLoader loader, String jsonName ){
        InputStream inputStream = loader.getResourceAsStream(jsonName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return readFrom(reader);
    }

    public static String readFrom(BufferedReader reader) {
        String json;
        StringBuilder builder = new StringBuilder();
        try {
            while ((json = reader.readLine()) != null) builder.append(json);
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
