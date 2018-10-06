package com.apis.azure.vision;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.apis.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public class FaceDetect {
    private static final String uriBase =
            "https://westus.api.cognitive.microsoft.com/face/v1.0/detect";

    static JSONArray faceDetect(String url) throws URISyntaxException, IOException, ParseException {
        String imageWithFaces = "{\"url\": \"" + url + "\"}";
        HttpClient httpclient = new DefaultHttpClient();

        URIBuilder builder = new URIBuilder(uriBase);

        // Prepare the URI for the REST API call.
        URI uri = builder.build();
        HttpPost request = new HttpPost(uri);

        // Request headers.
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Ocp-Apim-Subscription-Key", Config.Azure.Vision.SUBSCRIPTION_KEY);

        // Request body.
        StringEntity reqEntity = new StringEntity(imageWithFaces);
        request.setEntity(reqEntity);

        // Execute the REST API call and get the response entity.
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();
        String jsonString = EntityUtils.toString(entity).trim();

        return (JSONArray) (new org.json.simple.parser.JSONParser().parse(jsonString));
    }
}
