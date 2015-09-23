package com.weaverprojects.instagramapi.lib.server;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by 7cm5_000 on 2015-08-16.
 * Modded  by Keith on 2015-09-22.
 */
public class NaturalDeserializer implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull()) {
            return null;
        } else if (json.isJsonPrimitive()) {
            return handlePrimitive(json.getAsJsonPrimitive());
        } else if (json.isJsonArray()){
            return handleArray(json.getAsJsonArray(), context);
        } else {
            return handleObject(json.getAsJsonObject(), context);
        }
    }

    private Object handlePrimitive(JsonPrimitive json){
        if (json.isBoolean()) {
            return json.getAsBoolean();
        } else if (json.isString()) {
            return json.getAsString();
        } else {
            BigDecimal bigDecimal = json.getAsBigDecimal();
            try {
                bigDecimal.toBigIntegerExact();
                try {
                    return bigDecimal.intValueExact();
                } catch (ArithmeticException e) { }

                return bigDecimal.longValue();
            } catch (ArithmeticException e) { }

            return bigDecimal.doubleValue();
        }
    }

    private Object handleArray(JsonArray json, JsonDeserializationContext context){
        Object[] array = new Object[json.size()];
        for (int i = 0; i < json.size(); i += 1) {
            array[i] = this.deserialize(json.get(i), Object.class, context); // context.deserialize(json.get(i), Object.class);
        }
        return array;
    }

    private Object handleObject(JsonObject json, JsonDeserializationContext context){
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()){
            map.put(entry.getKey(), this.deserialize(entry.getValue(), Object.class, context)); // context.deserialize(entry.getValue(), Object.class));
        }
        return map;
    }

}
