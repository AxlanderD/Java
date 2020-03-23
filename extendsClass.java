import java.util.Comparator;

public class extendsClass extends outClass implements Comparable<extendsClass>{
    private int id; 

    extendsClass(){
    }

    public int compareTo(extendsClass o){
        return this.id - o.id;
    };


}