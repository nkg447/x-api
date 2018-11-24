package com.apis.azure.speech;

import com.apis.Config;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class Synthesize {

    static String subscriptionKey = Config.Azure.Speech.SUBSCRIPTION_KEY;

//    static String url = "https://westus.tts.speech.microsoft.com/cognitiveservices/v1";
    static String url = "https://azuretowatson.herokuapp.com/synthesize";

    static String getBody(String text, String lang) {
        String body = "<speak version='1.0' xmlns=\"http://www.w3.org/2001/10/synthesis\" xml:lang='" + lang + "'>\n" +
                "<voice  name='Microsoft Server Speech Text to Speech Voice (en-US, JessaRUS)'>\n" +
                "    " + text + "\n" +
                "</voice> </speak>";
        return body;
    }

    public static InputStream tts(URL url, String content) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/ssml+xml");
        connection.setRequestProperty("X-Microsoft-OutputFormat", "riff-24khz-16bit-mono-pcm");
        connection.setRequestProperty("Content-Length", content.length() + "");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        byte[] encoded_content = content.getBytes("UTF-8");
        wr.write(encoded_content, 0, encoded_content.length);
        wr.flush();
        wr.close();

        return connection.getInputStream();
    }

    public static InputStream tts(String text) throws Exception {
        return tts(new URL(url), getBody(text, "en-US"));
    }
}
