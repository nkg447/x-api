package com.apis.azure.language;

import com.google.gson.Gson;
import org.json.simple.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Detector {

    // endpoint and path to access the API
    static private final String host = "https://api.cognitive.microsofttranslator.com";
    static private final String path = "/detect?api-version=3.0";

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

        return PostRequest.prettify(PostRequest.post(url, content));
    }


}
