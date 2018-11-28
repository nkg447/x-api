package com.apis.google.vision;


import com.apis.Config;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FaceDetect {


    public static JSONObject faceDetect(String url) throws IOException, ParseException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"requests\": [\n" +
                "    {\n" +
                "      \"image\": {\n" +
                "        \"source\": {\n" +
                "          \"imageUri\": \"" + url + "\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"features\": [\n" +
                "        {\n" +
                "          \"type\": \"FACE_DETECTION\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        Request request = new Request.Builder()
                .url("https://vision.googleapis.com/v1/images:annotate?key="+ Config.Google.API_KEY)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "0db06089-9c6a-49e9-8fd6-4de868a47650")
                .build();

        Response response = client.newCall(request).execute();
        return (JSONObject)(new JSONParser().parse(response.body().string()));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(faceDetect("https://www.ergcouncil.com/home/images/group-cheering-diversity.jpg"));
    }
}
