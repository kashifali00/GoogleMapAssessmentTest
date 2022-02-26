package com.qa.Hook;

import com.qa.BaseTest;
import io.cucumber.java.*;

public class hook {

    BaseTest baseTest;
    public hook(){
        baseTest = new BaseTest();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario){
        System.out.println("printing beforestep from hook.java");

    }

    @AfterStep
    public void afterStep(){
        System.out.println("printing aftersetp from hook.java");


    }

    @Before
    public void before(){
        System.out.println("printing before from hook.java");

    }

    @After
    public void after(){
        System.out.println("printing after from hook.java");
    }
}
