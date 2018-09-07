package com.xapi.language;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LanguageUtil {

    public static String getTestFromRequest(HttpServletRequest request) throws IOException, ParseException {
        String body = com.xapi.Util.getRequestBody(request);
        Object obj = new JSONParser().parse(body);
        JSONObject jo = (JSONObject)obj;
        return (String) jo.get("text");
    }
}
