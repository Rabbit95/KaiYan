package com.rabbit.kaiyan.util;


import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtil {
    private String url;
    private String title;
    public final static UrlUtil UrlDecoder(String actionUrl){
        UrlUtil urlUtil = new UrlUtil();
        String deURL = URLDecoder.decode(actionUrl);
        urlUtil.title = getActionTitle(deURL);
        urlUtil.url = getActionUrl(deURL);
        return urlUtil;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    private static String getActionTitle(String deURL){
        String title="";
        String regex = "([\\u4e00-\\u9fa5]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(deURL);
        if (matcher.find()){
            title= matcher.group();
        }
        return title;
    }

    private static String getActionUrl(String deURL){
        String url="";
        String regex = "(http:\\/\\/)[^\\s]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(deURL);
        if(matcher.find()) {
            url = matcher.group();
        }
        return url;
    }

}
