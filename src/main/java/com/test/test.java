package com.test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
    	String step = "AB // CD (දත්තය)";
    	String reason = "";
        try {
            byte[] utf8Bytes = step.getBytes("UTF-8");

            reason = new String(utf8Bytes, "UTF-8");
            step = new String(utf8Bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Pattern unicodeOutliers = Pattern.compile("[^\\u0D80-\\u0DFF]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE);
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(reason);
        reason = unicodeOutlierMatcher.replaceAll(" ");
        reason = reason.trim();
        
        unicodeOutliers = Pattern.compile("[\\u0D80-\\u0DFF || \\( || \\)]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE);
        unicodeOutlierMatcher = unicodeOutliers.matcher(step);
        step = unicodeOutlierMatcher.replaceAll(" ");
        step = step.trim();
        System.out.println(reason);
        System.out.println(step);
    }
}