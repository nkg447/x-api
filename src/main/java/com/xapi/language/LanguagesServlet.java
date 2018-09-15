package com.xapi.language;

import com.apis.google.language.GoogleLanguagesServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LanguagesServlet")
public class LanguagesServlet extends HttpServlet {

    static private GoogleLanguagesServlet googleLanguagesServlet=new GoogleLanguagesServlet();

    /*
     * redirect request to corresponding API Vendor
     */
    @SuppressWarnings("Duplicates")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("TranslateLanguage with " + api + " API");
        if (api.equals("google")) {
            googleLanguagesServlet.doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
