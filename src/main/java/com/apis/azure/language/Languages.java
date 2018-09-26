package com.apis.azure.language;

import com.apis.Config;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Languages {

    // endpoint and path to access the API
    static String host = "https://api.cognitive.microsofttranslator.com";
    static String path = "/languages?api-version=3.0";

    public static JSONObject languages() throws Exception {
        String response = getLanguages();
        Object obj = new JSONParser().parse(response);
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    public static String getLanguages () throws Exception {
        URL url = new URL (host + path);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", Config.Azure.Language.SUBSCRIPTION_KEY);
        connection.setDoOutput(true);

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

}
