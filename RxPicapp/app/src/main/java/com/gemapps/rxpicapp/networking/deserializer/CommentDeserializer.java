package com.gemapps.rxpicapp.networking.deserializer;

import com.gemapps.rxpicapp.model.Comment;
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
 * Created by edu on 5/17/17.
 */

public class CommentDeserializer implements JsonDeserializer<CommentDeserializer.ResultValue> {

    @Override
    public ResultValue deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {

        JsonArray comments = getComments(json);
        return parse(comments);
    }

    private JsonArray getComments(JsonElement json) {
        JsonElement commentObj = json.getAsJsonObject().get("comments");
        return commentObj.getAsJsonObject().getAsJsonArray("comment");
    }

    private ResultValue parse(JsonArray comments) {
        ResultValue resultValue = new ResultValue();
        Gson gson = new Gson();

        comments.forEach(comment -> {
            Comment commentItem = gson.fromJson(comment, Comment.class);
            resultValue.addComment(commentItem);
        });
        return resultValue;
    }


    public static class ResultValue {
        private List<Comment> mComments = new ArrayList<>();

        public void addComment(Comment comment){
            mComments.add(comment);
        }

        public List<Comment> getComments() {
            return mComments;
        }
    }
}
