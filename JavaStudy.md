## HashTable
- Serializable 序列化
- Dictionary 字典
- Map 数据结构 {值:值}或者说是键值对的数据结构 相比于JSON更加的灵活，并不是只可以使用String作为Key或者value。

## This 的用法
- 1.作为一个指向类对象的指针，如`this`.实例名
- 2.指向另一个构造器，如 `this`(String name,double score); 意思是调用另一个构造函数

## 三种初始化方法
- 使用构造函数
- 声明变量时赋值
* - 使用初始化块,例如 { this.name = "n1" ; this.score = 1.2; } 该初始化块会自动执行

## 包的使用
- 导入包使用 import 如：import com.dwj.xxx (将定位类所在的位置，在子目录 com/dwj 下寻找)
- 打包使用 package com.dwj (那么应该把源文件放在 子目录 com/dwj 下)
- Jar包是一种压缩形式的包，可以改善性能而且该密封包一旦制作成功便无法添加更多的类在其中

## private、public、default、protected
- `default` 默认被包内的所有类访问
- `public` 都可以访问
- `private` 仅本类才可以访问
- `protected` 对本包以及所有子类可见

## native、transient 

## 泛型

## 变参方法
- 例子如printf(" format ",args),format中参数参数，和后面的args参数数目不定，这时候不可能写一个方法对应一个情况把所有情况全都包含。
```
  //源码这里就使用了变参的写法
  public PrintStream printf(String format, Object ... args) {
        return format(format, args);
  }
```
- ... 是java中的语法,相当于是创建了一个参数数组
```
  public static void main(String [] args){}
  等同于
  public static void main(String ...args){}
```

## Object类
- 在Java中Object类作为所有类的根类而存在，当没有指定超类存在的时候默认`Object`类为根类
- Object类中的一些方法:
  - Object.equals()：检查是否是相同的引用，可以自定义判断标准，例如: name.equals(B.name);
  - Object.getClass():返回所属的类的信息
  - Object.hasCode():返回散列码，唯一标识对象，散列值用于插入对象到散列表中
  - Object.notify():主要是和wait一起使用，通知在该对象上的一个线程通过wait()返回
  - Object.notifyAll():通知该对象上所有的线程返回
  - Object.wait():设置等待的时间，如果在设置的时间内没有通知则返回。调用这个函数的线程会进入等待状态，只有在等到通知或者中断后才会返回，并且在等待的时候会释放对象上的锁，即等待时，允许其他线程对该对象进行操作。
  ```
    wait()和notify()主要是用于并发编程
  ```
  - Object.toString()：返回对象的字符串描述，一般格式是
  ```
    ClassName[属性名:值,属性名:值...]
  ```
  - Object.wait()

## equals 和 ==
- 当比较对象为基本类型的时候使用 == 
- 当比较对象是对象时，使用equals进行比较，比较引用
```
  /*Object.equals()方法的源代码
  可以发现这里仅仅是比较是否引用了同一个对象，因此要满足合适自己程序的equals方法需要自己重新写覆盖方法。
  注意覆盖的时候形参为 Object 类型,这是因为Object为所有类的超类，因此后面还需要进行类型转换。*/
  public boolean equals(Object obj) {
        return (this == obj);
  }

```
  - equals方法重定义
  ```
  1.if(this == otherObject) return true;//引用指向同一对象的时候返回true
  2.if(otherObject.getClass()!=getClass()) return false;
  //获取类的信息，如果不一致返回false
  3.if(otherObject==null)return false;//如果为空，返回 false

  类型 other = (类型)otherObject//进行类型转化

  4.retunr ...//这里是自定义的条件，比较基本类型使用 == ，比较对象使用equals

  5.如果是一个子类，则需要对超类进行比较，即super.equals(other)
  ```
  - 重定义equals方法后需要重新定义 hashCode() 方法
  ```
    例如：
    return Objects.hash(变量1，变量2);
    即相等的变量应该有相同的散列值
  ```


## 类的设计技巧
- 不要使用过多的基本类型！ 
(八种基本类型: 1.byte 2.char 3.int 4.short 5.long 6.double 7.float 8.boolean )

## 继承、多态
- 类的继承
  - 使用关键字 `extends` 例如:public class A extends B 表示类A继承了类B
  - 超类（父类）、子类,一般是将通用性更高的类作为超类，将具有特定用途的类作为子类
  - 子类不可以访问父类中的私有域，需要借助父类中的公有方法来进行访问，例如: super.getSalary（因为子类中可能也有一个getSalary方法 即覆盖方法，因此需要加上 `super` 关键字）
  同理，如果要对超类的私有域进行构造那就需要使用超类的构造器如：super(String name ,double Salary,LocalDate hireday);
  - 当有子类中存在对父类的覆盖方法的时候，这要区分调用的是父类的方法还是子类的方法。运行时自动选择调用方法的现象称之为 `动态绑定`。
  - 有动态绑定，同时当然也有`静态绑定`.使用private、static、final告知编译器准确的找到需要调的方法。
  - 一个变量可以指向不同类型对象的现象称之为`多态性`(子类的对象也属于超类的对象)。
  - ```
      for(Employee e:staff){
        print(e.getSlary)
      }
      这里的e指向了两个对象，staff[0]是Manager类（继承自Employee类）staff[1]和staff[2]是Employee类，这种一个变量指向不同类型对象的现象称之为 多态性
    ```
  - `final`关键字可以阻止继承即代表被修饰的变量或者类，不可以被改变、继承或者覆盖
  - `abstract` 关键字实现抽象类：拥有多个抽象方法的类必须声明为`抽象类`。抽象方法中不需要具体的实现。抽象类充当一个占位的角色，不可以创建对象
  ```
  例如:
    public abstract class Person{
        public abstract void setName();
        public abstract void getName();
    }
    上面的两个方法是抽象方法，不需要具体实现，而是由继承的类来进行实现。
  ```
  - Java中只支持单继承，不支持多继承，因此若是需要多继承则是要使用到`interface`（接口）

## @Override
- 表明这是一个覆盖超类的覆盖方法，如果并没有进行覆盖的话会报错


## ArrayList
- 一个泛型类，动态数组 `ArrayList<ClassName>`
```
  ArrayList<Employee> staff = new ArrayList<这里可以不填>(这里可以不填，主要是填写预估的空间大小); 

  ArrayList<Employee> staff = new ArrayList<>(); 

```
- 方法
  - add(Object o):添加元素
  - size():求出动态数组当前的元素个数
  - ensureCapacity(int )：在不需要重新分配空间的前提下预留出需要存储的容量（但并非留出空位）
  - trimToSize():将空间缩减到当前尺寸
  - ArrayList中进行元素获取和更改需要使用 get()和set()方法而 <strong>不可以</strong> 通过 Array[i] 这种读取下标的方式来读取其中的元素

## 基本型对应的类（包装器）
- int：Integer
  - 常用方法：
    - int intValue():int型返回Integer的值
    - static String toString(int i):将一个i用String进行表示
    - static String toString(int i，int radix):将一个i用String进行表示,radix为进制
    - static int parseInt(String s):将字符串解析为int
    - static Integer valueOf(String s):将字符串中的值解析为Integer

## 反射
- Class 类记录类相关的信息，可以捕获运行时类的相关信息
  - forName(String className):根据类名返回对应的Class对象
  - newInstance():创建一个类的对象
## 异常
- try{代码段}catch(Exception e){出错处理}

## 接口



