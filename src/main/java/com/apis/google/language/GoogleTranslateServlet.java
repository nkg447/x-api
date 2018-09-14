package com.apis.google.language;

import com.google.cloud.translate.Translation;
import com.xapi.language.LanguageUtil;
import com.xapi.language.TranslationRequestBody;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoogleTranslateServlet")
public class GoogleTranslateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TranslationRequestBody requestBody = LanguageUtil.getTranslationRequestBody(request);

            Translation translation = Translator.translate(requestBody);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(translation);
            System.out.println("GoogleTranslateServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("GoogleTranslateServlet: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /*
     * creates a JSON response of the translated language
     */
    private String getJSONResponse(Translation translation) {
        JSONObject response = new JSONObject();
        response.put("translatedText", translation.getTranslatedText());
        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
