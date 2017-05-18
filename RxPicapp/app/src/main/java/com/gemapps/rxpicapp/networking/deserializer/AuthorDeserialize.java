package com.gemapps.rxpicapp.networking.deserializer;

import com.gemapps.rxpicapp.model.Author;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by edu on 5/18/17.
 */

public class AuthorDeserialize implements JsonDeserializer<Author>  {

    @Override
    public Author deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return parse(json);
    }

    private Author parse(JsonElement json) {
        return new Gson().fromJson(json.getAsJsonObject().getAsJsonObject("person"), Author.class);
    }
}
