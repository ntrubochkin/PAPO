package com.noname.books_exchange.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeneralUtils {
    //https://stackoverflow.com/questions/12800588/how-to-calculate-a-file-size-from-url-in-java
    public static int getImageSizeFromURL(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            //https://developer.mozilla.org/ru/docs/Web/HTTP/Methods/HEAD
            connection.setRequestMethod("HEAD");
            return connection.getContentLength();
        } catch (IOException ioe) {
            //TODO
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            return -1;
        }
    }
}
