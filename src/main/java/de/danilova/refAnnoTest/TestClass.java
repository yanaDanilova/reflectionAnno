package de.danilova.refAnnoTest;

public class TestClass {

    @Test(priority = 1)
    public void method1(){
        System.out.println("TestPriority1");
    }
    @Test(priority = 2)
    public void method2(){
        System.out.println("TestPriority2");
    }

    @BeforeSuite
    public void method3(){
        System.out.println("BeforeSuite"); ;
    }

    @AfterSuite
    public void method4(){
        System.out.println("AfterSuit");
    }
}
