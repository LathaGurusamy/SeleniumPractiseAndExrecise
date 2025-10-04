package com.bestbuy.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CookieRead {

    public static void injectCookies(WebDriver driver, String configPath) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(configPath));

        for (String key : props.stringPropertyNames()) {
            String raw = props.getProperty(key);
            String[] parts = raw.split(";");

            String[] nameValue = parts[0].split("=");
            String name = nameValue[0];
            String value = nameValue.length > 1 ? nameValue[1] : "";

            String path = "/";
            boolean isSecure = false;
            String domain = ".bestbuy.com"; // Default domain

            for (String part : parts) {
                part = part.trim();
                if (part.startsWith("Path=")) {
                    path = part.substring("Path=".length());
                } else if (part.equalsIgnoreCase("Secure")) {
                    isSecure = true;
                } else if (part.startsWith(".")) {
                    domain = part;
                }
            }

            Cookie cookie = new Cookie.Builder(name, value)
                    .domain(domain)
                    .path(path)
                    .isSecure(isSecure)
                    .build();

            driver.manage().addCookie(cookie);
        }
    }
}
