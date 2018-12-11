package com.apis.azure.speech;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "AzureRecognizeServlet")
public class AzureRecognizeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            InputStream audioInputStream = request.getInputStream();

            String results = Recognize.recognize(audioInputStream);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(results);

            response.getWriter().println(jsonResponse);
            System.out.println("AzureRecognizeServlet: response sent");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    static JSONObject parse(String data) throws ParseException {
        Object obj = new JSONParser().parse(data);
        return (JSONObject) obj;
    }


    private String getJSONResponse(String results) throws ParseException {
        JSONObject jsonResult = parse(results);

        JSONObject response = new JSONObject();
        JSONObject data = ((JSONObject) (
                (JSONArray) jsonResult.get("NBest"))
                .get(0));

        response.put("transcript", data.get("Display"));
        response.put("confidence", data.get("Confidence"));

        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
