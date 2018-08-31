package com.xapi;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class Util {
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();

        String body = "";
        String temp;
        while ((temp = br.readLine()) != null) {
            body += temp;
        }
        return body;
    }
    public static String toSentence(String str) {
        str = str.trim();
        if (!str.endsWith(".")) {
            str = str + ".";
        }
        String firstChar = String.valueOf(str.charAt(0));

        return firstChar.toUpperCase() + str.substring(1);
    }
}
