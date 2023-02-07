package tests.testng;

import org.testng.annotations.*;

public class TestNGHook01 {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println(">beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println(">>beforeClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println(">>>beforeMethod");
    }


    @Test
    public void testSomeThing(){
        System.out.println("testSomeThing");

    }

    @Test
    public void testSomeThingsElse(){
        System.out.println("testSomeThingsElse");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }

    @AfterTest
    public void afterTest(){
        System.out.println(">afterTest");
    }

    @AfterClass
    public void afterClass(){
        System.out.println(">>afterClass");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println(">>>afterMethod");
    }

}
