package com.xapi.language;

import com.apis.azure.language.AzureLanguagesServlet;
import com.apis.google.language.GoogleLanguagesServlet;
import com.apis.watson.language.WatsonLanguagesServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LanguagesServlet")
public class LanguagesServlet extends HttpServlet {

    static private GoogleLanguagesServlet googleLanguagesServlet=new GoogleLanguagesServlet();
    static private WatsonLanguagesServlet watsonLanguagesServlet=new WatsonLanguagesServlet();
    static private AzureLanguagesServlet azureLanguagesServlet=new AzureLanguagesServlet();

    /*
     * redirect request to corresponding API Vendor
     */
    @SuppressWarnings("Duplicates")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("Languages supported by " + api + " API");
        if (api.equals("google")) {
            googleLanguagesServlet.doPost(request, response);
        }
        else if(api.equals("watson")){
            watsonLanguagesServlet.doPost(request,response);
        }
        else{
            azureLanguagesServlet.doPost(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
