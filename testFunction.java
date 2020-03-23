import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class testFunction {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int all = scan.nextInt();
        List<String> results = new ArrayList<String>();
        for(int i = 0;i< all;i++){
            long n = scan.nextLong();
            long k = scan.nextLong();
            long d1 = scan.nextLong();
            long d2 = scan.nextLong(); 
            long rest = n-k;
            
            if(n==k){
                if(d1==d2&&d1==0){
                    results.add("yes");
                    continue;
                }else{
                    results.add("no");
                    continue;
                }
            }
            
            if(n!=k){
                long leftMore = 2*d2-d1;
                long rightMore = 2*d1+d2;
                long midMore = 2*d2+d1;
                
                if((rest-leftMore)%3==0|(rest-rightMore)%3==0|(rest-midMore)%3==0){
                    results.add("yes");
                    continue;
                }
                else if(d1 == d2&&d1 == 0){
                    if(rest%3==0){
                        results.add("yes");
                        continue;
                    }else{
                        results.add("no");
                        continue;
                    }
                        
                }
                    
            }
        }
        for(String s:results){
            System.out.println(s);
        }
        scan.close();
        
    }
}