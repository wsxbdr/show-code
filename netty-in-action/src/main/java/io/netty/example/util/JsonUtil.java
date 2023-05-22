package io.netty.example.util;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson GSON = new Gson();

    private JsonUtil() {
        //NO Instance
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return GSON.fromJson(jsonStr, clazz);
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }
}
