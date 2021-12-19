package com.example.l2_1.util;

import java.util.List;

public class ListConverter {

    public static String listToString(List<Object> objectList) {
        String resultString = "[ ";
        int i = 0;
        for(Object object: objectList) {
            resultString += objectList.toString();
            resultString += " ";
            if (i > 5) {
                resultString += "...";
                break;
            }
        }
        resultString = resultString.strip();
        resultString += "]";
        return resultString;
    }

}
