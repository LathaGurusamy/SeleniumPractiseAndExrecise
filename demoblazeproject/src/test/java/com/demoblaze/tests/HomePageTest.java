package com.demoblaze.tests;

import com.demoblaze.utils.BaseClass;
import com.demoblaze.pages.HomePage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {

    BaseClass base;
    HomePage home;

    @BeforeMethod
    public void setUp() {
        base = new BaseClass();
        base.initializeDriver();
        home = new HomePage(base.driver);
    }

    @Test
    public void testHomePageElements() {
        Assert.assertTrue(home.signUpBtn.isDisplayed(), "Sign Up button is not displayed");
        Assert.assertTrue(home.loginBtn.isDisplayed(), "Login button is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        base.quitDriver();
    }
}
