import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test{
    public static void main(String[] args){
        List<Integer> ls = new ArrayList<>();
        ls.add(1);
        ls.add(2);
        ls.add(3);
        ls.add(4);
        Iterator<Integer> iterator = ls.iterator();
        for (Integer i : ls) {
            if(i==4)
                ls.remove(3);

            iterator.remove();
        }
        System.out.print(ls.indexOf(10));
    }
}