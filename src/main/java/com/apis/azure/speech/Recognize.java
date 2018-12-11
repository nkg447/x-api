package com.apis.azure.speech;

import com.apis.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;


public class Recognize {

    static String subscriptionKey = Config.Azure.Speech.SUBSCRIPTION_KEY;
    static String url =
            "https://westus.stt.speech.microsoft.com/speech/recognition/conversation/cognitiveservices/v1?language=en-US&format=detailed";


    public static String Post(URL url, InputStream audioStream) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "audio/wav; codec=audio/pcm; samplerate=16000");
        connection.setRequestProperty("Accept", "application/json;text/xml");
        connection.setRequestProperty("Transfer-Encoding", "chunked");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setRequestProperty("Expect", "100-continue");
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        int c;
        while ((c = audioStream.read()) != -1) {
            wr.write(c);
        }

        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }


    static String recognize(InputStream audioStream) throws Exception {
        return Post(new URL(url),audioStream);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(recognize(null));
    }

}
