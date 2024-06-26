package com.liang.demo4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadConcurrentHashMapDemo {

    //底层使用synchronizes 同步代码块的方式
    Map<String, String> map = new ConcurrentHashMap<>();


    public void ThreadTestMap() {

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    map.put("B"+i, i + "");
                }

            }

        }, "ThreadTestMap").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    map.put("A"+i, i + "");
                }
            }
        }, "ThreadTestMap2").start();

    }



    public static void main(String[] args) {

        ThreadConcurrentHashMapDemo demo = new ThreadConcurrentHashMapDemo();
        demo.ThreadTestMap();

        demo.map.forEach((k, v) -> {
            System.out.println("key:" + k + " value:" + v);
        });

    }

}
