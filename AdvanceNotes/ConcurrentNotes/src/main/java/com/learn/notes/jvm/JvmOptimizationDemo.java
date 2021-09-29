package com.learn.notes.jvm;

/**
 * 查看JVM参数
 *  方式一：
 *    1、jps命令定位进程号： jps -l
 *    2、jstack找到死锁查看：jstack 进程号
 *    3、jinfo查看进程信息：
 *              查看JVM进程某一参数：jinfo -flag PrintGCDetails 进程号
 *              查看JVM进程所有参数：jinfo -flags 进程号
 *              两个经典参数：
 *                   -Xms等价于-XX:InitialHeapSize，初始大小内存，默认物理内存1/64
 *                   -Xmx等价于-XX:MaxHeapSize，最大分配内存，默认为物理内存1/4
 *  方式二：
 *    1、java -XX:+PrintFlagsInitial  等价于  jinfo -flags 进程号
 *
 * 查看初始默认参数值：-XX:+PrintFlagsInitial
 * 查看修改更新参数值：-XX:+PrintFlagsFinal
 * 查看正在使用的参数：-XX:+PrintCommandLineFlags -version
 *
 * 垃圾回收算法：GC算法(引用计数/复制/标清/标整)是内存回收的方法论，垃圾收集器就是算法落地实现。
 *    1、引用计数法
 *    2、复制拷贝：主要用于新生代
 *    3、标记清除：主要用于老年代
 *    4、标记清除压缩：主要用于老年代
 *
 * 四大垃圾回收方式：
 *    1、串行垃级回收器(Serial)：
 *          它为单线程环境设计且值使用一个线程进行垃圾收集，会暂停所有的用户线程（STW: stop-the-word），
 *          只有当垃圾回收完成时，才会重新唤醒主线程继续执行。所以不适合服务器环境。
 *    2、并行垃圾回收器(Parallel) ：
 *          多个垃圾收集线程并行工作，此时用户线程也是阻塞的，适用于科学计算/大数据处理等弱交互场景，
 *          也就是说Serial 和 Parallel其实是类似的，不过是多了几个线程进行垃圾收集，但是主线程都会被暂停（STW: stop-the-word），
 *          但是并行垃圾收集器处理时间，肯定比串行的垃圾收集器要更短。
 *    3、并发垃圾回收器(CMS)：
 *          用户线程和垃圾收集线程同时执行（不一定是并行，可能是交替执行），
 *          不需要停顿用户线程，互联网公司都在使用，适用于响应时间有要求的场景。
 *    4、G1垃圾回收器：
 *          G1垃圾回收器将堆内存分割成不同的区域然后并发的对其进行垃圾回收。
 *
 * 如何查看默认的垃圾收集器
 *    java -XX:+PrintCommandLineFlags -version
 *
 * 七大垃圾收集器：
 *    1、年轻代GC
 *      UserSerialGC：串行垃圾收集器
 *          一个单线程的收集器，在进行垃圾收集时候，必须暂停其他所有的工作线程直到它收集结束。没有线程交互的开销，可以获得最高的单线程垃圾收集效率。
 *          开启上述参数后，会使用：Serial(Young区用) + Serial Old(Old区用)的收集器组合
 *          说明：新生代、老年代都会使用串行回收收集器，新生代使用复制算法，老年代使用标记-整理算法
 *          VM参数：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *
 *      UserParallelGC：并行垃圾收集器
 *          Parallel Scavenge收集器类似ParNew也是一个新生代垃圾收集器，使用复制算法，
 *          也是一个并行的多线程的垃圾收集器，俗称吞吐量优先收集器。一句话：串行收集器在新生代和老年代的并行化。
 *          可控制的吞吐量(Thoughput=运行用户代码时间(运行用户代码时间+垃圾收集时间),也即比如程序运行100分钟，垃圾收集时间1分钟，吞吐量就是99% )。
 *          高吞吐量意味着高效利用CPU的时间，它多用于在后台运算而不需要太多交互的任务。
 *          自适应调节策略也是ParallelScavenge收集器与ParNew收集器的一个重要区别。
 *          (自适应调节策略:虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间（-XX:MaxGCPauseMillis）或最大的吞吐量。
 *          开启该参数后：新生代使用复制算法，老年代使用标记-整理算法。
 *          VM参数：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 *
 *      UseParNewGC：年轻代的并行垃圾回收器
 *          使用多线程进行垃圾回收，在垃圾收集时，会Stop-The-World暂停其他所有的工作线程直到它收集结束。
 *          ParNew收集器其实就是Serial收集器新生代的并行多线程版本，最常见的应用场景是配合老年代的CMS GC工作，
 *          其余的行为和Seria收集器完全一样，ParNew垃圾收集器在垃圾收集过程中同样也要暂停所有其他的工作线程。
 *          它是很多Java虚拟机运行在Server模式下新生代的默认垃圾收集器。
 *          开启上述参数后，会使用：ParNew(Young区)+ Serial Old的收集器组合，
 *          说明：新生代使用复制算法，老年代采用标记-整理算法
 *          VM参数：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 *
 *    2、老年代GC
 *      UserSerialOldGC：串行老年代垃圾收集器（已经被移除）
 *      UseParallelOldGC：老年代的并行垃圾回收器
 *      UseConcMarkSweepGC：（CMS）并发标记清除
 *          CMS收集器(Concurrent Mark Sweep：并发标记清除）是一种以获取最短回收停顿时间为目标的收集器。
 *          适合应用在互联网站或者B/S系统的服务器上，这类应用尤其重视服务器的响应速度，希望系统停顿时间最短。
 *          CMS非常适合地内存大、CPU核数多的服务器端应用，也是G1出现之前大型应用的首选收集器。
 *          Concurrent Mark Sweep并发标记清除，并发收集低停顿,并发指的是与用户线程一起执行
 *          开启该参数后，使用ParNew（Young区用）+ CMS（Old区用）+ Serial Old的收集器组合，Serial Old将作为CMS出错的后备收集器。
 *          4步过程：
 *              初始标记（CMS initial mark） - 只是标记一下GC Roots能直接关联的对象，速度很快，仍然需要暂停所有的工作线程。
 *              并发标记（CMS concurrent mark）和用户线程一起 - 进行GC Roots跟踪的过程，和用户线程一起工作，不需要暂停工作线程。主要标记过程，标记全部对象。
 *              重新标记（CMS remark）- 为了修正在并发标记期间，因用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，仍然需要暂停所有的工作线程。由于并发标记时，用户线程依然运行，因此在正式清理前，再做修正。
 *              并发清除（CMS concurrent sweep） - 清除GCRoots不可达对象，和用户线程一起工作，不需要暂停工作线程。基于标记结果，直接清理对象，由于耗时最长的并发标记和并发清除过程中，垃圾收集线程可以和用户现在一起并发工作，所以总体上来看CMS 收集器的内存回收和用户线程是一起并发地执行。
 *
 *    3、老嫩通吃
 *      UseG1GC：G1垃圾收集器
 *          G1是一种服务器端的垃圾收集器，应用在多处理器和大容量内存环境中，在实现高吞吐量的同时，尽可能的满足垃圾收集暂停时间的要求。
 *          特性:
 *              1、像CMS收集器一样，能与应用程序线程并发执行。
 *              2、整理空闲空间更快。
 *              3、需要更多的时间来预测GC停顿时间。
 *              4、不希望牺牲大量的吞吐性能。
 *              5、不需要更大的Java Heap。
 *          特点：
 *              1、G1能充分利用多CPU、多核环境硬件优势，尽量缩短STW。
 *              2、G1整体上采用标记-整理算法，局部是通过复制算法，不会产生内存碎片。
 *              3、宏观上看G1之中不再区分年轻代和老年代。把内存划分成多个独立的子区域(Region)，可以近似理解为一个围棋的棋盘。
 *              4、G1收集器里面讲整个的内存区都混合在一起了，但其本身依然在小范围内要进行年轻代和老年代的区分，保留了新生代和老年代，但它们不再是物理隔离的，而是一部分Region的集合且不需要Region是连续的，也就是说依然会采用不同的GC方式来处理不同的区域。
 *              5、G1虽然也是分代收集器，但整个内存分区不存在物理上的年轻代与老年代的区别，也不需要完全独立的survivor(to space)堆做复制准备。G1只有逻辑上的分代概念，或者说每个分区都可能随G1的运行在不同代之间前后切换。
 *
 *  GC之约定参数说明：
 *      DefNew：Default New Generation
 *      Tenured：Old
 *      ParNew：Parallel New Generation
 *      PSYoungGen：Parallel Scavenge
 *      ParOldGen：Parallel Old Generation
 *
 *  Server/Client模式：
 *      使用范围：一般使用Server模式，Client模式基本不会使用
 *      操作系统：
 *          1、32位的Window操作系统，不论硬件如何都默认使用Client的JVM模式
 *          2、32位的其它操作系统，2G内存同时有2个cpu以上用Server模式，低于该配置还是Client模式
 *          3、64位只有Server模式
 *
 *  GC之如何选择垃圾收集器
 *      单CPU或者小内存，单机程序
 *          -XX:+UseSerialGC
 *      多CPU，需要最大的吞吐量，如后台计算型应用
 *          -XX:+UseParallelGC（这两个相互激活）
 *          -XX:+UseParallelOldGC
 *      多CPU，追求低停顿时间，需要快速响应如互联网应用
 *          -XX:+UseConcMarkSweepGC
 *          -XX:+ParNewGC
 *
 *  G1收集器与CMS收集器的比较：
 *      1、G1是一个有整理内存过程的垃圾收集器，不会产生很多内存碎片。
 *      2、G1的Stop The World(STW)更可控，G1在停顿时间上添加了预测机制，用户可以指定期望停顿时间。
 *      3、主要改变是Eden，Survivor和Tenured等内存区域不再是连续的了，而是变成了一个个大小一样的region ,
 *         每个region从1M到32M不等。一个region有可能属于Eden，Survivor或者Tenured内存区域。
 *
 *  GC之G1参数配置:
 *      -XX:+UseG1GC
 *      -XX:G1HeapRegionSize=n：设置的G1区域的大小。值是2的幂，范围是1MB到32MB。目标是根据最小的Java堆大小划分出约2048个区域。
 *      -XX:MaxGCPauseMillis=n：最大GC停顿时间，这是个软目标，JVM将尽可能（但不保证）停顿小于这个时间。
 *      -XX:InitiatingHeapOccupancyPercent=n：堆占用了多少的时候就触发GC，默认为45。
 *      -XX:ConcGCThreads=n：并发GC使用的线程数。
 *      -XX:G1ReservePercent=n：设置作为空闲空间的预留内存百分比，以降低目标空间溢出的风险，默认值是10%。
 */
public class JvmOptimizationDemo {
    /**
     * 创建一个超出堆内存的对象
     * @param args
     */
    public static void main(String[] args) {
        byte[] array = new byte[80 * 1024 * 1024];
    }
}
