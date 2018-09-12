package com.apis.azure.language;

import com.xapi.language.LanguageUtil;
import com.xapi.language.TranslationRequestBody;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AzureTranslateServlet")
public class AzureTranslateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TranslationRequestBody requestBody = LanguageUtil.getTranslationRequestBody(request);

            String translatedText = Translator.translate(requestBody);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(translatedText);
            System.out.println("WatsonTranslateServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("WatsonTranslateServlet: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(String translatedText) {
        JSONObject response = new JSONObject();
        response.put("translatedText", translatedText);
        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
