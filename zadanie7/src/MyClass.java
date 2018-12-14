public class MyClass {
    @BeforeSuite
    public void start() {
        System.out.println("start");
    }

    @Test(priority = 4)
    public void test1() {
        System.out.println("test1");
    }

    @Test(priority = 7)
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 2)
    public void test3() {
        System.out.println("test3");
    }

    @AfterSuite
    public void end() {
        System.out.println("end");
    }

//    @AfterSuite
//    public void end2() {
//        System.out.println("end2");
//    }

}
