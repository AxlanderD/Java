@FunctionalInterface
public interface  testInterface<T>{

    public void test1(T t);

    public default void test2(){
        System.out.println("this is test2");
    }

    static void testStatic(){
        System.out.println("this is static interfaceTest");
    }

}