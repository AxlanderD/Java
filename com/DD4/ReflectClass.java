/*
 * @Author: alexander.DD4 
 * @Date: 2020-01-06 17:46:20 
 * @Last Modified by: alexander.DD4
 * @Last Modified time: 2020-01-06 18:09:17
 */

package com.DD4;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;
import java.util.*;
import java.lang.reflect.*;

public class ReflectClass{
    public static void print(Object o){
        System.out.print(o);
    }
    public static void printf(String fm,Object o){
        System.out.printf(fm,o);
    }
    public static void println(Object o){
        System.out.println(o);
    }

    public static void printConstructor(Class cl){
        Constructor [] constructors = cl.getDeclaredConstructors();
        for(Constructor con:constructors){
            String mo = Modifier.toString(con.getModifiers());
            if(mo.length()>0)
                print(mo+" ");
            print(con.getName()+" (");

            //args
            Class [] argType = con.getParameterTypes();
            if(argType.length>0){
                int len = argType.length;
                for(int i = 0;i<len;i++){
                    if(i<len-1)
                        print(argType[i].getName()+",");
                    else
                        println(argType[i].getName()+");");
                }
            }
        } 
    }

    public static void printMethod(Class cl){
        Method [] methods = cl.getDeclaredMethods();
        for(Method me:methods){
            String mo = Modifier.toString(me.getModifiers());
            if(mo.length()>0)
                print(mo+" ");
            Class returnType = me.getReturnType();
            print(returnType.getName() + " " +me.getName()+" (");

            //args
            Class [] argType = me.getParameterTypes();
            if(argType.length>0){
                int len = argType.length;
                for(int i = 0;i<len;i++){
                    if(i<len-1)
                        print(argType[i].getName()+",");
                    else
                        print(argType[i].getName());
                }
            }
            println(");");
        } 
    }

    public static void printFields(Class cl){
        Field []  fields = cl.getFields();
        if(fields.length>0){
            for(Field f:fields){
                String modify = Modifier.toString(f.getModifiers());
                if(modify.length()>0)
                    print(modify+" ");
                String argType = f.getClass().getName();
                print(argType+" ");
                println(f.getName()+";");
            }

        }

    }


    public static void main(String ...args){
        String className;
        if(args.length>0)
            className = args[0];
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("enter className(e.g java.util.Date):");
            className = in.next();
        }
        try{
            println("Class which find is : "+className+"\n");
            Class cl = Class.forName(className);
            Class superCl = cl.getSuperclass();
            String classType = cl.getClass().toString();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length()>0)
                print(modifiers + " ");
            print("class "+className);
            if(superCl!=null&&superCl!=Object.class)
                print(" extends "+superCl.getName());
            
            println("{");
            println("");
            printConstructor(cl);
            println("");
            printMethod(cl);
            println("");
            printFields(cl);
            println("}");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        

    }
}
