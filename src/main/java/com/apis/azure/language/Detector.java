package com.apis.azure.language;

import com.apis.Config;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Detector {

    // endpoint and path to access the API
    static String host = "https://api.cognitive.microsofttranslator.com";
    static String path = "/detect?api-version=3.0";

    /*
     * class of the Request
     */
    public static class RequestBody {
        String Text;

        public RequestBody(String text) {
            this.Text = text;
        }
    }

    /*
     * driver function for detecting language
     */
    public static JSONArray Detect(String text) throws Exception {
        URL url = new URL(host + path);

        List<RequestBody> objList = new ArrayList<RequestBody>();
        objList.add(new RequestBody(text));
        String content = new Gson().toJson(objList);

        return prettify(Post(url, content));
    }

    /*
     * convert the response to a json data
     */
    private static JSONArray prettify(String response) throws ParseException {
        Object obj = new JSONParser().parse(response);
        JSONArray jsonArray = (JSONArray) obj;
        return jsonArray;
    }

    /*
     * Send the request using the HttpsURLConnection
     */
    public static String Post(URL url, String content) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Length", content.length() + "");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", Config.Azure.Language.SUBSCRIPTION_KEY);
        connection.setRequestProperty("X-ClientTraceId", java.util.UUID.randomUUID().toString());
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        byte[] encoded_content = content.getBytes("UTF-8");
        wr.write(encoded_content, 0, encoded_content.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }


}
