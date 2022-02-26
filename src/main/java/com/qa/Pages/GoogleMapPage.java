package com.qa.Pages;

import com.qa.BaseTest;
import org.openqa.selenium.By;

public class GoogleMapPage {

    BaseTest baseTest;

    public GoogleMapPage(){
        baseTest = new BaseTest();
    }

    By searchDublin =  By.xpath("//input[@id='searchboxinput']");
    By searchButton = By.xpath("//button[@id='searchbox-searchbutton']");

    public void searchCity(String text){
        baseTest.waitforvisibilityofElement(searchDublin);
        baseTest.sendTextToElement(searchDublin,text);
        baseTest.ClickOnElement(searchButton);

    }
    public String getPageTitle(){
        return baseTest.getPageTitle();
    }
}
