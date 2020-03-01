## 一、Java基础

### 1、集合

> List Map Set

+ List

  ArrayList,LinkedList,				

ArrayList: Obejct类型的数组，初始化容量为10；扩容机制：为1.5倍的扩容，若是1.5倍的size小于最小所需容量，那数组的容量就为最小的容量，若是比最大的容量还要大，那个size就为最大的容量。

LinkedList：双链表，有前后指针，get方法是将链表分为2段，若是index<大小的一半就在前面一点正序遍历，否则从后边倒叙遍历查找。peek()只查看不移除。pop()和poll()都会将数据移除，但是有一点不同，pop移除时若list没有数据则会抛出NoSuchElementException，poll则是返回null。

Vector:和ArrayList类似，唯一不同的是Vector的方法都加了synchronized。

+ Map	

HashMap,HashTable,ConcurrentHashMap,LinkedHashMap,TreeMap;

HashMap: Node类型的数组

put方法

```java
// onlyIfAbsent 为true不会覆盖原来的值
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 若当前的table为空或者长度等于0，则初始化表默认长度为16，后面看看resize()的实现 
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
    	// 获得到当前节点，若为空则创建一个新的节点
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            // 存在值
            Node<K,V> e; K k;
            // 获得到的节点的hash值和新值的hash值相等，并且key相等 则值为旧值
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 若p是一个树
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 链表
                for (int binCount = 0; ; ++binCount) {
                    // 是否是最后一个节点，加到后面
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 若链表的长度大于等于8则会转化为树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 不是最后一个节点，节点key的hash相等并且key相等
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    // 指向下一个节点
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

resize()方法的原理：

```java
final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
    	// 旧表有值
        if (oldCap > 0) {
            // 容量大于等于最大值
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 2倍的扩容小于最大值 && 旧值大于等于默认的初始化容量，阈值也扩大2倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
      	// 旧表没有值阈值大于0 就以阈值为初始化容量
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            // 否则初始化新的容量和阈值
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
    	// 阈值为0
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            // 容量小于最大值 并且 新的阈值小于最大容量返回新的阈值 否则返回最大值
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
    	// 设置新的阈值
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
    	// 通过新的容量值建立新的node节点 
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
    	// 复制旧表的值到新表
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        // 只有一个值
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        // 树的复制
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        // 链表复制
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            // 原来的索引
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            // 新索引
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        // 原索引放原来的位置
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        // 新索引放的位置
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```

get()方法

```java
 final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
     // 表不为空且长度大于0 并且第一个所在下表的第一个元素不为空
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            // 查看第一个元素是否为空 相等则直接返回
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            // 若有下一个元素
            if ((e = first.next) != null) {
                // 树节点
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    // 循环表里链表的元素
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null
    }
```

## 二、分布式

### 1、分布式架构

+ [分布式概述](https://www.jianshu.com/p/fdd28fa3cba9)
+ [微服务架构](http://dockone.io/article/3687)
+ [认识分布式架构](https://juejin.im/post/5a5cc9f5f265da3e58595611)

## 三、网络

1、http/tcp协议





分布式主键



## 四、Mysql

### 1、数据库事务的四大特性

> 原子性 一致性 隔离性 持久性

### 2、数据库的隔离级别

>read uncommitted 读未提交 脏读 不可重复读 幻读 
>read committed 读已提交 不可重复读（允许其他的事务提交） 幻读
>repeatable read 可重复读。可能会出现幻读，新增或者删除记录
>Serializable 串行

脏读：一个事务读取了另一个事务还未提交的数据

不可重复读：同一个事务中多次读取同一个数据返回不同的结果。数据被修改了

幻读： 一个事务读取了其他事务已提交的数据，导致同一事务中读取的数据不一致。





## 五、jvm

### 1、类加载器

+ #### Bootstrap ClassLoader 根类加载器

  负责将 <JAVA_HOME>/lib路径下的核心类库或-Xbootclasspath参数指定的路径下的jar包加载到内存中，注意必由于虚拟机是按照文件名识别加载jar包的，如rt.jar，如果文件名不被虚拟机识别，即使把jar包丢到lib目录下也是没有作用的(出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类)。

+ #### Extension ClassLoader 拓展类加载器

  负责加载`/lib/ext`目录下或者由系统变量-Djava.ext.dir指定位路径中的类库，开发者可以直接使用标准扩展类加载器。

  ```java
  // ExtClassLoader类中
  private static File[] getExtDirs() {
              String var0 = System.getProperty("java.ext.dirs");
              File[] var1;
              if (var0 != null) {
                  StringTokenizer var2 = new StringTokenizer(var0, File.pathSeparator);
                  int var3 = var2.countTokens();
                  var1 = new File[var3];
                  for(int var4 = 0; var4 < var3; ++var4) {
                      var1[var4] = new File(var2.nextToken());
                  }
              } else {
                  var1 = new File[0];
              }
              return var1;
          }
  ```

  

+ #### Application ClassLoader 系统（System）类加载器

  它负责加载系统类路径java -classpath或-D java.class.path 指定路径下的类库，也就是我们经常用到的classpath路径，开发者可以直接使用系统类加载器，一般情况下该类加载是程序中默认的类加载器，通过ClassLoader#getSystemClassLoader()方法可以获取到该类加载器。

+ 类加载器之间的关系

  根类加载器，c++实现，没有父类加载器；

  拓展类加载器(ExtClassLoader)，由Java语言实现，父类加载器为null；

  系统类加载器(AppClassLoader)，由Java语言实现，父类加载器为ExtClassLoader；

  自定义类加载器，父类加载器肯定为AppClassLoader；

  系统默认的类加载器为系统类加载器。

```java
public Launcher() {
        Launcher.ExtClassLoader var1;
        try {
            // 获取到扩展类加载器
            var1 = Launcher.ExtClassLoader.getExtClassLoader();
        } catch (IOException var10) {
            throw new InternalError("Could not create extension class loader", var10);
        }

        try {
            // 将拓展类加载器设置为系统类加载器的父类 var1
            this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
        } catch (IOException var9) {
            throw new InternalError("Could not create application class loader", var9);
        }
      // 将系统类加载器设置为上下文类加载器
  		Thread.currentThread().setContextClassLoader(this.loader);
			// .... 省略一些代码
    }
```



### 2、JVM参数

查看JVM的默认参数

-  java -XX:+PrintFlagsInitial 查看虚拟机初始化参数
- java  -XX:+PrintFlagsFinal 查看虚拟机修改后的参数，最终的参数
- java -XX:+PrintCommandLineFlags 查看修改过的参数

常用参数：

![image-20200223122219346](file:///Users/mason/Library/Application%20Support/typora-user-images/image-20200223122219346.png?lastModify=1582441653)

Xms 初始化堆内存大小 默认为物理内存的1/64 等价于 -XX：InitialHeapSize Xmx 最大堆内存大小 默认为物理内存的1/4 等价于 -XX：MaxHeapSize Xss 单个线程栈的大小 默认为521k-1024k 等价于 -XX：ThreadStackSize



3、垃圾回收计数

- 标记清除

- 标记整理

- 复制

  ![image-20200223105906808](file:///Users/mason/Library/Application%20Support/typora-user-images/image-20200223105906808.png?lastModify=1582441653)

Gc root  根搜索算法。可达性分析

虚拟机栈（栈帧中的本地变量表）中引用的对象。

方法区中类静态属性引用的对象。

方法区中常量引用的对象。

本地方法栈中JNI（即一般说的Native方法）引用的对象。



### 3、垃圾收集器

<img src="/Users/mason/Library/Application Support/typora-user-images/image-20200223230011703.png" alt="image-20200223230011703" style="zoom:50%;" />

连线表示垃圾收集器之间可配合使用。

+ Serial收集器，“这个收集器是一个单线程的收集器，但它的“单线程”的意义并不仅仅说明它只会使用一个CPU或一条收集线程去完成垃圾收集工作，更重要的是在它进行垃圾收集时，必须暂停其他所有的工作线程，直到它收集结束。<font color="red">新生代收集器</font>"

  

  <img src="/Users/mason/Library/Application Support/typora-user-images/image-20200223230201473.png" alt="image-20200223230201473" style="zoom: 67%;" />

  ​														“Serial/Serial Old收集器运行示意图”

  新生代采用复制算法，老年代使用标记整理算法。

+ ParNew收集器，“ParNew收集器其实就是Serial收集器的多线程版本，除了使用多条线程进行垃圾收集之外，其余行为包括Serial收集器可用的所有控制参数（例如：-XX:SurvivorRatio、-XX:PretenureSizeThreshold、-XX:HandlePromotionFailure等）、收集算法、Stop The World、对象分配规则、回收策略等都与Serial收集器完全一样，在实现上，这两种收集器也共用了相当多的代码。<font color="red">新生代收集器</font>”

<img src="/Users/mason/Library/Application Support/typora-user-images/image-20200223230316743.png" alt="image-20200223230316743" style="zoom: 67%;" />

​															“ParNew/Serial Old收集器运行示意图”

 新生代采用复制算法，老年代使用标记整理算法。“它默认开启的收集线程数与CPU的数量相同，可以使用
-XX:ParallelGCThreads参数来限制垃圾收集的线程数。”

<font color="red">注意：</font>
“并行（Parallel）：指多条垃圾收集线程并行工作，但此时用户线程仍然处于等待状态。”
“并发（Concurrent）：指用户线程与垃圾收集线程同时执行（但不一定是并行的，可能会交替执行），用户程序在继续运行，而垃圾收集程序运行于另一个CPU上。”

+ Parallel Scavenge收集器
   “Parallel Scavenge收集器是一个<font color="red">新生代收集器</font>，它也是使用复制算法的收集器，又是并行的多线程收集器,目标则是达到一个可控制的吞吐量（Throughput）。所谓吞吐量就是CPU用于运行用户代码的时间与CPU总消耗时间的比值，<font color ="red">即吞吐量=运行用户代码时间/（运行用户代码时间+垃圾收集时间）</font>，虚拟机总共运行了100分钟，其中垃圾收集花掉1分钟，那吞吐量就是99%。Parallel Scavenge收集器提供了两个参数用于精确控制吞吐量，分别是控制最大垃圾收集停顿时间的-XX:MaxGCPauseMillis参数以及直接设置吞吐量大小的-XX:GCTimeRatio参数。GC停顿时间缩短是以牺牲吞吐量和新生代空间来换取的,这也直接导致垃圾收集发生得更频繁一些,时间是缩短了，但是吞吐量下降了。“

+ Serial Old收集器
  “Serial Old是Serial收集器的老年代版本，它同样是一个单线程收集器，使用“标记-整理”算法。这个收集器的主要意义也是在于给Client模式下的虚拟机使用。如果在Server模式下，那么它主要还有两大用途：一种用途是在JDK 1.5以及之前的版本中与Parallel Scavenge收集器搭配使用，另一种用途就是作为CMS收集器的后备预案，在并发收集发生Concurrent Mode Failure时使用。”

  <img src="/Users/mason/Library/Application Support/typora-user-images/image-20200224083553905.png" alt="image-20200224083553905" style="zoom:67%;" />

  ​														“Serial/Serial Old收集器运行示意图”

  新生代采用复制算法，老年代使用标记整理算法。

+ Parallel Old收集器
  “Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和“标记-整理”算法。”

<img src="/Users/mason/Library/Application Support/typora-user-images/image-20200224084147093.png" alt="image-20200224084147093" style="zoom:67%;" />

​										“Parallel Scavenge/Parallel Old收集器运行示意图”

+ CMS 收集器
  “CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器，重视响应速度。CMS作为老年代的收集器，新生代只能选择ParNew或者Serial收集器中的一个。” CMS收集器的工作主要分一下几个步骤：

  1、初始标记（CMS initial mark）

  2、并发标记（CMS concurrent mark）

  3、重新标记（CMS remark）

  4、并发清除（CMS concurrent sweep）
  “初始标记、重新标记这两个步骤仍然需要"Stop The World"。初始标记仅仅只是标记一下GC Roots能直接关联到的对象，速度很快，并发标记阶段就是进行GC RootsTracing的过程，而重新标记阶段则是为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段稍长一些，但远比并发标记的时间短。

  <img src="/Users/mason/Library/Application Support/typora-user-images/image-20200224085308214.png" alt="image-20200224085308214" style="zoom:67%;" />

  CMS 收集器的缺点：

  + CMS收集器对CPU资源非常敏感，CMS默认启动的回收线程数是（CPU数量+3）/4”；
  + CMS收集器无法处理浮动垃圾（Floating Garbage）；
  + 会有大量空间碎片产生。

 + G1收集器
   G1是一款面向服务端应用的垃圾收集器。
   有一下优点：
   1、并行与并发：G1能充分利用多CPU、多核环境下的硬件优势，使用多个CPU（CPU或者CPU核心）来缩短Stop-The-World停顿的时间，部分其他收集器原本需要停顿Java线程执行的GC动作，G1收集器仍然可以通过并发的方式让Java程序继续执行。

   2、分代收集
   3、空间整合：不会导致大碎片的空间
   4、可预测的停顿

   步骤大致为：

   初始标记（Initial Marking）

   并发标记（Concurrent Marking）

   最终标记（Final Marking）

   筛选回收（Live Data Counting and Evacuation

   

   <img src="/Users/mason/Library/Application Support/typora-user-images/image-20200224095723440.png" alt="image-20200224095723440" style="zoom:67%;" />

参数总结

<img src="/Users/mason/Library/Application Support/typora-user-images/image-20200224101605999.png" alt="image-20200224101605999" style="zoom: 35%;" />

<img src="/Users/mason/Library/Application Support/typora-user-images/image-20200224101640591.png" alt="image-20200224101640591" style="zoom:35%;" />



## 六、zookeeper





## 七、消息队列

### 1、RabbitMQ

+ 异步通信（跨系统或者同一应用）
+ 应用之间的耦合
+ 削峰（同一时间，请求过多）

### 2、rabbitmq 怎么避免消息丢失？

1. **消息持久化**
2. **ACK确认机制**
3. **设置集群镜像模式**
4. **消息补偿机制**





如何设计一个高并发系统？

- 系统拆分
- 缓存
- MQ
- 分库分表
- 读写分离
- ElasticSearch













