package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    
    @FindBy(id = "signin2")
    public WebElement signUpBtn;
    
    @FindBy(id = "login2")
    public WebElement loginBtn;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignUp() {
        signUpBtn.click();
    }

    public void clickLogin() {
        loginBtn.click();
    }
}
