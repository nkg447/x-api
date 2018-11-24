package com.apis.watson.language;

import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiedLanguage;
import com.xapi.language.LanguageUtil;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetectServlet")
public class WatsonDetectServlet extends HttpServlet {

    /*
     * Handle the request and create the response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String text = LanguageUtil.getTestFromRequest(request);
            IdentifiedLanguage identifiedLanguage = Detector.detectLanguages(text);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(identifiedLanguage);
            System.out.println("WatsonDetectServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("WatsonDetectServlet: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /*
     * creates a JSON response of the detected language
     */
    private String getJSONResponse(IdentifiedLanguage identifiedLanguage) {
        JSONObject response = new JSONObject();
        response.put("detectedLanguage",identifiedLanguage.getLanguage());
        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
