package com.noname.books_exchange.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.util.Pair;
import org.springframework.util.ResourceUtils;

public class GeneralUtils {
    public static final int MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5;
    public static final int MAX_USERNAME_LENGTH = 20;
    private static final Encoder BASE64_ENCODER = Base64.getEncoder();

    //https://stackoverflow.com/questions/12800588/how-to-calculate-a-file-size-from-url-in-java
    public static Pair<Integer, String> getVKAvatarInfo(URL url) {
        HttpURLConnection connection = null;
        int length = -1;
        String type = "";
        try {
            connection = (HttpURLConnection) url.openConnection();
            //https://developer.mozilla.org/ru/docs/Web/HTTP/Methods/HEAD
            connection.setRequestMethod("HEAD");
            length = connection.getContentLength();
            type = connection.getContentType();
        } catch (IOException ioe) {
            //TODO
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return Pair.of(length, type);
    }

    public static String getHtmlPage(String urlStr) {
        String html = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            int code = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input = "";
            StringBuffer content = new StringBuffer();
            while((input = in.readLine()) != null) {
                content.append(input);
            }
            connection.disconnect();
            in.close();
            if(code != 200) {
                //TODO что-то произошло с запросом
            }
            html = content.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //TODO
        } catch (IOException e) {
            e.printStackTrace();
            // TODO
        }
        return html;
    }

    public static String imageToBase64String(byte[] data, String type) {
        return "data:" + type + ";base64," + BASE64_ENCODER.encodeToString(data);
    }

    public static File getResourceFile(String fileName) {
        File result = null;
        try {
            result = ResourceUtils.getFile("classpath:misc/" + fileName);
        } catch (FileNotFoundException e) {
            // TODO
            e.printStackTrace();
        }
        return result;
    }

    public static String multipleReplace(String template, HashMap<String, String> tokens) {
        String patternString = "%(" + String.join("|", tokens.keySet()) + ")%";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);
        StringBuffer buffer = new StringBuffer();
        while(matcher.find()) {
            matcher.appendReplacement(buffer, tokens.get(matcher.group(1)));
        }
        matcher.appendTail(buffer);
        String result = buffer.toString();
        return result;
    }
}
