package com.apis.watson.language;

import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiableLanguage;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiableLanguages;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WatsonLanguagesServlet")
public class WatsonLanguagesServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            IdentifiableLanguages identifiableLanguages = Languages.languages();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(identifiableLanguages);
            System.out.println("WatsonIdentifiableLanguages: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("WatsonIdentifiableLanguages: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(IdentifiableLanguages identifiableLanguages) {
        JSONObject response=new JSONObject();

        JSONArray languages=new JSONArray();
        JSONObject language;

        for(IdentifiableLanguage lang : identifiableLanguages.getLanguages()){
            language=new JSONObject();
            language.put("name",lang.getName());
            language.put("code",lang.getLanguage());
            languages.add(language);
        }

        response.put("languages",languages);

        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
