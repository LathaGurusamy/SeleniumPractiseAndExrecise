package com.bestbuy.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.*;

public class CookieUtil {

    public static void loadCookies(WebDriver driver, String cookieFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cookieFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length >= 6) {
                String name = parts[0];
                String value = parts[1];
                String domain = parts[2];
                String path = parts[3];
                boolean isSecure = Boolean.parseBoolean(parts[4]);
                Date expiry = null; // Optional: parse expiry if needed

                Cookie cookie = new Cookie.Builder(name, value)
                        .domain(domain)
                        .path(path)
                        .isSecure(isSecure)
                        .build();

                driver.manage().addCookie(cookie);
            }
        }
        reader.close();
    }
}
