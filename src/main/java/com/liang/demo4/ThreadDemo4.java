package com.liang.demo4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadDemo4 {


    // 1 使用多线程 向arrayList 中添加数据 会报添加异常

   private ArrayList<Integer> list1 = new ArrayList<>();


   //2 使用两个线程 对 vectoer 进行操作

   private List<Integer> list2 =  new Vector<>();

   //3
    private List<Integer> list3 = Collections.synchronizedList(new ArrayList<>());


    //4 copyOnWriteArrayList 解决list线程安全问题
    // 写时复制 ： 并发读取 独立写入（串行） ，
    // 当需要向容器中添加元素的时候 不会直接添加 而是先将当前容器复制一份 向副本容器中添加数据 添加完元素之后再将原容器的引用指向新的容器
    // 缺点： 内存占用较大 数据一致性的问题 只能保证数据最终的一致性 不能保证实时一致性
    //  源码 public boolean add(E e) {
    //        final ReentrantLock lock = this.lock;
    //         加锁
    //        lock.lock();
    //        try {
    //          获取元素数组
    //            Object[] elements = getArray();
    //            int len = elements.length;
    //             扩容一个新数组+1 并将原数组复制过去
    //            Object[] newElements = Arrays.copyOf(elements, len + 1);
    //             将新的元素 写入
    //            newElements[len] = e;
    //             引用指向心的数组
    //            setArray(newElements);
    //            return true;
    //        } finally {
    //           解锁
    //            lock.unlock();
    //        }
    //    }
    List<Integer> list4 = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        ThreadDemo4 demo4 = new ThreadDemo4();

        //at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
        //报出数组异常
       // demo4.threadTestArrayList();

        //使用 vector 没有问题 因为在源码中 vector的 add()方法中 有synchronized 关键字
        //    public synchronized boolean add(E e) {
//        demo4.threadTestVector();

        //使用 Collections 将普通的list 包装为线程安全的list
        //结果多线程操作正常 源码中 synchronizedList 是一个静态的class 重写了list的add方法 使得线程安全
        //public void add(int index, E element) {
        //            synchronized (mutex) {list.add(index, element);}
        //        }
        demo4.threadTestSynchronizedArrayList();

    }


    //使用两个线程 对线程进行操作
    public void threadTestArrayList() {

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list1.add(1);
                }

            }
        }, "ThreadTestArrayList1").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list1.add(2);
                }
            }
        }, "ThreadTestArrayList2").start();

        System.out.println(list1);

    }


    public void threadTestVector() throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list2.add(3);
                }
            }
        }, "ThreadTestVector1").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list2.add(4);
                }
            }
        }, "ThreadTestVector2").start();
        Thread.sleep(10);
        System.out.println(list2);

    }

    public  void threadTestSynchronizedArrayList() throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list3.add(5);
                }
            }
        }, "ThreadTestSynchronizedArrayList1").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list3.add(6);
                }
            }
        }, "ThreadTestSynchronizedArrayList2").start();
        Thread.sleep(10);
        System.out.println(list3);

    }







}
