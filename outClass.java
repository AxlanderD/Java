public class outClass{
    private int v1;
    public int v2;

    class inner{
        inner(){
            System.out.println("inner class , private v1:"+v1);
        }

    }

    outClass(){}

    outClass(int v1,int v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getV1(){
        return this.v1;
    }
    
    public String getV1(String str){
        return str+"v1::::::";
    } 

    public int getV2(){
        return this.v2;
    }
}