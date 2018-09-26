package com.apis.azure.language;

import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiableLanguage;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiableLanguages;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AzureLanguagesServlet")
public class AzureLanguagesServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONObject languages = Languages.languages();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(languages);
            System.out.println("AzureSupportedLanguages: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("AzureSupportedLanguages: response sent");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(JSONObject languages) {
        JSONObject response=new JSONObject();

        JSONObject language;
        JSONArray langs = new JSONArray();

        for(Object object : languages.keySet()) {
            JSONObject jsonObject = (JSONObject) languages.get((String)object);

            for(Object key : jsonObject.keySet()){
                String keyStr = (String) key;

                language=new JSONObject();
                language.put("code", keyStr);
                language.put("name", ((JSONObject)jsonObject.get(keyStr)).get("name"));
                langs.add(language);
            }
        }

        response.put("languages",langs);

        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
