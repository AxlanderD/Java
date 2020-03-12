public class Singleton{
    private static class single{
        private static final Singleton instance = new Singleton();
    }
    // static {
    //     instance = new Singleton();
    // }
    private static int InstanceCount = 0;
    private Singleton(){
        InstanceCount++;
    };


    public static Singleton getInstance(){
        return single.instance;
    }

    public static void getCount(){
        System.out.print("\n Count:"+InstanceCount);
    }

    public void getInstanceNum(){
        System.out.println("Instance No:" + InstanceCount);
    }
}