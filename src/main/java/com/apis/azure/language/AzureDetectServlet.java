package com.apis.azure.language;

import com.xapi.language.LanguageUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetectServlet")
public class AzureDetectServlet extends HttpServlet {

    /*
     * Handle the request and create the response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String text = LanguageUtil.getTestFromRequest(request);
            JSONArray detectedLanguage = Detector.Detect(text);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(detectedLanguage);
            System.out.println("AzureDetectServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("AzureDetectServlet: response sent");


        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }


    /*
     * creates a JSON response of the detected language
     */
    private String getJSONResponse(JSONArray languages) {
        JSONObject response = new JSONObject();
        JSONObject language = (JSONObject) languages.get(0);
        response.put("detectedLanguage", language.get("language"));
        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
