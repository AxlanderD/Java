
public class threadTest{

    private volatile int j = 10;
    private volatile int i = 5;

    static void staticTest(){
        System.out.println("override static?");
    }

    private void privateTest(){
        System.out.println("override private?");
    }

    synchronized public void addOne(){
        j++;
        i--;
        System.out.println("-----thName: "+Thread.currentThread().getName()+" add ,result:"+j);
    }

    synchronized public void decOne(){
        j--;
        i--;
        System.out.println("-----thName: "+Thread.currentThread().getName()+" dec ,result:"+j);
    }

    class dec implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            decOne();
        }
    }

    class add implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            addOne();
        }
    }

    public static void main(String ...args){
        threadTest th = new threadTest();
        add thAdd = th.new add();
        dec thDec = th.new dec();
        try{
            for(int i = 0;i<2;i++){
                new Thread(thAdd).start();
                new Thread(thDec).start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
