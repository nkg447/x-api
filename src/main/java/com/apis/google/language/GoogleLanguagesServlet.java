package com.apis.google.language;

import com.google.cloud.translate.Language;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoogleLanguagesServlet")
public class GoogleLanguagesServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Language> languages = SupportedLanguages.getSupportedLanguages();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(languages);
            System.out.println("GoogleSupportedLanguages: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("GoogleSupportedLanguages: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(List<Language> supportedLanguages) {
        JSONObject response=new JSONObject();

        JSONArray languages=new JSONArray();
        JSONObject language;

        for(Language lang : supportedLanguages){
            language=new JSONObject();
            language.put("name",lang.getName());
            language.put("code",lang.getCode());
            languages.add(language);
        }

        response.put("languages",languages);

        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
