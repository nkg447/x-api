package com.apis.watson.language;

import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.xapi.language.LanguageUtil;
import com.xapi.language.TranslationRequestBody;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WatsonTranslateServlet")
public class WatsonTranslateServlet extends HttpServlet {
    /*
     * creates a JSON response of the source language and the translation results
     */
    static String getJSONResponse(TranslationResult translationResult) {
        JSONObject response = new JSONObject();
        response.put("translatedText",translationResult.getTranslations().get(0).getTranslationOutput());
        return String.valueOf(response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TranslationRequestBody requestBody = LanguageUtil.getTranslationRequestBody(request);

            TranslationResult translationResult = Translator.translate(requestBody);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(translationResult);
            System.out.println("WatsonTranslateServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("WatsonTranslateServlet: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
