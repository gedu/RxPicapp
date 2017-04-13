package com.gemapps.rxpicapp.networking.deserializer;

import com.gemapps.rxpicapp.model.Picture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edu on 4/13/17.
 */

public class PictureDeserializer implements JsonDeserializer<PictureDeserializer.ResultValue> {

    @Override
    public ResultValue deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        JsonArray photoArray = getPhotos(jsonElement);
        return parse(photoArray);
    }

    private JsonArray getPhotos(JsonElement jsonElement) {
        JsonElement photos = jsonElement.getAsJsonObject().get("photos");
        return photos.getAsJsonObject().getAsJsonArray("photo");
    }

    private ResultValue parse(JsonArray photoArray){
        ResultValue resultValue = new ResultValue();
        Gson gson = new Gson();

        for (JsonElement element: photoArray) {
            Picture pic = gson.fromJson(element, Picture.class);
            resultValue.addPicture(pic);
        }
        return resultValue;
    }

    public static class ResultValue {
        private List<Picture> mPictures = new ArrayList<>();

        public void addPicture(Picture picture){
            mPictures.add(picture);
        }

        public List<Picture> getPictures() {
            return mPictures;
        }
    }
}
