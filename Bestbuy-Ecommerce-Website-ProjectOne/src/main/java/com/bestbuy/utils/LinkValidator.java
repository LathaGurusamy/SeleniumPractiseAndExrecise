package com.bestbuy.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {

    // ✅ Checks if a single URL is broken
    public static boolean isLinkBroken(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            return responseCode >= 400; // true means broken

        } catch (Exception e) {
            System.out.println("⚠️ Error checking link: " + urlString);
            System.out.println("Exception: " + e.getMessage());
            return true; // treat as broken if exception occurs
        }
    }
}
