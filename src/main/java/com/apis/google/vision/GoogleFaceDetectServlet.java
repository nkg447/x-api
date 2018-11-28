package com.apis.google.vision;

import com.xapi.vision.VisionUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoogleFaceDetectServlet")
public class GoogleFaceDetectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = VisionUtil.getUrlFromRequest(request);

            JSONObject detectedFaces = FaceDetect.faceDetect(url);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(detectedFaces);
            System.out.println("GoogleFaceDetectServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("GoogleFaceDetectServlet: response sent");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(JSONObject detectedFaces) {
        JSONObject response = new JSONObject();
        JSONArray faces = new JSONArray();
        System.out.println("det fec - "+ detectedFaces.toString());

        JSONObject faceAnnotation = (JSONObject) ((JSONArray) detectedFaces.get("responses")).get(0);

        for(Object faceObj : (JSONArray)faceAnnotation.get("faceAnnotations")){
            JSONObject face = ((JSONObject) ((JSONObject) faceObj).get("fdBoundingPoly"));
            JSONObject facePositionStart = (JSONObject) ((JSONArray)face.get("vertices")).get(0);
            JSONObject facePositionEnd = (JSONObject) ((JSONArray)face.get("vertices")).get(2);

            JSONObject face_location=new JSONObject();

            long faceWidth = (long)facePositionEnd.get("x") - (long)facePositionStart.get("x");
            long faceHeight = (long)facePositionEnd.get("y") - (long)facePositionStart.get("y");

            face_location.put("left",facePositionStart.get("x"));
            face_location.put("top", facePositionStart.get("y"));
            face_location.put("width", faceWidth);
            face_location.put("height", faceHeight);

            face=new JSONObject();
            face.put("face_location", face_location);
            faces.add(face);
        }
        response.put("faces", faces);
        return response.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
