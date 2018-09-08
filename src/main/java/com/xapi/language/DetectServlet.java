package com.xapi.language;

import com.apis.azure.language.AzureDetectServlet;
import com.apis.google.language.GoogleDetectServlet;
import com.apis.watson.language.WatsonDetectServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetectServlet")
public class DetectServlet extends HttpServlet {

    static WatsonDetectServlet watsonDetectServlet = new WatsonDetectServlet();
    static AzureDetectServlet azureDetectServlet = new AzureDetectServlet();
    static GoogleDetectServlet googleDetectServlet = new GoogleDetectServlet();

    /*
    * redirect request to corresponding API Vendor
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("DetectLanguage with " + api + " API");
        if (api.equals("watson")) {
            watsonDetectServlet.doPost(request, response);
        }else if(api.equals("azure")){
            azureDetectServlet.doPost(request,response);
        }else{
            googleDetectServlet.doPost(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
