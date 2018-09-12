package com.xapi.language;

import com.apis.watson.language.WatsonTranslateServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TranslateServlet")
public class TranslateServlet extends HttpServlet {

    static WatsonTranslateServlet watsonTranslateServlet=new WatsonTranslateServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("TranslateLanguage with " + api + " API");
        if (api.equals("watson")) {
            watsonTranslateServlet.doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
