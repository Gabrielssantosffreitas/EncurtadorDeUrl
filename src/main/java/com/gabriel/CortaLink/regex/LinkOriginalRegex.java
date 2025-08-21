package com.gabriel.CortaLink.regex;

import java.util.regex.Pattern;

public class LinkOriginalRegex {
    private final static  String regex = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]+(\\/[\\w\\-._~:/?#[\\\\]@!$&'()*+,;=%]*)?$";
    private final static Pattern  pattern = Pattern.compile(regex);

   public static boolean isLink(String url){
        if (url == null) return false;
        return pattern.matcher(url).matches();
    }
}
