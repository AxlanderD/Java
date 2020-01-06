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

  4.return ...//这里是自定义的条件，比较基本类型使用 == ，比较对象使用equals

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



### 以上为基础
-------------------------------------------
### 以下为高级技术

## 接口
- `Interface`，接口非类而是对需求的描述
```
//定义接口
public interface Compare{
  int Max = 1000;
  int compareTo(Object other);
}
函数签名：返回值类型 函数名 参数
任何实现该接口的类都需要根据函数签名实现 compareTo 这个方法
```
- 接口中的所有方法自动声明为public,所有变量自动声明为 public static final 类型，不可更改的公有静态变量 
```
//实现接口
 class Employee implements Compare<Employee>
 { 
   public int compareTo(Employee other){
     return Double.compare(salary,other.salary);
   }
 }

```
- className  `instanceOf`  InterfaceName 可以检查该类是否实现了该接口
- Comparable 和 Cloneable 是比较和拷贝对象的两个接口
- 抽象类和接口的必要性：
  - Java中为了避免类似于C++的多继承带来的复杂性，因此只允许使用单继承，但是为了有更加灵活的拓展性，因此引入了接口的概念。
  - 接口和抽象类的主要区别在于如果一个类继承了抽象类，因为单继承的特性，就不能再继承其他的类，但是可以选择同时实现多个接口。例如：
  ```
    public class ClassName extends SuperClass implements interface1,interface2,...{
      do something
    }
  ```
- 接口中可以使用的修饰词有 `public` `abstract` `default` `static` `stricfp`
  - `default` 修饰的方法为默认方法，当该方法没有被覆盖的时候默认使用定义在接口中的行为 

## lambda表达式
- lambda 主要是用于传递代码段。例如Arrays.sort(S,lambda)。

```
  //Java中的非法写法
  function fun1 = ()->{
    statements...;
  }

  or

  Object obj = ()->{
    statements...;
  }
  //因为Java中并不支持这种函数式编程,obj为一个对象而非接口
```
- 在Java中因为所有的方法都在类和接口中，本质上并没有函数式编程，因此lambda表达式在Java中遵循了这一点，只能用于转换为函数式接口，如例2
```
  例1
  //lambda可以看作是一个代码块的简写,无需写出返回值类型和方法名 形如 
  (类型 arg1,类型 arg2)->{
    statements...;
  }
  例2
  //或者接口实现
  Runnable run = ()->{
    statements...;
  }
  new Thread(run).start();


  Arrays.sort(String[] s,(s1,s2)->s1.length()-s2.length());//更改默认的排序行为，传入了自定义的排序代码
```
- 当想要实现的动作已经有了完成好的成品，可以使用更简便的一种写法。如 `System.out::print` 等价于 `e->System.out.print(e)` 或者 `Math::pow` 等价于`(x,y)->Math.pow(x,y)`<br> :: 表示方法引用.这点上和 C++中的类外引用方法有些类似，但是在Java中只是使用，C++中则是可以在类外定义。
- 原本在表达式外的变量会被lambda表达式捕捉并且复制一份。因为表达式中的动作执行可能在相隔一段时间之后才会执行。而那个时候自由变量可能已经不存在了，所以lambda表达式需要及时捕获自由变量。而且自由变量必须是不可变的（final 类型），表达式内也不会对自由变量进行更改操作。
```
  public static void repeatMessage(String text,int dalay){
    ActionListener listener = event->{
      System.out.print(text);//注意这里的text是lambda表达式外传进来的自由变量
      Toolkit.getDefaultToolkit().beep();
    };
    Timer t = new Timer(delay,listener);
    t.start();
  }
```

## 内部类
- 内部类可以访问外部类的变量
- 内部类默认仅在定义内部类的类中可见，而包内的其他类不可见相当于权限控制在protected和private之间。但是可以将修饰符显式设置为 public。
- 内部类并非每个实例中都会有该内部类的实例域，只有当调用构造的时候才会创建
- 在外部类外引用内部类的时候写法如下：OutClass.InnerClass

## 代理
----
## 概念解析

## 深拷贝和浅拷贝 - clone
- 默认的克隆是浅拷贝，对于对象中的引用变量会保存一份相同的引用副本而不会重新创建一个引用。如果这个被引用的对象没有更改器的方法，即不可变。那么是安全的，如果对象是可变的话就不安全。
- 深拷贝则是在浅拷贝的基础上进一步对引用对象进行了复制,即深拷贝后，对象中的引用变量也进行了拷贝，不会是两个引用指向同一个子对象，而是两个引用分别指向一个对象
```
  //需要实现clonable 接口
  public Employee clone() throws CloneNotSupportedException
  {
    return (Employee) super.clone;
  }

  //如果Employee这个类中存在例如Date这种可变对象，若要深拷贝则需要进一步操作
  public Employee clone() throws CloneNotSupportedException
  {
    Employee deepClone = (Employee)super.clone();//object.clone,默认的克隆模式
    deepClone.date = (Date)date.clone();
    return deepClone;
  }
```

## 注解



