package com.forgerock.devops.challenge.ianensor.testUtils;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

import okio.BufferedSource;
import okio.Okio;

/**
 * A utils class, boilerplated from the CrazzyGhost's Alphavantage test utils
 */
public class JsonUtils {

    private static String directory;

    private static JsonAdapter<Map<String,Object>> getJsonAdapter() {
        final Moshi moshi = new Moshi.Builder().build();
        final Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
        return moshi.adapter(type);
    }

    public static Map<String,Object> json(String filename) throws IOException {
        return getJsonAdapter().fromJson(getJson(filename));
    }

    private static BufferedSource getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src","test","resources", directory, filename + ".json").toFile());
        return Okio.buffer(Okio.source(stream));
    }

    public static void forDirectory(String directory){
        JsonUtils.directory = directory;
    }
}
