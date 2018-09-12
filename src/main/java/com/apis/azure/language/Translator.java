package com.apis.azure.language;

import com.xapi.language.TranslationRequestBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;

public class Translator {

    // endpoint and path to access the API
    static private final String host = "https://api.cognitive.microsofttranslator.com";
    static private final String path = "/translate?api-version=3.0";

    /*
     * driver function for translating language
     */
    static String translate(TranslationRequestBody requestBody) throws Exception {
        String param = "&to=" + requestBody.getTargetLanguage();
        URL url = new URL(host + path + param);

        String content = "[\n" +
                "    {\"Text\":\"" + requestBody.getText() + "\"}\n" +
                "]";

        JSONObject response = (JSONObject) PostRequest.prettify(PostRequest.post(url, content)).get(0);
        JSONObject translation = ((JSONObject) ((JSONArray) response.get("translations")).get(0));
        return (String) translation.get("text");
    }

}
