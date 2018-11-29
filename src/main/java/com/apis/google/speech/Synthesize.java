package com.apis.google.speech;


import com.apis.Config;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import okhttp3.*;
import org.json.simple.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Synthesize {
    static InputStream text_to_speech(String text) throws Exception {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "    'input':{\n" +
                "      'text':'"+text+"'\n" +
                "    },\n" +
                "    'voice':{\n" +
                "      'languageCode':'en-gb',\n" +
                "      'name':'en-GB-Standard-A',\n" +
                "      'ssmlGender':'FEMALE'\n" +
                "    },\n" +
                "    'audioConfig':{\n" +
                "      'audioEncoding':'MP3'\n" +
                "    }\n" +
                "}");
        Request request = new Request.Builder()
                .url("https://texttospeech.googleapis.com/v1/text:synthesize?key="+ Config.Google.API_KEY)
                .post(body)
                .addHeader("Referer", "http://translate.google.com/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject resp = (JSONObject)(new org.json.simple.parser.JSONParser().parse(response.body().string()));
        String b64String = (String) resp.get("audioContent");
        byte[] decodedBytes = Base64.decode(b64String);

        return new ByteArrayInputStream(decodedBytes);
    }

    public static void main(String[] args) throws Exception {
        text_to_speech("hello world");
    }

}
