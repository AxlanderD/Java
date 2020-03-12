import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.Query;
import javax.management.QueryExp;
import javax.print.attribute.standard.MediaSize.Other;

import javafx.print.Collation;

public class Sort implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int v1;
    private int v2;
    public int v3;

    Sort(){}

    Sort(int v1,int v2,int v3){
        this.v1 =v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    Sort(Sort o){       
           this.v1 = o.v1;
           this.v2 = o.v2;
           this.v3 = o.v3;
    }

    @Override
    public String toString(){
        return "{v1:"+this.v1+",v2:"+this.v2+",v3:"+this.v3+"}";
    }

    public static void XuanZeSort(int [] array){
        int len = array.length;
        for(int i = len-1;i>=0;i--){
            int temp = array[i];
            for(int j = 0;j < i;j++){
                if(array[j]>temp){
                    array[i] = array[j];
                    array[j] = temp;
                    temp = array[i];
                }
            }
        }
    }
    
    public static void MaoPaoSort(int [] array){
        int len = array.length;
        for(int i =0;i<len;i++){
            for(int j = 0;j<len-1;j++){
                if(array[j]>array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static void QuickSort(int [] array,int left,int right){
        int i = left;
        int j = right;
        if(left>right)
            return;
        int temp = array[left];
        while(left!=right){
            while(left<right&&array[right]>temp){
                right--;
            }
            array[left] = array[right];
            while(left<right&&array[left]<=temp){
                left++;
            }
            array[right] = array[left];           
        }
        array[left] = temp;
        QuickSort(array, i, left-1);
        QuickSort(array, left+1, j);

    }

    public static void swap(Integer a,Integer b){
        Integer temp = new Integer(a.intValue());
        a = b.intValue();
        b = temp.intValue();
    }

    public static <K,V> void printMap(Map<K,V> map){
      
        System.out.println("----------------");
        map.forEach((k,v)->{
            System.out.println("k : "+k+" v : "+v);
        });
        System.out.println("----------------");
    }

    public static void main(String []args) throws IOException{
        int [] arr =new int[] {9,8,7,6,5,4,3,2,1,0,-1,-2,69,105,-123,0};
        int [] a1 = arr.clone();
        int [] a2 = arr.clone();
        int [] a3 = arr.clone();
        Sort.XuanZeSort(a1);
        Sort.MaoPaoSort(a2);
        Sort.QuickSort(a3,0,a3.length-1);
        for(int e:a1)
            System.out.print(e+" ");
        System.out.println();
        for(int e:a2)
            System.out.print(e+" ");
        System.out.println();
        for(int e:a3)
            System.out.print(e+" ");

        String a = "XX是好XX";

        try{
            a =new String(a.getBytes("GBK"),"UTF-8");
            System.out.println(a);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        ArrayList<Singleton> arrayList = new ArrayList<>();
        arrayList.add(Singleton.getInstance());
        arrayList.add(Singleton.getInstance());

        for(Singleton e:arrayList){
            e.getInstanceNum();
        }
        System.out.println(arrayList.get(0) == arrayList.get(1));

        Runnable run = ()->{
            try{
                System.out.println(new String("输出".getBytes("GBK"),"UTF-8"));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        };

        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("hash1", Integer.valueOf("24"));
        hashMap.put("hash2", Integer.valueOf("46"));
        hashMap.put("hash3", Integer.valueOf("12"));
        hashMap.put(null, null);
         
        

        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("hash1", Integer.valueOf("24"));
        hashtable.put("hash2", Integer.valueOf("46"));
        hashtable.put("hash3", Integer.valueOf("12"));
        hashtable.put("hash4", 10);
        
        LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap<>(); 
        linkedHashMap.put("hash1", Integer.valueOf("24"));
        linkedHashMap.put("hash2", Integer.valueOf("46"));
        linkedHashMap.put("hash3", Integer.valueOf("12"));
        linkedHashMap.put(null, null);

        TreeMap<String,Integer> treeMap = new TreeMap<>();
        treeMap.put("hash1", Integer.valueOf("24"));
        treeMap.put("hash2", Integer.valueOf("46"));
        treeMap.put("hash3", Integer.valueOf("12"));
        treeMap.put("hash4", null);

        ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("hash1", 10);
        concurrentHashMap.put("hash2", Integer.valueOf("46"));
        concurrentHashMap.put("hash3", Integer.valueOf("12"));

        System.out.println("hashMap");
        Sort.printMap(hashMap);
        System.out.println("hashtable");
        Sort.printMap(hashtable);
        System.out.println("linkedHashMap");
        Sort.printMap(linkedHashMap);
        System.out.println("TreeMap");
        Sort.printMap(treeMap);
        System.out.println("concurrentHashMap");
        Sort.printMap(concurrentHashMap);

        System.out.println("equal?:" + (hashMap.equals(linkedHashMap)));
        
        Sort s2 = new Sort(1,2,3);
        Sort ss = new Sort(s2);
        outClass os = new outClass(1,2); 
        
        os.v2 = os.v2+1;
    
        System.out.println(hashMap.hashCode() == linkedHashMap.hashCode());

        extendsClass ec= new extendsClass();
        outClass.inner in =  os.new inner();

        System.out.println(Thread.activeCount());
        Integer va = Integer.valueOf("159");
        Integer vb = Integer.valueOf("200");
        swap(va,vb);
        System.out.println("va:"+va + "  vb:"+vb);

        int ia = 200000;
        Integer ib = 200000;
        System.out.println(ia==ib);

        System.gc();
        Runtime rt = Runtime.getRuntime();
        System.out.println("TotalMemory:"+ rt.totalMemory()/1024.0f/1024.0f/1024.0f + " GB");
        System.out.println("FreeMemory:"+ rt.freeMemory());
        System.out.println("UseMemory:"+ (rt.totalMemory() - rt.freeMemory())/1024.0f/1024.0f/1024.0f + "GB");
        System.out.println("MaxMemory:"+ rt.maxMemory());
        try{
             ObjectOutputStream ot = new ObjectOutputStream(new FileOutputStream("Object.txt"));
             ot.writeObject(s2);
        
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            ObjectInputStream it = new ObjectInputStream(new FileInputStream("Object.txt"));
            try{
                Object o =  it.readObject();
                System.out.println(o.toString());
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                System.out.println("finish");
            }
            
        }
       
    }

    
}

