package com.demoblaze.tests;

import com.demoblaze.utils.BaseClass;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest {
    BaseClass base;
    HomePage home;
    LoginPage login;

    @BeforeMethod
    public void setUp() {
        base = new BaseClass();
        base.initializeDriver();
        home = new HomePage(base.driver);
        login = new LoginPage(base.driver);
    }

    @Test
    public void testLogin() {
        home.clickLogin();
        login.enterUsername("testuser");
        login.enterPassword("password");
        login.clickLoginButton();
        // Add assertions based on expected results
    }

    @AfterMethod
    public void tearDown() {
        base.quitDriver();
    }
}
